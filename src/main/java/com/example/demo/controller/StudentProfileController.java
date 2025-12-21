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
    public StudentProfileController(StudentProfileService studentProfileService) {
        this.studentProfileService = studentProfileService;
    }

    @PostMapping
    public StudentProfile createProfile(@RequestBody StudentProfile profile) {
        return studentProfileService.createOrUpdateProfile(profile);
    }
    @GetMapping("/{id}")
    public StudentProfile getProfileById(@PathVariable Long id) {
        return studentProfileService.getProfileById(id);
    }
    @GetMapping("/enrollment/{enrollmentId}")
    public StudentProfile getByEnrollmentId(@PathVariable String enrollmentId) {
        return studentProfileService.getProfileByEnrollmentId(enrollmentId);
    }
    @GetMapping
    public List<StudentProfile> getAllProfiles() {
        return studentProfileService.getAllProfiles();
    }
}
