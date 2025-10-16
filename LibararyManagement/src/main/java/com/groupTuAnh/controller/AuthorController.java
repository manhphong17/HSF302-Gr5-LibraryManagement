package com.groupTuAnh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.groupTuAnh.model.Author;
import com.groupTuAnh.service.AuthorService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("author", new Author());
        model.addAttribute("authors", authorService.getAllAuthors());
        return "author/create";
    }

    @PostMapping("/create")
    public String createAuthor(@ModelAttribute Author author) {
        authorService.createAuthor(author);
        return "redirect:/authors/create";
    }
}
