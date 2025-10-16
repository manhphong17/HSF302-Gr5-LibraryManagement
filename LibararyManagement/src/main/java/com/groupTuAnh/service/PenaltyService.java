package com.groupTuAnh.service;

import com.groupTuAnh.enums.PenaltyType;
import com.groupTuAnh.model.BorrowRecord;
import com.groupTuAnh.model.Penalty;
import com.groupTuAnh.model.PenaltyPolicy;
import com.groupTuAnh.repository.BorrowRecordRepository;
import com.groupTuAnh.repository.PenaltyPolicyRepository;
import com.groupTuAnh.repository.PenaltyRepository;
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
}
