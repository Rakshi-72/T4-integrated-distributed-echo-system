package com.example.t4springboot.repo;

import java.util.Optional;

import com.example.t4springboot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepo extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
}
