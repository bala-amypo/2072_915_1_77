package com.example.demo.serviceimpl;

import java.util.List;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.SkillGapService;

@Service
public class SkillGapServiceImpl implements SkillGapService {

    private final SkillGapRecordRepository gapRepo;
    private final AssessmentResultRepository assessmentRepo;
    private final SkillRepository skillRepo;
    private final StudentProfileRepository studentRepo;

    public SkillGapServiceImpl(
            @Lazy SkillGapRecordRepository gapRepo,
            @Lazy AssessmentResultRepository assessmentRepo,
            @Lazy SkillRepository skillRepo,
            @Lazy StudentProfileRepository studentRepo
    ) {
        this.gapRepo = gapRepo;
        this.assessmentRepo = assessmentRepo;
        this.skillRepo = skillRepo;
        this.studentRepo = studentRepo;
    }

    @Override
    public List<SkillGapRecord> computeGaps(Long studentId) {

        StudentProfile student = studentRepo.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        List<Skill> skills = skillRepo.findByActiveTrue();

        for (Skill skill : skills) {

            List<AssessmentResult> results =
                    assessmentRepo.findByStudentProfileIdAndSkillId(studentId, skill.getId());

            double currentScore = results.isEmpty() ? 0
                    : results.get(results.size() - 1).getScore();

            double targetScore = skill.getMinCompetencyScore();
            double gap = targetScore - currentScore;

            SkillGapRecord record = new SkillGapRecord();
            record.setStudentProfile(student);
            record.setSkill(skill);
            record.setCurrentScore(currentScore);
            record.setTargetScore(targetScore);
            record.setGapScore(gap);

            gapRepo.save(record);
        }

        return gapRepo.findByStudentProfileId(studentId);
    }

    @Override
    public List<SkillGapRecord> getGapsByStudent(Long studentId) {
        return gapRepo.findByStudentProfileId(studentId);
    }
}
