package com.example.t4springboot.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class MySecurity {

    private final ApiEntryPoint entryPoint;
    private final MyFilter filter;
    private final MyAccessDeniedHandler handler;

    @Bean
    SecurityFilterChain getConfig(HttpSecurity security) throws Exception{

        security.csrf().disable().cors().disable();

        security.httpBasic().disable().formLogin().disable();

        security.authorizeHttpRequests()
                .antMatchers("/api/public/**").permitAll()
                .antMatchers("/api/auth/consumer/**").hasRole("CONSUMER")
                .antMatchers("/api/auth/seller/**").hasRole("SELLER")
                .anyRequest()
                .authenticated();

        security.exceptionHandling().accessDeniedHandler(handler).authenticationEntryPoint(entryPoint);

        security.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        security.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);

        return security.build();

    }

}
