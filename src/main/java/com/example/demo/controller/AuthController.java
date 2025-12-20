package com.example.demo.controller;

import com.example.demo.dto.AuthRequest;
import com.example.demo.entity.User;
import com.example.demo.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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

        authService.register(user);

        return ResponseEntity.ok("User registered successfully");
    }

    // ✅ LOGIN (required by spec, simple version)
    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody AuthRequest request) {

        User user = authService.login(request.getEmail());
        return ResponseEntity.ok(user);
    }
}
