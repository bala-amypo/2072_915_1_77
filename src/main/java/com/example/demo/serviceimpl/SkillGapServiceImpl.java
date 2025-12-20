package com.example.demo.serviceimpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.SkillGapRecord;
import com.example.demo.repository.SkillGapRecordRepository;
import com.example.demo.service.SkillGapService;

@Service
public class SkillGapServiceImpl implements SkillGapService {

    private final SkillGapRecordRepository repository;

    public SkillGapServiceImpl(SkillGapRecordRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<SkillGapRecord> computeGaps(Long studentId) {
        // Review-1: no calculation required
        return repository.findAll();
    }

    @Override
    public List<SkillGapRecord> getGapsByStudent(Long studentId) {
        return repository.findAll();
    }
}
