package com.example.demo.serviceimpl;

import org.springframework.stereotype.Service;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.AuthService;

@Service
public class UserServiceImpl implements AuthService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void register(AuthRequest request) {

        User user = new User();
        user.setFullName(request.getFullName()); // 🔥 FIX
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setRole("STUDENT");

        userRepository.save(user);
    }

    @Override
    public AuthResponse login(AuthRequest request) {
        // Review-1: no JWT, no validation
        return new AuthResponse("Login successful");
    }
}
