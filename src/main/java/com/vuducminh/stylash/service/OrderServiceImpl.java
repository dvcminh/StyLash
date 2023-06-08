package com.vuducminh.stylash.service;

import com.vuducminh.stylash.model.Order;
import com.vuducminh.stylash.model.OrderItem;
import com.vuducminh.stylash.model.Product;
import com.vuducminh.stylash.repository.OrderItemRepository;
import com.vuducminh.stylash.repository.OrderRepository;
import com.vuducminh.stylash.user.User;
import com.vuducminh.stylash.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    @Override
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order findById(Long id) {
        return orderRepository.findById(id).get();
    }

    @Override
    public Order createOrderFromCartItems(List<Product> cartItems, User user) {
        // Tạo đơn hàng mới
        Order order = new Order();
        order.setUser(user);
        order.setOrderDate(LocalDateTime.now());
        order.setPaymentStatus("Pending"); // Trạng thái thanh toán chờ xử lý
        order.setShippingAddress(user.getAddress());

        // Tính tổng giá trị đơn hàng
        BigDecimal totalAmount = BigDecimal.ZERO;
        for (Product cartItem : cartItems) {
            totalAmount = totalAmount.add(cartItem.getPrice());
        }
        order.setTotalAmount(totalAmount);

        // Lưu đơn hàng vào cơ sở dữ liệu
        orderRepository.save(order);

        // Lưu các mục trong giỏ hàng vào bảng order_item
        for (Product cartItem : cartItems) {
            OrderItem orderItem = new OrderItem(order, cartItem, 1, cartItem.getPrice());
            orderItemRepository.save(orderItem);
        }

        return order;
    }
}
