package com.example.demo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.AssessmentResult;

public interface AssessmentResultRepository extends JpaRepository<AssessmentResult, Long> {
    List<AssessmentResult> findByStudentProfileId(Long studentProfileId);
    List<AssessmentResult> findByStudentProfileIdAndSkillId(Long studentProfileId, Long skillId);
}
