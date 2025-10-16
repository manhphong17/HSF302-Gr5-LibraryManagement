package com.groupTuAnh.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.groupTuAnh.dto.BookDTO;
import com.groupTuAnh.enums.BookItemStatus;
import com.groupTuAnh.model.Author;
import com.groupTuAnh.model.Book;
import com.groupTuAnh.model.BookItem;
import com.groupTuAnh.model.Category;
import com.groupTuAnh.repository.AuthorRepository;
import com.groupTuAnh.repository.BookItemRepository;
import com.groupTuAnh.repository.BookRepository;
import com.groupTuAnh.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final BookItemRepository bookItemRepository;
    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;

    // create book
    public Book createBook(BookDTO dto) {

        // check duplicate title
        if (bookRepository.existsByTitleIgnoreCase(dto.getTitle())) {
            throw new IllegalArgumentException("Book with title '" + dto.getTitle() + "' already exists!");
        }
        List<Author> authors = authorRepository.findAllById(dto.getAuthorIds());
        List<Category> categories = categoryRepository.findAllById(dto.getCategoryIds());

        Book book = Book.builder()
                .title(dto.getTitle())
                .stockQuantity(dto.getStockQuantity())
                .isDeleted(false)
                .authors(authors)
                .categories(categories)
                .build();

        Book savedBook = bookRepository.save(book);

        // create book items based on stock quantity
        List<BookItem> items = new ArrayList<>();
        for (int i = 0; i < dto.getStockQuantity(); i++) {
            BookItem item = BookItem.builder()
                    .barCode("BOOK-" + savedBook.getBookId() + "-" + (i + 1))
                    .status(BookItemStatus.AVAILABLE)
                    .book(savedBook)
                    .build();
            items.add(item);
        }
        bookItemRepository.saveAll(items);

        return savedBook;
    }

    // get book list
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
}
