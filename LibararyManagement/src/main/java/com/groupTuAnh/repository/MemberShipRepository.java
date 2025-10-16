package com.groupTuAnh.repository;

import com.groupTuAnh.model.MembershipPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberShipRepository extends JpaRepository<MembershipPackage, Long> {
}
