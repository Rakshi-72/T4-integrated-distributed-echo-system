package com.example.t4springboot.repo;

import com.example.t4springboot.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
