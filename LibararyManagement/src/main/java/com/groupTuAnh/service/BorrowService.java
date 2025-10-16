package com.groupTuAnh.service;

import com.groupTuAnh.model.BookItem;
import com.groupTuAnh.model.BorrowRecord;
import com.groupTuAnh.model.Reader;
import com.groupTuAnh.repository.BookItemRepository;
import com.groupTuAnh.repository.BorrowRecordRepository;
import com.groupTuAnh.repository.ReaderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
public class BorrowService {

    private final BorrowRecordRepository borrowRecordRepo;
    private final BookItemRepository bookItemRepo;
    private final ReaderRepository readerRepo;

    @Value("${library.borrow.max-active:5}")
    private int maxActiveBorrowsPerReader;

    @Value("${library.borrow.default-days:14}")
    private int defaultBorrowDays;

    @Value("${library.fine.overdue-per-day:10000}") // chỉ tính để hiển thị, chưa tạo Penalty entity
    private long overdueFinePerDay;

    /** MƯỢN SÁCH: theo studentCode + bookItemId */
    @Transactional
    public BorrowRecord borrow(String studentCode, Long bookItemId, Long librarianId, LocalDate dueDate) {
        // 1) Tìm reader + bookItem
        Reader reader = readerRepo.findByStudentCode(studentCode)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy độc giả với mã: " + studentCode));

        BookItem item = bookItemRepo.findById(bookItemId)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy bản sao sách: " + bookItemId));

        // 2) Kiểm tra giới hạn số phiếu đang mượn
        long activeCount = borrowRecordRepo.countByReaderAndIsReturnedFalse(reader);
        if (activeCount >= maxActiveBorrowsPerReader) {
            throw new IllegalStateException("Độc giả đã đạt giới hạn mượn: " + maxActiveBorrowsPerReader);
        }

        // 3) Kiểm tra BookItem có đang bị mượn không
        boolean itemInUse = borrowRecordRepo.existsByBookItemAndIsReturnedFalse(item);
        if (itemInUse) {
            throw new IllegalStateException("Bản sao sách này đang được mượn.");
        }

        // 4) Tính hạn trả
        LocalDate today = LocalDate.now();
        LocalDate finalDue = (dueDate != null) ? dueDate : today.plusDays(defaultBorrowDays);
        if (finalDue.isBefore(today)) {
            throw new IllegalArgumentException("Ngày đến hạn không hợp lệ.");
        }

        // 5) Tạo BorrowRecord
        BorrowRecord br = BorrowRecord.builder()
                .reader(reader)
                .bookItem(item)
                .borrowDate(today)
                .dueDate(finalDue)
                .isReturned(false)
                .build();

        // 6) Lưu
        return borrowRecordRepo.save(br);
    }

    /** TRẢ SÁCH: theo recordId */
    @Transactional
    public BorrowRecord returnBorrow(Long recordId, Long librarianId) {
        BorrowRecord br = borrowRecordRepo.findById(recordId)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy phiếu mượn #" + recordId));

        if (br.isReturned()) {
            throw new IllegalStateException("Phiếu mượn đã được trả trước đó.");
        }

        LocalDate today = LocalDate.now();
        br.setReturnDate(today);
        br.setReturned(true);

        // Nếu cần: tính tiền phạt (chỉ để hiển thị/log; entity hiện không lưu fine)
        if (br.getDueDate() != null && today.isAfter(br.getDueDate())) {
            long daysLate = ChronoUnit.DAYS.between(br.getDueDate(), today);
            long fine = (daysLate > 0) ? daysLate * overdueFinePerDay : 0;
            // TODO: nếu bạn có entity Penalty, tạo và set vào br.setPenalty(...).
            // Hiện BorrowRecord đã có quan hệ OneToOne Penalty (mappedBy="record").
        }

        return borrowRecordRepo.save(br);
    }
}
