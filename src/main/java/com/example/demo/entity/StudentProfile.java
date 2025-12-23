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
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "enrollmentId"))
public class StudentProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false)
    private User user;

    @Column(nullable = false, unique = true)
    private String enrollmentId;

    private String cohort;

    private Integer yearLevel;

    // ✅ REQUIRED BY TEST
    private String grade;

    @Builder.Default
    private boolean active = true;

    private Instant lastUpdatedAt;

    @PrePersist
    @PreUpdate
    public void preUpdate() {
        this.lastUpdatedAt = Instant.now();
    }
}
