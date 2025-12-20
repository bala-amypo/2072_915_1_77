package com.example.demo.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    // ✅ REGISTER
    @PostMapping("/register")
    public User register(@RequestBody Map<String, String> body) {

        User user = new User();
        user.setEmail(body.get("email"));
        user.setPassword(body.get("password"));
        user.setRole(User.Role.STUDENT);

        return userService.register(user);
    }

    // ✅ LOGIN (simple lookup, no JWT)
    @PostMapping("/login")
    public User login(@RequestBody Map<String, String> body) {
        return userService.findByEmail(body.get("email"));
    }
}
