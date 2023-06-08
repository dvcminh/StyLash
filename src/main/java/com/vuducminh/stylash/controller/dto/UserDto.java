package com.vuducminh.stylash.controller.dto;

import java.time.ZonedDateTime;
import java.util.List;

public record UserDto(Integer id, String email, String firstname, String lastname, String phoneNumber, String address) {
    public record OrderDto(String id, String orderStatus) {
    }
}