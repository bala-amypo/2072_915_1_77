package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
public class SkillGapRecommendation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_profile_id", nullable = false)
    private StudentProfile studentProfile;

    @ManyToOne
    @JoinColumn(name = "skill_id", nullable = false)
    private Skill skill;

    private String recommendedAction;
    private String priority;
    private Double gapScore;

    private Instant generatedAt;

    @PrePersist
    public void onCreate() {
        this.generatedAt = Instant.now();
    }

  
    public Long getId() { return id; }

    public StudentProfile getStudentProfile() { return studentProfile; }
    public void setStudentProfile(StudentProfile studentProfile) {
        this.studentProfile = studentProfile;
    }

    public Skill getSkill() { return skill; }
    public void setSkill(Skill skill) { this.skill = skill; }

    public String getRecommendedAction() { return recommendedAction; }
    public void setRecommendedAction(String recommendedAction) {
        this.recommendedAction = recommendedAction;
    }

    public String getPriority() { return priority; }
    public void setPriority(String priority) { this.priority = priority; }

    public Double getGapScore() { return gapScore; }
    public void setGapScore(Double gapScore) { this.gapScore = gapScore; }

    public Instant getGeneratedAt() { return generatedAt; }
}
