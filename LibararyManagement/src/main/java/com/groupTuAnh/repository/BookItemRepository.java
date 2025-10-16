package com.groupTuAnh.repository;

import com.groupTuAnh.enums.BookItemStatus;
import com.groupTuAnh.model.Book;
import com.groupTuAnh.model.BookItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookItemRepository extends JpaRepository<BookItem, Long> {
    List<BookItem> findByBook(Book book);

    Optional<BookItem> findFirstByBook_BookIdAndStatus(Long bookId, BookItemStatus status);

}
