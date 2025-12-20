package com.example.demo.controller;

import com.example.demo.dto.AuthRequest;
import com.example.demo.service.AuthService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // ✅ REGISTER
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody AuthRequest request) {
        authService.register(request);
        return ResponseEntity.ok("User registered successfully");
    }

    // ✅ LOGIN  (THIS WAS MISSING)
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthRequest request) {
        authService.login(request);
        return ResponseEntity.ok("Login successful");
    }
}
