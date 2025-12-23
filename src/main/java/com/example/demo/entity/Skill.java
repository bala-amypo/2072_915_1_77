package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String code;   // REQUIRED BY TESTS

    private String name;

    private String category;

    private String description;

    private Double minCompetencyScore = 0.0;

    private Boolean active = true;
}
