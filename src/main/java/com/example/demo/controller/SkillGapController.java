package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.SkillGapRecord;
import com.example.demo.service.SkillGapService;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Gaps")
@RestController
@RequestMapping("/api/gaps")
public class SkillGapController {

    private final SkillGapService skillGapService;

    public SkillGapController(SkillGapService skillGapService) {
        this.skillGapService = skillGapService;
    }

    // 1️⃣ POST /api/gaps/compute/{studentId}
    @PostMapping("/compute/{studentId}")
    public List<SkillGapRecord> computeGaps(@PathVariable Long studentId) {
        return skillGapService.computeGaps(studentId);
    }

    // 2️⃣ GET /api/gaps/student/{studentId}
    @GetMapping("/student/{studentId}")
    public List<SkillGapRecord> getGapsByStudent(@PathVariable Long studentId) {
        return skillGapService.getGapsByStudent(studentId);
    }
}
