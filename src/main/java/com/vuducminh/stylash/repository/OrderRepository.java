package com.vuducminh.stylash.repository;

import com.vuducminh.stylash.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

    // Additional custom query methods can be added here
}

