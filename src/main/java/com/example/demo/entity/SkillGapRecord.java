package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
public class SkillGapRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private StudentProfile studentProfile;

    @ManyToOne
    private Skill skill;

    private Double currentScore;
    private Double targetScore;
    private Double gapScore;

    private Instant calculatedAt;

    @PrePersist
    public void onCreate() {
        this.calculatedAt = Instant.now();
    }

    // ---------- GETTERS ----------

    public Long getId() {
        return id;
    }

    public StudentProfile getStudentProfile() {
        return studentProfile;
    }

    public Skill getSkill() {
        return skill;
    }

    public Double getCurrentScore() {
        return currentScore;
    }

    public Double getTargetScore() {
        return targetScore;
    }

    public Double getGapScore() {
        return gapScore;
    }

    public Instant getCalculatedAt() {
        return calculatedAt;
    }

    // ---------- SETTERS ----------

    public void setStudentProfile(StudentProfile studentProfile) {
        this.studentProfile = studentProfile;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    public void setCurrentScore(Double currentScore) {
        this.currentScore = currentScore;
    }

    public void setTargetScore(Double targetScore) {
        this.targetScore = targetScore;
    }

    public void setGapScore(Double gapScore) {
        this.gapScore = gapScore;
    }
}
