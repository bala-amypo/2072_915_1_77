package com.example.demo.controller;

import com.example.demo.entity.Skill;
import com.example.demo.service.SkillService;
import io.swagger.v3.oas.annotations.tags.Tag;
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

    @PostMapping
    public Skill create(@RequestBody Skill s) {
        return service.createSkill(s);
    }

    @PutMapping("/{id}")
    public Skill update(@PathVariable Long id, @RequestBody Skill s) {
        return service.updateSkill(id, s);
    }

    @GetMapping("/{id}")
    public Skill get(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping
    public List<Skill> getAll() {
        return service.getAllSkills();
    }
    @PutMapping("/{id}/deactivate")
public Skill deactivate(@PathVariable Long id) {
    return service.deactivateSkill(id);
}

}
