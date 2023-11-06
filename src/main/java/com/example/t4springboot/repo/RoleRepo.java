package com.example.t4springboot.repo;

import com.example.t4springboot.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoleRepo extends JpaRepository<Role, Integer> {

}
