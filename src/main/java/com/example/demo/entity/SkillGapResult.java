package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
public class SkillGapResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "student_profile_id", nullable = false)
    private StudentProfile studentProfile;
    @ManyToOne
    @JoinColumn(name = "skill_id", nullable = false)
    private Skill skill;

    private Double currentScore;
    private Double targetScore;
    private Double gapScore;

    private Instant calculatedAt;
    @PrePersist
    public void onCreate() {
        this.calculatedAt = Instant.now();
    }

    public Long getId() {
        return id;
    }

    public StudentProfile getStudentProfile() {
        return studentProfile;
    }
    public void setStudentProfile(StudentProfile studentProfile) {
        this.studentProfile = studentProfile;
    }

    public Skill getSkill() {
        return skill;
    }
    public void setSkill(Skill skill) {
        this.skill = skill;
    }
    public Double getCurrentScore() {
        return currentScore;
    }
    public void setCurrentScore(Double currentScore) {
        this.currentScore = currentScore;
    }
    public Double getTargetScore() {
        return targetScore;
    }
    public void setTargetScore(Double targetScore) {
        this.targetScore = targetScore;
    }
    public Double getGapScore() {
        return gapScore;
    }
    public void setGapScore(Double gapScore) {
        this.gapScore = gapScore;
    }
    public Instant getCalculatedAt() {
        return calculatedAt;
    }
}
