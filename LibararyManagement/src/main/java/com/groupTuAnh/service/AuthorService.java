package com.groupTuAnh.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.groupTuAnh.model.Author;
import com.groupTuAnh.repository.AuthorRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    public Author createAuthor(Author author) {
        return authorRepository.save(author);
    }

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }
}
