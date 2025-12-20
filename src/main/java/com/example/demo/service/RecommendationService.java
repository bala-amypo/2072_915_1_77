package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.SkillGapRecommendation;
public interface RecommendationService {
    List<SkillGapRecommendation> getRecommendationsForStudent(Long studentId);
}
