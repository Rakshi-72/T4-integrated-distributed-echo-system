package com.example.t4springboot.service;

import com.example.t4springboot.model.User;
import com.example.t4springboot.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LoadUser implements CommandLineRunner {

    private final UserRepo repo;

    @Override
    public void run(String... args) throws Exception {

        User user = new User();

        user.setUsername("rakshith");
        user.setPassword("123");

        System.out.println(repo.save(user).toString());

    }
}
