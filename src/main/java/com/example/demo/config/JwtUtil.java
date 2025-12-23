package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfig {

    /**
     * JwtUtil bean for Spring runtime
     * Matches TestNG usage exactly:
     *   new JwtUtil("01234567890123456789012345678901", 3600000)
     */
    @Bean
    public JwtUtil jwtUtil() {
        return new JwtUtil(
                "01234567890123456789012345678901", // 32+ characters
                3600000 // 1 hour validity
        );
    }
}
