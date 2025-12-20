package com.example.demo.serviceimpl;

import java.util.List;
import org.springframework.stereotype.Service;
import com.example.demo.entity.AssessmentResult;
import com.example.demo.repository.AssessmentResultRepository;
import com.example.demo.service.AssessmentService;

@Service
public class AssessmentServiceImpl implements AssessmentService {
    private final AssessmentResultRepository repository;
    public AssessmentServiceImpl(AssessmentResultRepository repository) {
        this.repository = repository;
    }
    @Override
    public AssessmentResult recordAssessment(AssessmentResult result) {
        if (result.getScore() < 0 || result.getScore() > result.getMaxScore()) {
            throw new IllegalArgumentException("Score must be between 0 and 100");
        }
        return repository.save(result);
    }
    @Override
    public List<AssessmentResult> getResultsByStudent(Long studentId) {
        return repository.findByStudentProfileId(studentId);
    }
    @Override
    public List<AssessmentResult> getResultsByStudentAndSkill(Long studentId, Long skillId) {
        return repository.findByStudentProfileIdAndSkillId(studentId, skillId);
    }
}
