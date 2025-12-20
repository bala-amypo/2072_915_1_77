package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.StudentProfile;
import com.example.demo.service.StudentProfileService;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Students")
@RestController
@RequestMapping("/api/students")
public class StudentProfileController {

    private final StudentProfileService studentProfileService;

    // Constructor Injection
    public StudentProfileController(StudentProfileService studentProfileService) {
        this.studentProfileService = studentProfileService;
    }

    // 1️⃣ POST /api/students  → Create profile
    @PostMapping
    public StudentProfile createProfile(@RequestBody StudentProfile profile) {
        return studentProfileService.createOrUpdateProfile(profile);
    }

    // 2️⃣ GET /api/students/{id}  → Get profile by ID
    @GetMapping("/{id}")
    public StudentProfile getProfileById(@PathVariable Long id) {
        return studentProfileService.getProfileById(id);
    }

    // 3️⃣ GET /api/students/enrollment/{enrollmentId} → Get by enrollment ID
    @GetMapping("/enrollment/{enrollmentId}")
    public StudentProfile getByEnrollmentId(@PathVariable String enrollmentId) {
        return studentProfileService.getProfileByEnrollmentId(enrollmentId);
    }

    // 4️⃣ GET /api/students  → List all profiles
    @GetMapping
    public List<StudentProfile> getAllProfiles() {
        return studentProfileService.getAllProfiles();
    }
}
