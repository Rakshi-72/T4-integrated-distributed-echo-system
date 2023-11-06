package com.example.t4springboot.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class JwtUtils {

    private final UserDetailsService service;

    public String generateToken(String username) {

        return Jwts.builder()
                .setClaims(new HashMap<String, Object>())
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + Duration.ofHours(5).toMillis()))
                .signWith(SignatureAlgorithm.HS256, "SECRET")
                .compact();

    }

    public UserDetails getUserDetails(String token) {
        return service.loadUserByUsername(this.getClaim(Claims::getSubject, token));
    }

    private Claims getAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey("SECRET")
                .parseClaimsJws(token)
                .getBody();
    }

    private <T> T getClaim(Function<Claims, T> resolver, String token) {
        return resolver.apply(getAllClaims(token));
    }

    public void validateToken(String token) {

        boolean valid = getClaim(Claims::getExpiration, token).after(new Date());
        UserDetails details = this.getUserDetails(token);

        SecurityContext context = SecurityContextHolder.getContext();
        if (valid && context.getAuthentication() == null) {
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(details, null, details.getAuthorities());
            context.setAuthentication(auth);
        } else {
            System.out.println("either token or context error");
        }

    }

    public UserDetails getLoggedInUser() {
        return (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }


}
