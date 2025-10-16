package com.groupTuAnh.controller;

import com.groupTuAnh.dto.BookDTO;
import com.groupTuAnh.repository.AuthorRepository;
import com.groupTuAnh.repository.CategoryRepository;
import com.groupTuAnh.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("bookDTO", new BookDTO());
        model.addAttribute("authors", authorRepository.findAll());
        model.addAttribute("categories", categoryRepository.findAll());
        return "book/create";
    }

    @PostMapping("/create")
    public String createBook(@ModelAttribute BookDTO bookDTO, Model model) {
        try {
            bookService.createBook(bookDTO);
            return "redirect:/books/list";
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("bookDTO", bookDTO);
            model.addAttribute("authors", authorRepository.findAll());
            model.addAttribute("categories", categoryRepository.findAll());
            return "book/create";
        }
    }

    @GetMapping("/list")
    public String listBooks(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        return "book/list";
    }
}
