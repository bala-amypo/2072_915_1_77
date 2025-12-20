package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "users")
public class User {

    public enum Role {
        STUDENT,
        INSTRUCTOR,
        ADMIN
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role = Role.STUDENT;

    private Instant createdAt;

    @PrePersist
    public void onCreate() {
        this.createdAt = Instant.now();
    }

    // ===== BUILDER =====
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final User u = new User();

        public Builder id(Long id) {
            u.id = id;
            return this;
        }

        public Builder fullName(String fullName) {
            u.fullName = fullName;
            return this;
        }

        public Builder email(String email) {
            u.email = email;
            return this;
        }

        public Builder password(String password) {
            u.password = password;
            return this;
        }

        public Builder role(Role role) {
            u.role = role;
            return this;
        }

        public User build() {
            return u;
        }
    }

    // ===== GETTERS & SETTERS =====
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }

    public Instant getCreatedAt() { return createdAt; }
}
