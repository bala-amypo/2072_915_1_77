package com.example.demo.serviceimpl;

import com.example.demo.entity.Skill;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.SkillRepository;
import com.example.demo.service.SkillService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillServiceImpl implements SkillService {

    private final SkillRepository repository;

    public SkillServiceImpl(SkillRepository repository) {
        this.repository = repository;
    }

    @Override
    public Skill createSkill(Skill skill) {

        if (repository.findByCode(skill.getCode()).isPresent()) {
            throw new IllegalArgumentException("Skill code must be unique");
        }

        if (skill.getMinCompetencyScore() < 0 ||
            skill.getMinCompetencyScore() > 100) {
            throw new IllegalArgumentException("Invalid competency score");
        }

        return repository.save(skill);
    }

    @Override
    public Skill updateSkill(Long id, Skill skill) {
        Skill existing = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Skill not found"));

        existing.setName(skill.getName());
        existing.setCategory(skill.getCategory());
        existing.setDescription(skill.getDescription());
        existing.setMinCompetencyScore(skill.getMinCompetencyScore());

        return repository.save(existing);
    }

    @Override
    public Skill getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Skill not found"));
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
    @Override
public Skill deactivateSkill(Long id) {
    Skill skill = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Skill not found"));

    skill.setActive(false);
    return repository.save(skill);
}

}
