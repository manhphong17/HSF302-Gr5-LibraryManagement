package com.groupTuAnh.repository;

import com.groupTuAnh.model.Book;
import com.groupTuAnh.model.BookItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookItemRepository extends JpaRepository<BookItem, Long> {
    List<BookItem> findByBook(Book book);
}
