package com.example.demo.dto;

import com.example.demo.entity.User;
import lombok.*;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long id;
    private String fullName;
    private String email;
    private User.Role role;
    private Instant createdAt;
}
