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
    @PostMapping("/register")
    public User register(@RequestBody Map<String, String> body) {

        User user = new User();
        user.setEmail(body.get("email"));
        user.setPassword(body.get("password"));

        return authService.register(user);
    }

    // LOGIN
    @PostMapping("/login")
    public User login(@RequestBody Map<String, String> body) {
        return authService.login(body.get("email"));
    }
}
