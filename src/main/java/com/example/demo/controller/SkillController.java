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
    @PostMapping
    public Skill createSkill(@RequestBody Skill skill) {
        return skillService.createSkill(skill);
    }
    @PutMapping("/{id}")
    public Skill updateSkill(@PathVariable Long id,
                             @RequestBody Skill skill) {
        return skillService.updateSkill(id, skill);
    }
    @GetMapping("/{id}")
    public Skill getSkillById(@PathVariable Long id) {
        return skillService.getById(id);
    }
    @GetMapping
    public List<Skill> getAllSkills() {
        return skillService.getAllSkills();
    }
    @PutMapping("/{id}/deactivate")
    public String deactivateSkill(@PathVariable Long id) {
        skillService.deactivateSkill(id);
        return "Skill deactivated successfully";
    }
}
