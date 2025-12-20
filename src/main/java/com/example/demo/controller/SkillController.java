package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Skill;
import com.example.demo.service.SkillService;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Skills")
@RestController
@RequestMapping("/api/skills")
public class SkillController {

    private final SkillService skillService;

    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    // 1️⃣ POST /api/skills → Create skill
    @PostMapping
    public Skill createSkill(@RequestBody Skill skill) {
        return skillService.createSkill(skill);
    }

    // 2️⃣ PUT /api/skills/{id} → Update skill
    @PutMapping("/{id}")
    public Skill updateSkill(@PathVariable Long id,
                             @RequestBody Skill skill) {
        return skillService.updateSkill(id, skill);
    }

    // 3️⃣ GET /api/skills/{id} → Get skill
    @GetMapping("/{id}")
    public Skill getSkillById(@PathVariable Long id) {
        return skillService.getById(id);
    }

    // 4️⃣ GET /api/skills → List all
    @GetMapping
    public List<Skill> getAllSkills() {
        return skillService.getAllSkills();
    }

    // 5️⃣ PUT /api/skills/{id}/deactivate → Deactivate
    @PutMapping("/{id}/deactivate")
    public String deactivateSkill(@PathVariable Long id) {
        skillService.deactivateSkill(id);
        return "Skill deactivated successfully";
    }
}
