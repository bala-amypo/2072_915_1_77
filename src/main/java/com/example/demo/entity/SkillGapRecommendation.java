package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.SkillGapRecommendation;
import com.example.demo.service.RecommendationService;

@RestController
@RequestMapping("/api/recommendations")
public class RecommendationController {

    private final RecommendationService recommendationService;

    public RecommendationController(RecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }

    // 1️⃣ Recommendation for ONE skill of a student
    // GET /api/recommendations/student/{studentId}/skill/{skillId}
    @GetMapping("/student/{studentId}/skill/{skillId}")
    public SkillGapRecommendation getRecommendationForStudentSkill(
            @PathVariable Long studentId,
            @PathVariable Long skillId
    ) {
        return recommendationService.computeRecommendationForStudentSkill(studentId, skillId);
    }

    // 2️⃣ Recommendations for ALL skills of a student
    // GET /api/recommendations/student/{studentId}
    @GetMapping("/student/{studentId}")
    public List<SkillGapRecommendation> getRecommendationsForStudent(
            @PathVariable Long studentId
    ) {
        return recommendationService.computeRecommendationsForStudent(studentId);
    }
}
