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
    @GetMapping("/student/{studentId}/skill/{skillId}")
    public SkillGapRecommendation getRecommendationForStudentSkill(
            @PathVariable Long studentId,
            @PathVariable Long skillId) {

        return recommendationService.computeRecommendationForStudentSkill(studentId, skillId);
    }
    @GetMapping("/student/{studentId}")
    public List<SkillGapRecommendation> getRecommendationsForStudent(
            @PathVariable Long studentId) {

        return recommendationService.computeRecommendationsForStudent(studentId);
    }
}
