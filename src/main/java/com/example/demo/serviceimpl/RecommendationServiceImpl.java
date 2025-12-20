package com.example.demo.serviceimpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.SkillGapRecommendation;
import com.example.demo.repository.SkillGapRecommendationRepository;
import com.example.demo.service.RecommendationService;

@Service
public class RecommendationServiceImpl implements RecommendationService {

    private final SkillGapRecommendationRepository repository;

    public RecommendationServiceImpl(SkillGapRecommendationRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<SkillGapRecommendation> getRecommendationsForStudent(Long studentId) {
        return repository.findByStudentProfileId(studentId);
    }
}
