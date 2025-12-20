package com.example.demo.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entity.StudentProfile;
import com.example.demo.service.StudentProfileService;

@RestController
@RequestMapping("/students")
public class StudentProfileController {
    private final StudentProfileService service;
    public StudentProfileController(StudentProfileService service) {
        this.service = service;
    }
    @PostMapping
    public ResponseEntity<StudentProfile> create(@RequestBody StudentProfile profile) {
        return ResponseEntity.ok(service.createProfile(profile));
    }
    @GetMapping("/{id}")
    public ResponseEntity<StudentProfile> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }
    @GetMapping
    public ResponseEntity<List<StudentProfile>> getAll() {
        return ResponseEntity.ok(service.getAllProfiles());
    }
}
