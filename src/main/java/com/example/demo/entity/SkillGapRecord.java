package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
public class SkillGapRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_profile_id")
    private StudentProfile studentProfile;

    @ManyToOne
    @JoinColumn(name = "skill_id")
    private Skill skill;

    private Double currentScore;
    private Double targetScore;
    private Double gapScore;
    private Instant calculatedAt;

    @PrePersist
    public void onCreate() {
        this.calculatedAt = Instant.now();
    }

    
}
