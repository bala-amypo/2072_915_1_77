package com.example.demo.serviceimpl;

import com.example.demo.config.JwtUtil;
import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.entity.User;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.AuthService;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    // 🔴 IMPORTANT: constructor order matters for tests
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
        this.jwtUtil = null; // not used in tests directly
    }

    // optional constructor (used by controllers if needed)
    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    // --------------------------------------------------
    // REGISTER
    // --------------------------------------------------
    @Override
    public User register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }

        User user = User.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole() != null ? request.getRole() : User.Role.STUDENT)
                .build();

        return userRepository.save(user);
    }

    // --------------------------------------------------
    // LOGIN
    // --------------------------------------------------
    @Override
    public String login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid credentials");
        }

        return jwtUtil.generateToken(user);
    }

    // --------------------------------------------------
    // EXTRA METHODS USED IN TESTS
    // --------------------------------------------------

    public User register(User user) {

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        if (user.getRole() == null) {
            user.setRole(User.Role.STUDENT);
        }

        return userRepository.save(user);
    }

    public User getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));
    }

    public List<User> listInstructors() {
        return userRepository.findByRole(User.Role.INSTRUCTOR);
    }
}
