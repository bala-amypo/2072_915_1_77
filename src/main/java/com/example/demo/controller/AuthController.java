package com.example.demo.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.User;
import com.example.demo.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // REGISTER
   @PostMapping("/auth/register")
public ResponseEntity<String> register(@RequestBody AuthRequest request) {
    authService.register(request);
    return ResponseEntity.ok("User registered successfully");
}


    // LOGIN
    @PostMapping("/login")
    public User login(@RequestBody Map<String, String> body) {
        return authService.login(body.get("email"));
    }
}
