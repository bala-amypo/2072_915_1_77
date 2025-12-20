package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.AssessmentResult;
import com.example.demo.service.AssessmentService;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Assessments")
@RestController
@RequestMapping("/api/assessments")
public class AssessmentController {

    private final AssessmentService assessmentService;

    public AssessmentController(AssessmentService assessmentService) {
        this.assessmentService = assessmentService;
    }

    // 1️⃣ POST /api/assessments → Record result
    @PostMapping
    public AssessmentResult recordAssessment(@RequestBody AssessmentResult result) {
        return assessmentService.recordAssessment(result);
    }

    // 2️⃣ GET /api/assessments/student/{studentId} → Get results
    @GetMapping("/student/{studentId}")
    public List<AssessmentResult> getResultsByStudent(@PathVariable Long studentId) {
        return assessmentService.getResultsByStudent(studentId);
    }

    // 3️⃣ GET /api/assessments/student/{studentId}/skill/{skillId}
    @GetMapping("/student/{studentId}/skill/{skillId}")
    public List<AssessmentResult> getResultsByStudentAndSkill(
            @PathVariable Long studentId,
            @PathVariable Long skillId) {

        return assessmentService.getResultsByStudentAndSkill(studentId, skillId);
    }
}
