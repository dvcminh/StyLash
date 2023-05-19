package com.vuducminh.stylash.service;

import com.vuducminh.stylash.user.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(Integer userId);

    User createUser(User user);
    User updateUser(User user);
    void deleteUser(Integer userId);
}

