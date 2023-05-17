package com.vuducminh.stylash.repository;

import com.vuducminh.stylash.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    // Additional custom query methods can be added here
}

