package com.groupTuAnh.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.groupTuAnh.model.Category;
import com.groupTuAnh.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
}
