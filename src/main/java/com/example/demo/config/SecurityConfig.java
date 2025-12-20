package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityConfig {

    @Bean
    public Object securityFilterChain() {
        // Dummy bean to bypass Spring Security for Review-1
        return new Object();
    }
}
