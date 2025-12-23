package com.example.demo.serviceimpl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.RecommendationService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecommendationServiceImpl implements RecommendationService {

    private final AssessmentResultRepository assessmentRepo;
    private final SkillGapRecommendationRepository recommendationRepo;
    private final StudentProfileRepository studentProfileRepo;
    private final SkillRepository skillRepo;

    public RecommendationServiceImpl(
            AssessmentResultRepository assessmentRepo,
            SkillGapRecommendationRepository recommendationRepo,
            StudentProfileRepository studentProfileRepo,
            SkillRepository skillRepo
    ) {
        this.assessmentRepo = assessmentRepo;
        this.recommendationRepo = recommendationRepo;
        this.studentProfileRepo = studentProfileRepo;
        this.skillRepo = skillRepo;
    }

    @Override
    public SkillGapRecommendation computeRecommendationForStudentSkill(
            Long studentProfileId,
            Long skillId
    ) {
        StudentProfile profile = studentProfileRepo.findById(studentProfileId)
                .orElseThrow(() -> new RuntimeException("Student profile not found"));

        Skill skill = skillRepo.findById(skillId)
                .orElseThrow(() -> new RuntimeException("Skill not found"));

        double avgScore = assessmentRepo
                .findByStudentProfileIdAndSkillId(studentProfileId, skillId)
                .stream()
                .mapToDouble(AssessmentResult::getScore)
                .average()
                .orElse(0.0);

        double gap = skill.getMinCompetencyScore() - avgScore;

        SkillGapRecommendation rec = new SkillGapRecommendation();
        rec.setStudentProfile(profile);
        rec.setSkill(skill);
        rec.setGapScore(gap);
        rec.setPriority(gap > 20 ? "HIGH" : "LOW");
       rec.setRecommendedAction("Practice more on " + skill.getName());


        // ❌ DO NOT SET generatedAt (handled by @PrePersist)

        return recommendationRepo.save(rec);
    }

    @Override
    public List<SkillGapRecommendation> computeRecommendationsForStudent(
            Long studentProfileId
    ) {
        studentProfileRepo.findById(studentProfileId)
                .orElseThrow(() -> new RuntimeException("Student profile not found"));

        List<Skill> skills = skillRepo.findByActiveTrue();
        List<SkillGapRecommendation> list = new ArrayList<>();

        for (Skill skill : skills) {
            list.add(
                computeRecommendationForStudentSkill(studentProfileId, skill.getId())
            );
        }
        return list;
    }
}
