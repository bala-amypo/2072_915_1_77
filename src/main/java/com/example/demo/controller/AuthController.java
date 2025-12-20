package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.service.AuthService;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Auth")
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    // Constructor injection
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // POST /auth/register
    @PostMapping("/register")
    public AuthResponse register(@RequestBody AuthRequest request) {
        authService.register(request);
        return new AuthResponse("User registered successfully");
    }

    // POST /auth/login
    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {
        return new AuthResponse("Login successful");
    }
}
