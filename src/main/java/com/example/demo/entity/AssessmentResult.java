package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AssessmentResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String assessmentId;

    private Double score;

    private Double maxScore = 100.0;

    private Instant attemptedAt = Instant.now();

    @ManyToOne
    private StudentProfile studentProfile;

    @ManyToOne
    private Skill skill;
}
