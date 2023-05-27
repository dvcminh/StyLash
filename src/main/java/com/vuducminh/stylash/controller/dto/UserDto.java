package com.vuducminh.stylash.controller.dto;

import java.time.ZonedDateTime;
import java.util.List;

public record UserDto(Integer id, String email, String firstname, String lastname) {
    public record OrderDto(String id, String orderStatus) {
    }
}