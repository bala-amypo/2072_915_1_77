package com.example.demo.service;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.entity.User;

public interface AuthService {

    /**
     * Registers a new user.
     * - Email must be unique
     * - Password must be hashed
     * - Role defaults to STUDENT if not provided
     */
    User register(RegisterRequest request);

    /**
     * Authenticates user and returns JWT token.
     * - Throws exception on invalid credentials
     */
    String login(LoginRequest request);
}
