package com.groupTuAnh.dto;

import com.groupTuAnh.model.Author;
import com.groupTuAnh.model.BookItem;
import com.groupTuAnh.model.Category;
import jakarta.persistence.*;

import java.util.List;

public class BookDetailResponse {

    private final boolean isDeleted = false;
    private Long bookId;
    private String title;
    private int stockQuantity;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "book_authors",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private List<Author> authors;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "book_categories",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories;
    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
    private List<BookItem> bookItems;
}
