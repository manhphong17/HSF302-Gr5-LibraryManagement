package com.groupTuAnh.controller;

import com.groupTuAnh.dto.BookBorrowedResponse;
import com.groupTuAnh.dto.PenaltyResponse;
import com.groupTuAnh.service.BookService;
import com.groupTuAnh.service.ReaderService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BookBorrowController {
    private final BookService bookService;
    private final ReaderService readerService;

/*    @GetMapping("/books")
    public String getBooks(Model model, BookSearchRequest request) {
        List<Book> books = bookService.searchBooksForReader(request);
        model.addAttribute("books", books);
        return "books-borrow";
    }*/

    @GetMapping("/books-borrowed")
    public String getBorrowedBooks(HttpSession session, Model model) {
        Long accountId = (Long) session.getAttribute("userId");

        if (accountId == null) return "redirect:/login";

        List<BookBorrowedResponse> records = readerService.getBorrowedBooks(accountId);
        model.addAttribute("records", records);
        return "borrow";
    }

    @GetMapping("/penalties")
    public String getPenalties(HttpSession session, Model model) {
        Long accountId = (Long) session.getAttribute("userId");
        if (accountId == null) return "redirect:/login";

        List<PenaltyResponse> penalties = readerService.getPenalties(accountId);
        model.addAttribute("penalties", penalties);
        return "penalties";
    }

}
