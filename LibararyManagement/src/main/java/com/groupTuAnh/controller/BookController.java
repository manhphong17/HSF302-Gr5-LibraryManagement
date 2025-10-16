package com.groupTuAnh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.groupTuAnh.dto.BookDTO;
import com.groupTuAnh.service.BookService;
import com.groupTuAnh.service.AuthorService;
import com.groupTuAnh.service.CategoryService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;
    private final CategoryService categoryService;

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("bookDTO", new BookDTO());
        model.addAttribute("authors", authorService.getAllAuthors());
        model.addAttribute("categories", categoryService.getAllCategories());
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
            model.addAttribute("authors", authorService.getAllAuthors());
            model.addAttribute("categories", categoryService.getAllCategories());
            return "book/create";
        }
    }

    // Display list of all books
    @GetMapping("/list")
    public String listBooks(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        return "book/list";
    }

    // Display the edit form for a specific book
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        BookDTO bookDTO = bookService.getBookById(id);
        model.addAttribute("bookDTO", bookDTO);
        model.addAttribute("authors", authorService.getAllAuthors());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "book/edit";
    }

    // Handle book update
    @PostMapping("/update")
    public String updateBook(@ModelAttribute("bookDTO") BookDTO bookDTO, Model model) {
        try {
            bookService.updateBook(bookDTO);
            model.addAttribute("successMessage", "Book updated successfully!");
            model.addAttribute("categories", categoryService.getAllCategories());
            model.addAttribute("authors", authorService.getAllAuthors());
            model.addAttribute("bookDTO", bookService.getBookById(bookDTO.getBookId()));
            return "book/edit";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Update failed: " + e.getMessage());
            model.addAttribute("categories", categoryService.getAllCategories());
            model.addAttribute("authors", authorService.getAllAuthors());
            return "book/edit";
        }
    }

    // Soft delete
    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.softDeleteBook(id);
        return "redirect:/books/list";
    }

    // Restore soft-deleted book
    @GetMapping("/restore/{id}")
    public String restoreBook(@PathVariable Long id) {
        bookService.restoreBook(id);
        return "redirect:/books/list";
    }

}
