package com.groupTuAnh.controller;

import com.groupTuAnh.dto.BookSearchRequest;
import com.groupTuAnh.model.Book;
import com.groupTuAnh.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BookBorrowController {
    private final BookService bookService;

    @GetMapping("/books")
    public String getBooks(Model model, BookSearchRequest request) {
        List<Book> books = bookService.searchBooksForReader(request);
        model.addAttribute("books", books);
        return "books-borrow";
    }

}
