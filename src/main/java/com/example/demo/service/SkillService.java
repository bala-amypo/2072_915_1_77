package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.Skill;

public interface SkillService {

    Skill createSkill(Skill skill);
    Skill getById(Long id);
    List<Skill> getAllSkills();
    List<Skill> getActiveSkills();
    void deactivateSkill(Long id);
}
