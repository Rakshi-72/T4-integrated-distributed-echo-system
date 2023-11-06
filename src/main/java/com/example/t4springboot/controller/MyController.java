package com.example.t4springboot.controller;

import com.example.t4springboot.model.Login;
import com.example.t4springboot.security.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/public")
public class MyController {

    private final AuthenticationManager manager;
    private final UserDetailsService service;
    private final JwtUtils utils;

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Login login) {
        try {
            manager.authenticate(new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword()));
        } catch (Exception exception) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        String token = utils.generateToken(login.getUsername());
        return ResponseEntity.ok(token);
    }

}
