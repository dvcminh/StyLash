package com.vuducminh.stylash.service;

import com.vuducminh.stylash.model.Order;
import com.vuducminh.stylash.model.Product;
import com.vuducminh.stylash.user.User;

import java.util.List;

public interface OrderService {
    Order createOrder(Order order);
    Order findById(Long id);
    Order createOrderFromCartItems(List<Product> cartItems, User user);
}
