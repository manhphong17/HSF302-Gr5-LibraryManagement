package com.groupTuAnh.repository;

import com.groupTuAnh.model.BookItem;
import com.groupTuAnh.model.BorrowRecord;
import com.groupTuAnh.model.Reader;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowRecordRepository extends JpaRepository<BorrowRecord, Long> {

    long countByReaderAndIsReturnedFalse(Reader reader);

    boolean existsByBookItemAndIsReturnedFalse(BookItem bookItem);
}
