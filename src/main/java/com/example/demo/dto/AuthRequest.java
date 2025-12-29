-- Active: 1766461709708@@127.0.0.1@3306
package com.example.demo.dto;

import com.example.demo.entity.User;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthRequest {

    private String fullName;
    private String email;
    private String password;
    private User.Role role;
}
