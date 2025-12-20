package com.example.demo.service;

import com.example.demo.entity.SkillGapRecommendation;
import java.util.List;

public interface RecommendationService {

    // generate recommendation for ONE skill
    SkillGapRecommendation computeRecommendationForStudentSkill(
            Long studentProfileId,
            Long skillId
    );

    // generate recommendations for ALL skills
    List<SkillGapRecommendation> computeRecommendationsForStudent(
            Long studentProfileId
    );

    // view recommendations
    List<SkillGapRecommendation> getRecommendationsForStudent(
            Long studentProfileId
    );
}
