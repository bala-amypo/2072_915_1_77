package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SkillGapRecommendation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private StudentProfile studentProfile;

    @ManyToOne(optional = false)
    private Skill skill;

    private String recommendedAction;

    private String priority;

    private Double gapScore;

    private String generatedBy;

    @Builder.Default
    private Instant generatedAt = Instant.now();
}
