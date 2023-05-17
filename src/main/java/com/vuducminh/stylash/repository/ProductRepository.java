package com.vuducminh.stylash.repository;

import com.vuducminh.stylash.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

    // Additional custom query methods can be added here
}

