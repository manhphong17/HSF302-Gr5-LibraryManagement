package com.groupTuAnh.service;

import com.groupTuAnh.dto.OverdueRecordDTO;
import com.groupTuAnh.enums.PenaltyType;
import com.groupTuAnh.model.BorrowRecord;
import com.groupTuAnh.model.Penalty;
import com.groupTuAnh.repository.BorrowRecordRepository;
import com.groupTuAnh.repository.PenaltyRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
@Builder
public class BorrowRecordService {

private final PenaltyRepository penaltyRepo;
private final BorrowRecordRepository borrowRecordRepo;


public List<OverdueRecordDTO> getAllOverdueRecords(){

    List<Penalty> penalties = penaltyRepo.findByPenaltyPolicy_Type(PenaltyType.OVERDUE);

return penalties.stream()
        .filter(p -> !p.isPaid())
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
            .build();
}

}
