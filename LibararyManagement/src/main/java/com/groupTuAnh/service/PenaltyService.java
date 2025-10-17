package com.groupTuAnh.service;

import com.groupTuAnh.enums.BookItemStatus;
import com.groupTuAnh.enums.PenaltyType;
import com.groupTuAnh.enums.TransactionType;
import com.groupTuAnh.model.*;
import com.groupTuAnh.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PenaltyService {
    private final BorrowRecordRepository borrowRecordRepo;
    private final PenaltyPolicyRepository penaltyPolicyRepo;
    private final PenaltyRepository penaltyRepo;
    private final ReaderRepository readerRepo;
    private final TransactionRepository transactionRepo;
    private final BookItemRepository bookItemRepo;

    @Transactional
    public void updateOverduePenalties(){
        LocalDate today = LocalDate.now();

        PenaltyPolicy overduePolicy = penaltyPolicyRepo.findByType(PenaltyType.OVERDUE)
                .orElseThrow(() -> new RuntimeException("Penalty policy for OVERDUE not found"));

        List<BorrowRecord> overdueRecords = borrowRecordRepo.findOverdueRecords(today);

        for (BorrowRecord borrowRecord : overdueRecords) {
            int daysLate = (int) ChronoUnit.DAYS.between(borrowRecord.getDueDate(), today);
            double totalFine = overduePolicy.getFinePerDay() * daysLate;

            Penalty existing = penaltyRepo.findByRecord(borrowRecord).orElse(null);

            if (existing == null) {
                Penalty penalty = Penalty.builder()
                        .record(borrowRecord)
                        .reader(borrowRecord.getReader())
                        .penaltyPolicy(overduePolicy)
                        .totalFine(totalFine)
                        .totalDaysLate(daysLate)
                        .fineSnapshot(overduePolicy.getFinePerDay())
                        .isPaid(false)
                        .build();
                penaltyRepo.save(penalty);
            }else{
                existing.setTotalFine(totalFine);
                existing.setTotalDaysLate(daysLate);
                existing.setFineSnapshot(overduePolicy.getFinePerDay());
                penaltyRepo.save(existing);
            }
        }
    }


    @Transactional
    public void payPenalty(Long penaltyId) {
        Penalty penalty = penaltyRepo.findById(penaltyId)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy phiếu phạt."));

        if (penalty.isPaid()) {
            throw new IllegalArgumentException("Phiếu phạt này đã được thanh toán.");
        }

        Reader reader = penalty.getReader();
        BorrowRecord record = penalty.getRecord();
        BookItem bookItem = record.getBookItem();
        double fine = penalty.getTotalFine();

        if (reader.getBalance() < fine) {
            throw new IllegalArgumentException("Số dư không đủ để trả phạt.");
        }

        // 1. Trừ tiền
        reader.setBalance(reader.getBalance() - fine);

        // 2. Ghi transaction
        Transaction transaction = Transaction.builder()
                .reader(reader)
                .amount(fine)
                .type(TransactionType.PENALTY)
                .date(LocalDate.now())
                .description("Thanh toán phạt quá hạn cho bản ghi #" + record.getRecordId())
                .build();
        transactionRepo.save(transaction);

        // 3. Cập nhật trạng thái
        penalty.setPaid(true);
        record.setReturned(true);
        record.setReturnDate(LocalDate.now());
        bookItem.setStatus(BookItemStatus.AVAILABLE);

        // 4. Lưu lại
        readerRepo.save(reader);
        penaltyRepo.save(penalty);
        borrowRecordRepo.save(record);
        bookItemRepo.save(bookItem);
    }
}
