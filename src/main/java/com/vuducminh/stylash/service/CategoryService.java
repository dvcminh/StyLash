package com.vuducminh.stylash.service;

import com.vuducminh.stylash.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();
    Category getCategoryById(Long categoryId);
    Category createCategory(Category category);
    Category updateCategory(Category category);
    void deleteCategory(Long categoryId);
}
