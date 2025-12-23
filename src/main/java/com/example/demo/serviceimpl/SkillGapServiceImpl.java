package com.example.demo.serviceimpl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.SkillGapService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SkillGapServiceImpl implements SkillGapService {

    private final SkillGapRecordRepository recordRepository;
    private final SkillRepository skillRepository;
    private final AssessmentResultRepository assessmentRepository;

    public SkillGapServiceImpl(SkillGapRecordRepository recordRepository,
                               SkillRepository skillRepository,
                               AssessmentResultRepository assessmentRepository) {
        this.recordRepository = recordRepository;
        this.skillRepository = skillRepository;
        this.assessmentRepository = assessmentRepository;
    }

    @Override
    public List<SkillGapRecord> computeGaps(Long studentProfileId) {

        List<SkillGapRecord> records = new ArrayList<>();

        for (Skill skill : skillRepository.findByActiveTrue()) {

            List<AssessmentResult> results =
                    assessmentRepository.findByStudentProfileIdAndSkillId(
                            studentProfileId, skill.getId());

            double currentScore =
                    results.isEmpty() ? 0 : results.get(0).getScore();

            double targetScore = skill.getMinCompetencyScore();
            double gapScore = targetScore - currentScore;

            SkillGapRecord record = SkillGapRecord.builder()
                    .studentProfile(null)
                    .skill(skill)
                    .currentScore(currentScore)
                    .targetScore(targetScore)
                    .gapScore(gapScore)
                    .build();

            records.add(recordRepository.save(record));
        }

        return records;
    }

    @Override
    public List<SkillGapRecord> getGapsByStudent(Long studentId) {
        return recordRepository.findByStudentProfileId(studentId);
    }
}
