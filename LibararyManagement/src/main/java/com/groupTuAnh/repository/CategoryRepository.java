package com.groupTuAnh.repository;

import com.groupTuAnh.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
