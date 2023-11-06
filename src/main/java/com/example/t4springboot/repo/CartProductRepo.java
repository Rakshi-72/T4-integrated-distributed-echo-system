package com.example.t4springboot.repo;


import com.example.t4springboot.model.CartProduct;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * CartProductRepo
 */
public interface CartProductRepo extends JpaRepository<CartProduct, Integer> {

}