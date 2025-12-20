package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.SkillGapRecord;

public interface SkillGapRecordRepository extends JpaRepository<SkillGapRecord, Long> {

    List<SkillGapRecord> findById(Long studentId);
}
package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.SkillGapRecord;

public interface SkillGapRecordRepository
        extends JpaRepository<SkillGapRecord, Long> {

    // ✅ Custom finder (no clash)
    List<SkillGapRecord> findByGapScoreGreaterThan(Double gapScore);
}
