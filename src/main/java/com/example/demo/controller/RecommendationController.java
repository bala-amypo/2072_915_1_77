package com.example.demo.controller;

import com.example.demo.entity.SkillGapRecommendation;
import com.example.demo.service.RecommendationService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recommendations")
public class RecommendationController {

    private final RecommendationService recommendationService;

    public RecommendationController(RecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }

    /**
     * Generate recommendation for ONE skill of ONE student
     * Example: studentId = 1, skillId = 2
     */
    @PostMapping("/student/{studentId}/skill/{skillId}")
    public SkillGapRecommendation generateForSkill(
            @PathVariable Long studentId,
            @PathVariable Long skillId
    ) {
        return recommendationService.computeRecommendationForStudentSkill(studentId, skillId);
    }

    /**
     * Generate recommendations for ALL skills of a student
     */
    @PostMapping("/student/{studentId}")
    public List<SkillGapRecommendation> generateForStudent(
            @PathVariable Long studentId
    ) {
        return recommendationService.computeRecommendationsForStudent(studentId);
    }

    /**
     * View recommendations of a student
     */
    @GetMapping("/student/{studentId}")
    public List<SkillGapRecommendation> getByStudent(
            @PathVariable Long studentId
    ) {
        return recommendationService.getRecommendationsForStudent(studentId);
    }
}
