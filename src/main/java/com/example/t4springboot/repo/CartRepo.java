package com.example.t4springboot.repo;

import java.util.Optional;

import com.example.t4springboot.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CartRepo extends JpaRepository<Cart, Integer> {
    Optional<Cart> findByUserUserId(Integer userId);
}
