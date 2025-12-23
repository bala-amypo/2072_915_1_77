package com.example.demo.serviceimpl;

import com.example.demo.dto.AuthRequest;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.AuthService;

import org.springframework.stereotype.Service;
import java.util.List;


import java.time.Instant;

@Service
public class UserServiceImpl implements AuthService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public User getById(Long id) {
    return userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found"));
}

public List<User> listInstructors() {
    return userRepository.findByRole(User.Role.INSTRUCTOR);
}


    @Override
    public User register(AuthRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }

        User user = User.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .fullName("DEFAULT_USER")
                .role(User.Role.STUDENT)
                .createdAt(Instant.now())
                .build();

        return userRepository.save(user);
    }

    @Override
    public User login(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Invalid email"));
    }
}
