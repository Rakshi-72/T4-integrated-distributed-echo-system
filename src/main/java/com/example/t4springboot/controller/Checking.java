package com.example.t4springboot.controller;

import com.example.t4springboot.security.JwtUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class Checking {

    private final JwtUtils utils;
    @GetMapping("/checking")
    public String checking() {

        log.info(utils.getLoggedInUser().toString());
        return "hello there";
    }

}
