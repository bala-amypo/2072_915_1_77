package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.SkillGapRecommendation;

public interface RecommendationService {

    SkillGapRecommendation computeRecommendationForStudentSkill(Long studentId, Long skillId);

    List<SkillGapRecommendation> computeRecommendationsForStudent(Long studentId);
}
