package com.example.demo.controller;

import com.example.demo.entity.Skill;
import com.example.demo.service.SkillService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/skills")
@Tag(name = "Skills")
public class SkillController {

    private final SkillService service;

    public SkillController(SkillService service) {
        this.service = service;
    }

    // CREATE SKILL – ADMIN ONLY
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Skill create(@RequestBody Skill s) {
        return service.createSkill(s);
    }

    // UPDATE SKILL – ADMIN ONLY
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Skill update(@PathVariable Long id, @RequestBody Skill s) {
        return service.updateSkill(id, s);
    }

    // GET BY ID – ANY AUTHENTICATED USER
    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public Skill get(@PathVariable Long id) {
        return service.getById(id);
    }

    // GET ALL – ANY AUTHENTICATED USER
    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public List<Skill> getAll() {
        return service.getAllSkills();
    }

    // DEACTIVATE – ADMIN ONLY
    @PutMapping("/{id}/deactivate")
    @PreAuthorize("hasRole('ADMIN')")
    public Skill deactivate(@PathVariable Long id) {
        return service.deactivateSkill(id);
    }
}
