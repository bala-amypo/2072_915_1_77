package com.example.demo.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entity.Skill;
import com.example.demo.service.SkillService;

@RestController
@RequestMapping("/skills")
public class SkillController {
    private final SkillService service;
    public SkillController(SkillService service) {
        this.service = service;
    }
    @PostMapping
    public ResponseEntity<Skill> create(@RequestBody Skill skill) {
        return ResponseEntity.ok(service.createSkill(skill));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Skill> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }
    @GetMapping
    public ResponseEntity<List<Skill>> getAll() {
        return ResponseEntity.ok(service.getAllSkills());
    }
    @PutMapping("/{id}/deactivate")
    public ResponseEntity<String> deactivate(@PathVariable Long id) {
        service.deactivateSkill(id);
        return ResponseEntity.ok("Skill deactivated");
    }
}
