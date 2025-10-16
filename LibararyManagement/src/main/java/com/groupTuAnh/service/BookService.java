package com.groupTuAnh.service;

import com.groupTuAnh.dto.BookDTO;
import com.groupTuAnh.dto.BookSearchRequest;
import com.groupTuAnh.enums.BookItemStatus;
import com.groupTuAnh.model.Author;
import com.groupTuAnh.model.Book;
import com.groupTuAnh.model.BookItem;
import com.groupTuAnh.model.Category;
import com.groupTuAnh.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final BookItemRepository bookItemRepository;
    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;
    private final SearchRepository searchRepository;
    private final ReaderRepository readerRepository;

    // create book
    public Book createBook(BookDTO dto) {

        // check duplicate title
        if (bookRepository.existsByTitleIgnoreCase(dto.getTitle())) {
            throw new IllegalArgumentException("Book with title '" + dto.getTitle() + "' already exists!");
        }
        List<Author> authors = authorRepository.findAllById(dto.getAuthorIds());
        List<Category> categories = categoryRepository.findAllById(dto.getCategoryIds());

        Book book = Book.builder().title(dto.getTitle()).stockQuantity(dto.getStockQuantity()).isDeleted(false).authors(authors).categories(categories).build();

        Book savedBook = bookRepository.save(book);

        // create book items based on stock quantity
        List<BookItem> items = new ArrayList<>();
        for (int i = 0; i < dto.getStockQuantity(); i++) {
            BookItem item = BookItem.builder().barCode("BOOK-" + savedBook.getBookId() + "-" + (i + 1)).status(BookItemStatus.AVAILABLE).book(savedBook).build();
            items.add(item);
        }
        bookItemRepository.saveAll(items);

        return savedBook;
    }

    // get book list
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // delete book (soft delete) and its book items
    public void softDeleteBook(Long id) {
        bookRepository.findById(id).ifPresent(book -> {
            book.setDeleted(true);
            bookRepository.save(book);

            List<BookItem> items = bookItemRepository.findByBook(book);
            for (BookItem item : items) {
                item.setStatus(BookItemStatus.NONAVAILABLE);
            }
            bookItemRepository.saveAll(items);
        });
    }

    // restore book and its book items
    public void restoreBook(Long id) {
        bookRepository.findById(id).ifPresent(book -> {
            book.setDeleted(false);
            bookRepository.save(book);

            List<BookItem> items = bookItemRepository.findByBook(book);
            for (BookItem item : items) {
                item.setStatus(BookItemStatus.AVAILABLE);
            }
            bookItemRepository.saveAll(items);
        });
    }

    public BookDTO getBookById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Book not found with id: " + id));

        BookDTO dto = new BookDTO();
        dto.setBookId(book.getBookId());
        dto.setTitle(book.getTitle());
        dto.setStockQuantity(book.getStockQuantity());
        dto.setAuthorIds(book.getAuthors().stream().map(Author::getAuthorId).toList());
        dto.setCategoryIds(book.getCategories().stream().map(Category::getCategoryId).toList());
        return dto;
    }

    public Book updateBook(BookDTO dto) {
        // Get current book
        Book book = bookRepository.findById(dto.getBookId()).orElseThrow(() -> new IllegalArgumentException("Book not found with id: " + dto.getBookId()));

        // Check duplicate title (exclude itself)
        if (bookRepository.existsByTitleIgnoreCase(dto.getTitle()) && !book.getTitle().equalsIgnoreCase(dto.getTitle())) {
            throw new IllegalArgumentException("Book title '" + dto.getTitle() + "' already exists!");
        }

        // Update main info
        book.setTitle(dto.getTitle());
        book.setAuthors(authorRepository.findAllById(dto.getAuthorIds()));
        book.setCategories(categoryRepository.findAllById(dto.getCategoryIds()));

        int oldStock = book.getStockQuantity();
        int newStock = dto.getStockQuantity();
        book.setStockQuantity(newStock);
        Book updatedBook = bookRepository.save(book);

        // Get current book items
        List<BookItem> existingItems = bookItemRepository.findByBook(book);

        // If stock increased -> add new items
        if (newStock > oldStock) {
            int toAdd = newStock - oldStock;
            List<BookItem> newItems = new ArrayList<>();
            for (int i = 0; i < toAdd; i++) {
                BookItem item = BookItem.builder().barCode("BOOK-" + book.getBookId() + "-" + (oldStock + i + 1)).status(BookItemStatus.AVAILABLE).book(book).build();
                newItems.add(item);
            }
            bookItemRepository.saveAll(newItems);
        }

        // If stock decreased -> mark extra items as NONAVAILABLE
        else if (newStock < oldStock) {
            int toDisable = oldStock - newStock;
            List<BookItem> toUpdate = existingItems.stream().sorted((a, b) -> b.getBarCode().compareTo(a.getBarCode())) // newest first
                    .limit(toDisable).toList();
            for (BookItem item : toUpdate) {
                item.setStatus(BookItemStatus.NONAVAILABLE);
            }
            bookItemRepository.saveAll(toUpdate);
        }
        return updatedBook;
    }


    public List<Book> searchBooksForReader(BookSearchRequest request) {
        return searchRepository.searchBooksForReader(
                request.getKeyword(),
                request.getCategoryName(),
                request.getSortBy()
        );
    }


}
