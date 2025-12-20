package com.example.demo.dto;

public class AuthResponse {

    private String message;
    private Long userId;
    private String role;

    public AuthResponse(String message, Long userId, String role) {
        this.message = message;
        this.userId = userId;
        this.role = role;
    }

    public String getMessage() { return message; }
    public Long getUserId() { return userId; }
    public String getRole() { return role; }
}
