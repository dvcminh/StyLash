package com.vuducminh.stylash.service;

import com.vuducminh.stylash.user.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUsers();
    public Optional<User> getAuthenticatedUser();

    User getUserById(Integer userId);
    User validateAndGetUserByUsername(String username);

    int countManagers();

    Optional<User> getUserByUsername(String username);

    User getUserByEmail(String email);

    User createUser(User user);
    User updateUser(User user);
    void deleteUser(Integer userId);
}

