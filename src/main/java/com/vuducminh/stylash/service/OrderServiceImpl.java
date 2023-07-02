package com.vuducminh.stylash.service;

import com.vuducminh.stylash.controller.dto.DailyRevenueDTO;
import com.vuducminh.stylash.model.Order;
import com.vuducminh.stylash.repository.OrderItemRepository;
import com.vuducminh.stylash.repository.OrderRepository;
import com.vuducminh.stylash.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    private final UserService userService;

    @Override
    public List<Order> viewAll() {
        return orderRepository.findAll();
    }

//    @Override
//    public List<Order> getOrders() {
//        return orderRepository.findAllByOrderByOrderDateAtDesc();
//    }

    @Override
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order findById(Long id) {
        return orderRepository.findById(id).get();
    }


    @Override
    public List<Order> getOrdersByUser(User user) {
        return orderRepository.findByUser(user);
    }

    @Override
    public List<DailyRevenueDTO> calculateDailyRevenue() {
        List<Order> orders = orderRepository.findAll();

        List<Order> sortedOrders = orders.stream()
                .sorted(Comparator.comparing(Order::getOrderDate).reversed())
                .collect(Collectors.toList());

        List<Order> recentOrders = sortedOrders.stream()
                .limit(14)
                .toList();

        // Group recent orders by date and calculate total revenue for each date
        Map<LocalDate, BigDecimal> revenueByDate = recentOrders.stream()
                .collect(Collectors.groupingBy(order -> order.getOrderDate().toLocalDate(),
                        Collectors.mapping(Order::getTotalAmount, Collectors.reducing(BigDecimal.ZERO, BigDecimal::add))));

        List<DailyRevenueDTO> dailyRevenues = new ArrayList<>();
        for (Map.Entry<LocalDate, BigDecimal> entry : revenueByDate.entrySet()) {
            DailyRevenueDTO dailyRevenue = new DailyRevenueDTO();
            dailyRevenue.setDate(entry.getKey());
            dailyRevenue.setRevenue(entry.getValue());
            dailyRevenues.add(dailyRevenue);
        }

        return dailyRevenues;
    }



    @Override
    public BigDecimal calculateTotalRevenue() {
        return orderRepository.findAll().stream()
                .map(Order::getTotalAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public List<Order> getOrdersContainingText(String text) {
        User user = userService.validateAndGetUserByUsername(text);
        return orderRepository.findByUser(user);
    }
}
