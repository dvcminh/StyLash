package com.vuducminh.stylash.mapper;

import com.vuducminh.stylash.controller.dto.OrderDto;
import com.vuducminh.stylash.controller.dto.UserDto;
import com.vuducminh.stylash.model.Order;
import org.springframework.stereotype.Service;

@Service
public class OrderMapperImpl implements OrderMapper {

    @Override
    public OrderDto toOrderDto(Order order) {
        if (order == null) {
            return null;
        }
        OrderDto.UserDto userDto = new OrderDto.UserDto(order.getUser().getUsername());
        return new OrderDto(order.getId(),
                userDto,
                order.getOrderDate(),
                order.getTotalAmount(),
                order.getPaymentStatus(),
                order.getShippingAddress());
    }
}
