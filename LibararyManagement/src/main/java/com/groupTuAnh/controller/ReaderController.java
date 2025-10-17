package com.groupTuAnh.controller;

import com.groupTuAnh.dto.BookBorrowedResponse;
import com.groupTuAnh.dto.BookSearchRequest;
import com.groupTuAnh.dto.PenaltyResponse;
import com.groupTuAnh.model.Book;
import com.groupTuAnh.model.Category;
import com.groupTuAnh.service.BookService;
import com.groupTuAnh.service.CategoryService;
import com.groupTuAnh.service.ReaderService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/reader")
@Slf4j
@RequiredArgsConstructor
public class ReaderController {

    private final ReaderService readerService;
    private final BookService bookService;
    private final CategoryService categoryService;


    @GetMapping("/books")
    public String books(
            Model model, BookSearchRequest request) {

        List<Book> books = bookService.searchBooksForReader(request);
        List<Category> categories = categoryService.getAllCategories();

        model.addAttribute("books", books);
        model.addAttribute("categories", categories);
        return "reader/search-book";
    }

    @GetMapping("/borrowed")
    public String borrowed(Model model, HttpSession session) {
        Long accountId = (Long) session.getAttribute("userId");

        if (accountId == null) return "redirect:/login";

        List<BookBorrowedResponse> records = readerService.getBorrowedBooks(accountId);
        log.info(records.toString());
        model.addAttribute("borrowedBooks", records);
        return "reader/borrow";
    }

    @GetMapping("/penalties")
    public String penalties(Model model, HttpSession session) {
        Long accountId = (Long) session.getAttribute("userId");
        if (accountId == null) return "redirect:/login";

        List<PenaltyResponse> penalties = readerService.getPenalties(accountId);
        model.addAttribute("penalties", penalties);
        return "reader/penalties";
    }
}
