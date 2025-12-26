package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

@Bean
public SecurityFilterChain filterChain(HttpSecurity http, JwtUtil jwtUtil) throws Exception {

    http
        .csrf(csrf -> csrf.disable())
        .addFilterBefore(new JwtAuthenticationFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class)   
        .authorizeHttpRequests(auth -> auth

            .requestMatchers("/auth/**", "/health", "/swagger-ui/**", "/v3/api-docs/**").permitAll()

            .requestMatchers("/api/students/**").hasRole("ADMIN")
            .requestMatchers("/api/skills/**").hasRole("ADMIN")
            .requestMatchers("/api/assessments/**").hasRole("ADMIN")
            .requestMatchers("/api/gaps/**").hasRole("ADMIN")
            .requestMatchers("/api/recommendations/**").hasRole("ADMIN")

            .anyRequest().authenticated()
        );

    return http.build();
}

@Bean
public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
}

@Bean
public JwtUtil jwtUtil() {
    return new JwtUtil("01234567890123456789012345678901", 3600000);
}
}
