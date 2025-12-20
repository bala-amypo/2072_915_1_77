package com.example.demo.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entity.AssessmentResult;
import com.example.demo.service.AssessmentService;
@RestController
@RequestMapping("/assessments")
public class AssessmentController {
    private final AssessmentService service;
    public AssessmentController(AssessmentService service) {
        this.service = service;
    }
    @PostMapping
    public ResponseEntity<AssessmentResult> record(@RequestBody AssessmentResult result) {
        return ResponseEntity.ok(service.recordAssessment(result));
    }
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<AssessmentResult>> getByStudent(
            @PathVariable Long studentId) {
        return ResponseEntity.ok(service.getResultsByStudent(studentId));
    }
    @GetMapping("/student/{studentId}/skill/{skillId}")
    public ResponseEntity<List<AssessmentResult>> getByStudentAndSkill(
            @PathVariable Long studentId,
            @PathVariable Long skillId) {
        return ResponseEntity.ok(
                service.getResultsByStudentAndSkill(studentId, skillId));
    }
}
