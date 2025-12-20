package com.example.demo.serviceimpl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.RecommendationService;

import org.springframework.stereotype.Service;

import java.time.Instant;
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

        List<AssessmentResult> results =
                assessmentRepo.findByStudentProfileIdAndSkillId(studentProfileId, skillId);

        double avgScore = results.isEmpty()
                ? 0
                : results.stream().mapToDouble(AssessmentResult::getScore).average().orElse(0);

        double gapScore = skill.getMinCompetencyScore() - avgScore;

        SkillGapRecommendation rec = new SkillGapRecommendation();
        rec.setStudentProfile(profile);
        rec.setSkill(skill);
        rec.setGapScore(gapScore);
        rec.setGeneratedAt(Instant.now());

        if (gapScore > 30) {
            rec.setPriority("HIGH");
            rec.setRecommendedAction("Immediate training required");
        } else if (gapScore > 10) {
            rec.setPriority("MEDIUM");
            rec.setRecommendedAction("Practice recommended");
        } else {
            rec.setPriority("LOW");
            rec.setRecommendedAction("Skill adequate");
        }

        return recommendationRepo.save(rec);
    }

    @Override
    public List<SkillGapRecommendation> computeRecommendationsForStudent(
            Long studentProfileId
    ) {
        List<SkillGapRecommendation> list = new ArrayList<>();

        for (Skill skill : skillRepo.findByActiveTrue()) {
            list.add(
                    computeRecommendationForStudentSkill(studentProfileId, skill.getId())
            );
        }

        return list;
    }

    @Override
    public List<SkillGapRecommendation> getRecommendationsForStudent(
            Long studentProfileId
    ) {
        return recommendationRepo.findByStudentProfileId(studentProfileId);
    }
}
