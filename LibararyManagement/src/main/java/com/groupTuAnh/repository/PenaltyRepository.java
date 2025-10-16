package com.groupTuAnh.repository;


import com.groupTuAnh.enums.PenaltyType;
import com.groupTuAnh.model.BorrowRecord;
import com.groupTuAnh.model.Penalty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PenaltyRepository extends JpaRepository<Penalty,Long> {

    Optional<Penalty> findByRecord(BorrowRecord record);

    List<Penalty> findByPenaltyPolicy_Type(PenaltyType type);
}
