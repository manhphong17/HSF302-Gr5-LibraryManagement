package com.groupTuAnh.repository;

import com.groupTuAnh.model.Reader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReaderRepository extends JpaRepository<Reader, Long> {

    Optional<Reader> findByStudentCode(String studentCode);

    Optional<Reader> findByAccountId(Long accountId);
}
