package com.example.demo.controller;

import com.example.demo.entity.AssessmentResult;
import com.example.demo.service.AssessmentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assessments")
@Tag(name = "Assessments")
public class AssessmentController {

    private final AssessmentService service;

    public AssessmentController(AssessmentService service) {
        this.service = service;
    }

    @PostMapping
    public AssessmentResult record(@RequestBody AssessmentResult r) {
        return service.recordAssessment(r);
    }

    @GetMapping("/student/{studentId}")
    public List<AssessmentResult> getByStudent(@PathVariable Long studentId) {
        return service.getResultsByStudent(studentId);
    }
}
