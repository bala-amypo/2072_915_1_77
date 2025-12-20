package com.example.demo.serviceimpl;

import java.util.List;
import org.springframework.stereotype.Service;
import com.example.demo.entity.Skill;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.SkillRepository;
import com.example.demo.service.SkillService;

@Service
public class SkillServiceImpl implements SkillService {
    private final SkillRepository repository;
    public SkillServiceImpl(SkillRepository repository) {
        this.repository = repository;
    }
    @Override
    public Skill createSkill(Skill skill) {
        if (repository.findBySkillName(skill.getSkillName()).isPresent()) {
            throw new IllegalArgumentException("Skill already exists");
        }
        return repository.save(skill);
    }
    @Override
    public Skill getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Skill not found"));
    }
    @Override
    public List<Skill> getAllSkills() {
        return repository.findAll();
    }
    @Override
    public List<Skill> getActiveSkills() {
        return repository.findByActiveTrue();
    }
    @Override
    public void deactivateSkill(Long id) {
        Skill skill = getById(id);
        skill.setActive(false);
        repository.save(skill);
    }
}
