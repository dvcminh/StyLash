package com.vuducminh.stylash.service;

import com.vuducminh.stylash.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(Long userId);
    User createUser(User user);
    User updateUser(User user);
    void deleteUser(Long userId);
}

