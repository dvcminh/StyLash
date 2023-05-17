package com.vuducminh.stylash.repository;

import com.vuducminh.stylash.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    // Additional custom query methods can be added here
}

