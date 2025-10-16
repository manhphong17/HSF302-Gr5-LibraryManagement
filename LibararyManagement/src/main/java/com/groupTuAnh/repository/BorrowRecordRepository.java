package com.groupTuAnh.repository;

import com.groupTuAnh.model.BorrowRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface BorrowRecordRepository extends JpaRepository<BorrowRecord, Long> {
@Query("SELECT br FROM BorrowRecord br WHERE br.isReturned = false AND br.dueDate < :today")
List<BorrowRecord> findOverdueRecords(LocalDate today);

    @Query("""
        SELECT r FROM BorrowRecord r
        WHERE (:fromDate IS NULL OR r.dueDate >= :fromDate)
          AND (:toDate IS NULL OR r.dueDate <= :toDate)
          AND (:isReturned IS NULL OR r.isReturned = :isReturned)
        ORDER BY r.dueDate ASC
    """)
    List<BorrowRecord> filterBorrowRecords(
            @Param("fromDate") LocalDate fromDate,
            @Param("toDate") LocalDate toDate,
            @Param("isReturned") Boolean isReturned
    );

}
