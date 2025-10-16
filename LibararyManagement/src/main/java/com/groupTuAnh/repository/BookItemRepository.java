package com.groupTuAnh.repository;

import com.groupTuAnh.model.BookItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookItemRepository extends JpaRepository<BookItem, Long> {
}
