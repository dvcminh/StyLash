package com.vuducminh.stylash.service;

import com.vuducminh.stylash.controller.dto.DailyRevenueDTO;
import com.vuducminh.stylash.model.Order;
import com.vuducminh.stylash.model.Product;
import com.vuducminh.stylash.user.User;
import org.aspectj.weaver.ast.Or;

import java.math.BigDecimal;
import java.util.List;

public interface OrderService {
    List<Order> viewAll();

//    List<Order> getOrders();

    Order createOrder(Order order);
    Order findById(Long id);
//    Order createOrderFromCartItems(List<Product> cartItems, User user);
    List<Order> getOrdersByUser(User user);

    List<DailyRevenueDTO> calculateDailyRevenue();

    BigDecimal calculateTotalRevenue();

    List<Order> getOrdersContainingText(String text);
}
