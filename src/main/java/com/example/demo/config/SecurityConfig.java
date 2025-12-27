package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;


@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, JwtUtil jwtUtil) throws Exception {

        http
            .cors(cors -> {})   // ✅ IMPORTANT for Swagger / proxy
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session ->
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            .addFilterBefore(
                    new JwtAuthenticationFilter(jwtUtil),
                    UsernamePasswordAuthenticationFilter.class
            )

            .authorizeHttpRequests(auth -> auth

                // PUBLIC ENDPOINTS
                .requestMatchers(
                        "/auth/**",
                        "/health",
                        "/swagger-ui/**",
                        "/v3/api-docs/**"
                ).permitAll()

                // STUDENT PROFILE
                .requestMatchers(HttpMethod.POST, "/api/students/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/students/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/students/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/api/students/**").authenticated()

                // SKILLS
               .requestMatchers(HttpMethod.POST, "/api/skills/**").permitAll()
                .requestMatchers(HttpMethod.PUT, "/api/skills/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/skills/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/api/skills/**").authenticated()

                // ASSESSMENTS
                .requestMatchers(HttpMethod.POST, "/api/assessments/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/api/assessments/**").authenticated()

                // SKILL GAP & RECOMMENDATIONS
                .requestMatchers(HttpMethod.GET, "/api/gaps/**").authenticated()
                .requestMatchers(HttpMethod.GET, "/api/recommendations/**").authenticated()

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
        return new JwtUtil(
                "01234567890123456789012345678901",
                3600000
        );
    }
}
