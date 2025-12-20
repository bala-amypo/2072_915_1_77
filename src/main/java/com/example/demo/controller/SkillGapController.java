package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.SkillGapRecord;
import com.example.demo.service.SkillGapService;

@RestController
@RequestMapping("/api/gaps")
public class SkillGapController {

    private final SkillGapService skillGapService;

    public SkillGapController(SkillGapService skillGapService) {
        this.skillGapService = skillGapService;
    }

    @PostMapping("/compute/{studentId}")
    public List<SkillGapRecord> compute(@PathVariable Long studentId) {
        return skillGapService.computeGaps(studentId);
    }

    @GetMapping("/student/{studentId}")
    public List<SkillGapRecord> getByStudent(@PathVariable Long studentId) {
        return skillGapService.getGapsByStudent(studentId);
    }
}
