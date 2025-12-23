package com.example.demo.serviceimpl;

import com.example.demo.dto.AuthRequest;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.AuthService;

import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class UserServiceImpl implements AuthService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

   
    @Override
    public User register(AuthRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword()); 
        user.setFullName("DEFAULT_USER");
        user.setRole("STUDENT");
        user.setCreatedAt(Instant.now());

        return userRepository.save(user);
    }
    @Override
    public User login(String email) {

        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new IllegalArgumentException("Invalid email")
                );
    }
}
