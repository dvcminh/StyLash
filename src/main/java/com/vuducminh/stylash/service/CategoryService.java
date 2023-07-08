package com.vuducminh.stylash.service;

import com.vuducminh.stylash.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();
    List<Category> getAllCategoriesByNameContaining(String name);
    Category getCategoryById(Integer categoryId);
    Category getCategoryByName(String name);
    Category createCategory(Category category);
    Category updateCategory(Category category);
    void deleteCategory(Integer categoryId);
}
