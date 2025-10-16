package com.groupTuAnh.repository;


import com.groupTuAnh.enums.PenaltyType;
import com.groupTuAnh.model.PenaltyPolicy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PenaltyPolicyRepository extends JpaRepository<PenaltyPolicy, Long> {

    Optional <PenaltyPolicy> findByType(PenaltyType type);
}
