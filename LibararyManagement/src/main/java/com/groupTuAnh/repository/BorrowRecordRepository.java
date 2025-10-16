package com.groupTuAnh.repository;

import com.groupTuAnh.model.BookItem;
import com.groupTuAnh.model.BorrowRecord;
import com.groupTuAnh.model.Reader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface BorrowRecordRepository extends JpaRepository<BorrowRecord, Long> {
@Query("SELECT br FROM BorrowRecord br WHERE br.isReturned = false AND br.dueDate < :today")
List<BorrowRecord> findOverdueRecords(LocalDate today);

    long countByReaderAndIsReturnedFalse(Reader reader);

    boolean existsByBookItemAndIsReturnedFalse(BookItem bookItem);
}
