package com.vuducminh.stylash.demo;

import com.vuducminh.stylash.controller.dto.DailyRevenueDTO;
import com.vuducminh.stylash.controller.dto.OrderDto;
import com.vuducminh.stylash.controller.dto.UserDto;
import com.vuducminh.stylash.mapper.OrderMapper;
import com.vuducminh.stylash.mapper.ProductMapper;
import com.vuducminh.stylash.mapper.UserMapper;
import com.vuducminh.stylash.model.Order;
import com.vuducminh.stylash.model.Product;
import com.vuducminh.stylash.service.OrderService;
import com.vuducminh.stylash.service.ProductService;
import com.vuducminh.stylash.service.UserService;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {
    private final OrderService orderService;
    private final OrderMapper orderMapper;

    private final UserService userService;
    private final UserMapper userMapper;

    private final ProductService productService;
    private final ProductMapper productMapper;


    @GetMapping("/getOrders")
    @PreAuthorize("hasAuthority('admin:read')")
    public List<OrderDto> get() {
        return orderService.viewAll().stream()
                .map(orderMapper::toOrderDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/getUsers")
    @PreAuthorize("hasAuthority('admin:read')")
    public List<UserDto> getUsers() {
        return userService.getAllUsers().stream()
                .map(userMapper::toUserDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/getProducts")
    @PreAuthorize("hasAuthority('admin:read')")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.viewAll();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/total-revenue")
    public BigDecimal getTotalRevenue() {
        return orderService.calculateTotalRevenue();
    }

    @GetMapping("/daily-revenue")
    public List<DailyRevenueDTO> getDailyRevenue() {
        return orderService.calculateDailyRevenue();
    }

//    @GetMapping("/getOrders")
//    public List<OrderDto> getOrders(@RequestParam(value = "text", required = false) String text) {
//        List<Order> orders = (text == null) ? orderService.getOrders() : orderService.getOrdersContainingText(text);
//        return orders.stream()
//                .map(orderMapper::toOrderDto)
//                .collect(Collectors.toList());
//    }

    @PostMapping
    @PreAuthorize("hasAuthority('admin:create')")
    @Hidden
    public String post() {
        return "POST:: admin controller";
    }
    @PutMapping
    @PreAuthorize("hasAuthority('admin:update')")
    @Hidden
    public String put() {
        return "PUT:: admin controller";
    }
    @DeleteMapping
    @PreAuthorize("hasAuthority('admin:delete')")
    @Hidden
    public String delete() {
        return "DELETE:: admin controller";
    }
}
