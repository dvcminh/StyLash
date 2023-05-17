package com.vuducminh.stylash.repository;

import com.vuducminh.stylash.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    // Additional custom query methods can be added here
}
