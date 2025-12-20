package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.*;

import com.example.demo.config.JwtUtil;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    // Constructor Injection
    public AuthController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    // POST /auth/register
    @PostMapping("/register")
    public User register(@RequestBody Map<String, String> body) {

        User user = User.builder()
                .email(body.get("email"))
                .password(body.get("password"))
                .role(User.Role.STUDENT)
                .build();

        return userService.register(user);
    }

    // POST /auth/login
    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Map<String, String> body) {

        User user = userService.findByEmail(body.get("email"));

        String token = jwtUtil.generateToken(user);

        Map<String, String> response = new HashMap<>();
        response.put("token", token);

        return response;
    }
}
