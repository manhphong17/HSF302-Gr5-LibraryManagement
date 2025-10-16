package com.groupTuAnh.service;

import com.groupTuAnh.dto.BorrowRecordDTO;
import com.groupTuAnh.dto.OverdueRecordDTO;
import com.groupTuAnh.enums.BookItemStatus;
import com.groupTuAnh.enums.PenaltyType;
import com.groupTuAnh.model.BookItem;
import com.groupTuAnh.model.BorrowRecord;
import com.groupTuAnh.model.Penalty;
import com.groupTuAnh.model.PenaltyPolicy;
import com.groupTuAnh.repository.BookItemRepository;
import com.groupTuAnh.repository.BorrowRecordRepository;
import com.groupTuAnh.repository.PenaltyPolicyRepository;
import com.groupTuAnh.repository.PenaltyRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
@Builder
public class BorrowRecordService {

private final PenaltyRepository penaltyRepo;
private final BorrowRecordRepository borrowRecordRepo;
private final BookItemRepository bookItemRepo;
private final PenaltyPolicyRepository penaltyPolicyRepo;

    public List<OverdueRecordDTO> getAllOverdueRecords(Boolean isPaid) {
        List<Penalty> penalties;

        if (isPaid == null) { // null = ALL
            penalties = penaltyRepo.findByPenaltyPolicy_Type(PenaltyType.OVERDUE);
        } else {
            penalties = penaltyRepo.findByPenaltyPolicy_TypeAndIsPaid(PenaltyType.OVERDUE, isPaid);
        }

        return penalties.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }


private OverdueRecordDTO mapToDTO(Penalty p) {
    BorrowRecord record = p.getRecord();

    return OverdueRecordDTO.builder()
            .recordId(record.getRecordId())
            .bookTitle(record.getBookItem().getBook().getTitle())
            .readerName(p.getReader().getUserProfile().getName())
            .borrowDate(record.getBorrowDate())
            .dueDate(record.getDueDate())
            .daysLate(p.getTotalDaysLate())
            .totalFine(p.getTotalFine())
            .fineSnapShot(p.getFineSnapshot())
            .penaltyId(p.getPenaltyId())
            .isPaid(p.isPaid())
            .build();
}


    public List<BorrowRecordDTO> getBorrowRecords(LocalDate fromDate, LocalDate toDate, String status) {
        Boolean isReturned = null;
        if ("RETURNED".equalsIgnoreCase(status)) isReturned = true;
        else if ("UNRETURNED".equalsIgnoreCase(status)) isReturned = false;

        List<BorrowRecord> records = borrowRecordRepo.filterBorrowRecords(fromDate, toDate, isReturned);

        LocalDate now =  LocalDate.now();

        // map entity → DTO
        return records.stream()
                .map(r -> {
                    BorrowRecordDTO dto = new BorrowRecordDTO();
                    dto.setRecordId(r.getRecordId());
                    dto.setStudentCode(r.getReader() != null ? r.getReader().getStudentCode() : "N/A");
                    dto.setBookTitle(r.getBookItem() != null && r.getBookItem().getBook() != null
                            ? r.getBookItem().getBook().getTitle() : "Không xác định");
                    dto.setBarCode(r.getBookItem() != null ? r.getBookItem().getBarCode() : "");
                    dto.setBorrowDate(r.getBorrowDate());
                    dto.setDueDate(r.getDueDate());
                    dto.setReturnDate(r.getReturnDate());
                    dto.setReturned(r.isReturned());

                    // tính quá hạn
                    boolean overdue = !r.isReturned() && r.getDueDate() != null && r.getDueDate().isBefore(now);
                    dto.setOverdue(overdue);

                    return dto;
                })
                .toList();
    }

    @Transactional(readOnly = true)
    public boolean isOverdue(Long recordId) {
        BorrowRecord record = borrowRecordRepo.findById(recordId)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy phiếu mượn."));
        return !record.isReturned() && record.getDueDate().isBefore(LocalDate.now());
    }

    @Transactional
    public void returnBook(Long recordId) {
        BorrowRecord record = borrowRecordRepo.findById(recordId)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy phiếu mượn."));

        if (record.isReturned()) throw new IllegalStateException("Phiếu mượn này đã được trả rồi.");

        // cập nhật trạng thái
        record.setReturned(true);
        record.setReturnDate(LocalDate.now());
        borrowRecordRepo.save(record);

        // đổi trạng thái BookItem
        BookItem item = record.getBookItem();
        item.setStatus(BookItemStatus.AVAILABLE);
        bookItemRepo.save(item);
    }


//    @Transactional
//    public Penalty createOrUpdatePenalty(BorrowRecord record, double fineSnapshot, double totalFine) {
//        // Tìm penalty cũ nếu đã tồn tại
//        Penalty penalty = penaltyRepo.findByRecord(record)
//                .orElse(new Penalty()); // nếu chưa có thì tạo mới
//
//        PenaltyPolicy lostPolicy = penaltyPolicyRepo.findByType(PenaltyType.LOST)
//                .orElseThrow(() -> new RuntimeException("Penalty policy for LOST not found"));
//
//        // Cập nhật hoặc set mới các giá trị
//        penalty.setRecord(record);
//        penalty.setReader(record.getReader());
//        penalty.setPenaltyPolicy(lostPolicy);
//        penalty.setFineSnapshot(fineSnapshot);
//        penalty.setTotalFine(totalFine);
//        penalty.setPaid(false);
//
//        // Lưu (insert hoặc update)
//        return penaltyRepo.save(penalty);
//    }
//
//    public BorrowRecord findById(Long id) {
//        return borrowRecordRepo.findById(id);
//    }
}
