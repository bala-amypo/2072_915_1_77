package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.AssessmentResult;

public interface AssessmentService {
    AssessmentResult recordAssessment(AssessmentResult result);
    List<AssessmentResult> getResultsByStudent(Long studentId);
    List<AssessmentResult> getResultsByStudentAndSkill(Long studentId, Long skillId);
}
