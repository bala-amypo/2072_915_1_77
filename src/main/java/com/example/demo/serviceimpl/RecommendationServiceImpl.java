package com.example.demo.serviceimpl;

import com.example.demo.entity.*;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.*;
import com.example.demo.service.RecommendationService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecommendationServiceImpl implements RecommendationService {

    private final AssessmentResultRepository assessmentRepo;
    private final SkillGapRecommendationRepository recommendationRepo;
    private final StudentProfileRepository profileRepo;
    private final SkillRepository skillRepo;

    public RecommendationServiceImpl(
            AssessmentResultRepository assessmentRepo,
            SkillGapRecommendationRepository recommendationRepo,
            StudentProfileRepository profileRepo,
            SkillRepository skillRepo) {

        this.assessmentRepo = assessmentRepo;
        this.recommendationRepo = recommendationRepo;
        this.profileRepo = profileRepo;
        this.skillRepo = skillRepo;
    }

    @Override
    public SkillGapRecommendation computeRecommendationForStudentSkill(
            Long studentId, Long skillId) {

        StudentProfile profile = profileRepo.findById(studentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Profile not found"));

        Skill skill = skillRepo.findById(skillId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Skill not found"));

        List<AssessmentResult> results =
                assessmentRepo.findByStudentProfileIdAndSkillId(studentId, skillId);

       double currentScore = results.isEmpty()
        ? 0
        : results.stream()
                .mapToDouble(AssessmentResult::getScore)
                .max()
                .orElse(0);

        double targetScore = skill.getMinCompetencyScore();
        double gapScore = targetScore - currentScore;

        String priority =
                gapScore >= 20 ? "HIGH" :
                gapScore >= 10 ? "MEDIUM" : "LOW";

        SkillGapRecommendation recommendation =
                SkillGapRecommendation.builder()
                        .studentProfile(profile)
                        .skill(skill)
                        .gapScore(gapScore)
                        .priority(priority)
                        .recommendedAction(
                                "Revise " + skill.getName() + " fundamentals")
                        .generatedBy("SYSTEM")
                        .build();

        return recommendationRepo.save(recommendation);
    }

    @Override
    public List<SkillGapRecommendation> computeRecommendationsForStudent(
            Long studentId) {

        StudentProfile profile = profileRepo.findById(studentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Profile not found"));

        List<SkillGapRecommendation> recommendations = new ArrayList<>();

        for (Skill skill : skillRepo.findByActiveTrue()) {
            recommendations.add(
                    computeRecommendationForStudentSkill(
                            profile.getId(), skill.getId()));
        }

        return recommendations;
    }

    @Override
    public List<SkillGapRecommendation> getRecommendationsForStudent(
            Long studentId) {

        // ✅ MUST MATCH TEST
        return recommendationRepo.findByStudentOrdered(studentId);
    }
}
