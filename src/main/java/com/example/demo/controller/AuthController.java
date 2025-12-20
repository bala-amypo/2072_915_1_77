package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.User;
import com.example.demo.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    public AuthController(AuthService authService) {
        this.authService = authServicepackage com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.SkillGapRecommendation;
import com.example.demo.service.RecommendationService;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Recommendations")
@RestController
@RequestMapping("/api/recommendations")
public class RecommendationController {

    private final RecommendationService recommendationService;

    public RecommendationController(RecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }

    // 1️⃣ POST /api/recommendations/generate/{studentId}
    @PostMapping("/generate/{studentId}")
    public List<SkillGapRecommendation> generateRecommendations(
            @PathVariable Long studentId) {

        return recommendationService.getRecommendationsForStudent(studentId);
    }

    // 2️⃣ GET /api/recommendations/student/{studentId}
    @GetMapping("/student/{studentId}")
    public List<SkillGapRecommendation> getRecommendations(
            @PathVariable Long studentId) {

        return recommendationService.getRecommendationsForStudent(studentId);
    }
}
;
    }
    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        return ResponseEntity.ok(authService.register(user));
    }
    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable Long id) {
        return ResponseEntity.ok(authService.getById(id));
    }
}
