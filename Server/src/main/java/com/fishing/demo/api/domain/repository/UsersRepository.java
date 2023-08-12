package com.fishing.demo.api.domain.repository;

import com.fishing.demo.api.domain.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long> {
    // Define the method to find a user by userId
    Optional<Users> findByUserId(String userId);
}
