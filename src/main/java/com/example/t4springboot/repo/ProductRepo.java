package com.example.t4springboot.repo;

import java.util.List;

import com.example.t4springboot.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepo extends JpaRepository<Product, Integer> {

    List<Product> findByProductNameContainingIgnoreCaseOrCategoryCategoryNameContainingIgnoreCase(String keyword,
            String keyword2);

}