package com.example.demo.repository;

import com.example.demo.entity.AssessmentResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.Instant;
import java.util.List;

public interface AssessmentResultRepository extends JpaRepository<AssessmentResult, Long> {

    List<AssessmentResult> findByStudentProfileId(Long studentProfileId);

    List<AssessmentResult> findByStudentProfileIdAndSkillId(Long studentProfileId, Long skillId);

    @Query("select avg(a.score) from AssessmentResult a " +
           "where a.studentProfile.cohort = :cohort and a.skill.id = :skillId")
    Double avgScoreByCohortAndSkill(String cohort, Long skillId);

    @Query("select a from AssessmentResult a " +
           "where a.studentProfile.id = :studentId order by a.attemptedAt desc")
    List<AssessmentResult> findRecentByStudent(Long studentId);

    @Query("select a from AssessmentResult a " +
           "where a.studentProfile.id = :studentId and a.attemptedAt between :from and :to")
    List<AssessmentResult> findResultsForStudentBetween(
            Long studentId, Instant from, Instant to);
}
