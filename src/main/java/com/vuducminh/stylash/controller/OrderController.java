package com.vuducminh.stylash.controller;

import com.vuducminh.stylash.model.Order;
import com.vuducminh.stylash.service.OrderService;
import com.vuducminh.stylash.service.UserService;
import com.vuducminh.stylash.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;

    @PostMapping("/create_order")
    public Long checkout(@RequestParam("totalAmount") BigDecimal totalAmount,
                         @RequestParam("userId") int id,
                         @RequestParam("shippingAddress") String address,
                         @RequestParam("firstName") String firstName,
                         @RequestParam("lastName") String lastName,
                         @RequestParam("email") String email,
                         @RequestParam("phoneNumber") String phone,
                         Authentication authentication) {
        // Lấy thông tin người dùng từ Authentication
        User user = new User(id,email,firstName,lastName,phone,address);

        Order order = new Order();
        order.setUser(user);
        order.setOrderDate(LocalDateTime.now());
        order.setPaymentStatus("Pending"); // Trạng thái thanh toán chờ xử lý
        order.setShippingAddress(user.getAddress());
        order.setTotalAmount(totalAmount);

        orderService.createOrder(order);

        return order.getId();

        // Gọi service để tạo đơn hàng mới và lưu các mục trong giỏ hàng
//        Order order = orderService.createOrderFromCartItems(cartItems, user);

        // Trả về thông báo thành công hoặc thất bại
//        if (order != null) {
//            return ResponseEntity.ok("Đơn hàng đã được tạo thành công.");
//        } else {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi khi tạo đơn hàng.");
//        }
//        return null;
    }
}
