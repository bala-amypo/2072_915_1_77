package com.example.demo.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entity.SkillGapRecord;
import com.example.demo.service.SkillGapService;

@RestController
@RequestMapping("/gaps")
public class SkillGapController {

    private final SkillGapService service;

    public SkillGapController(SkillGapService service) {
        this.service = service;
    }
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<SkillGapRecord>> getByStudent(
            @PathVariable Long studentId) {
        return ResponseEntity.ok(service.getGapsByStudent(studentId));
    }
}
