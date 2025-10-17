package com.groupTuAnh.repository;

import com.groupTuAnh.model.Librarian;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LibrarianRepository extends JpaRepository<Librarian, Long> {
    Optional<Librarian> findByAccountAccountId(Long accountId);
}
