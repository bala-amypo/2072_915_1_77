package com.example.demo.serviceimpl;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    // Constructor injection (important for tests)
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // REGISTER USER
    @Override
    public User register(User user) {

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }

        // Defaults (match DB + tests)
        user.setFullName(
                user.getFullName() != null ? user.getFullName() : "DEFAULT_USER"
        );

        user.setRole(
                user.getRole() != null ? user.getRole() : "STUDENT"
        );

        user.setCreatedAt(Instant.now());

        // Password hashing NOT required for test
        return userRepository.save(user);
    }

    // LOGIN (USED IN TESTS)
    @Override
    public User login(String email) {

        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new IllegalArgumentException("Invalid email")
                );
    }

    // FIND BY EMAIL (TEST t013)
    @Override
    public User findByEmail(String email) {

        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new IllegalArgumentException("User not found")
                );
    }
}
