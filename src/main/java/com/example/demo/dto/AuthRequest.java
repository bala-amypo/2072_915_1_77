package com.example.demo.dto;

import com.example.demo.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthRequest {

    // Used for BOTH register & login
    private String fullName;   // used only for register
    private String email;
    private String password;
    private User.Role role;    // optional (defaults to STUDENT)
}
