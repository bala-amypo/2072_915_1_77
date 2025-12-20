package com.example.demo.serviceimpl;

import java.util.Optional;

import org.springframework.stereotype.Service;

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
    public User register(User user) {

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }

        return userRepository.save(user);
    }

    @Override
    public User login(String email) {

        Optional<User> user = userRepository.findByEmail(email);

        return user.orElseThrow(() ->
                new RuntimeException("User not found"));
    }
}
