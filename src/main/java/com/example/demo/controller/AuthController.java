package com.example.demo.controller;

import com.example.demo.dto.AuthRequest;
import com.example.demo.entity.User;
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

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        // ❌ DO NOT SET ROLE (not in your entity)

        authService.register(user);

        return ResponseEntity.ok("User registered successfully");
    }

    // ✅ LOGIN (matches AuthService exactly)
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthRequest request) {

        authService.login(request.getEmail());

        return ResponseEntity.ok("Login successful");
    }
}
