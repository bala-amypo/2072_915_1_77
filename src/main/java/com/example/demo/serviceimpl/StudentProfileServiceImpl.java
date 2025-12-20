package com.example.demo.serviceimpl;

import java.util.List;
import org.springframework.stereotype.Service;
import com.example.demo.entity.StudentProfile;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.StudentProfileRepository;
import com.example.demo.service.StudentProfileService;

@Service
public class StudentProfileServiceImpl implements StudentProfileService {

    private final StudentProfileRepository repository;

    public StudentProfileServiceImpl(StudentProfileRepository repository) {
        this.repository = repository;
    }
    @Override
    public StudentProfile createProfile(StudentProfile profile) {
        if (repository.existsByEnrollmentId(profile.getEnrollmentId())) {
            throw new IllegalArgumentException("Enrollment ID already exists");
        }
        return repository.save(profile);
    }
    @Override
    public StudentProfile getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student profile not found"));
    }
    @Override
    public StudentProfile getByEnrollmentId(String enrollmentId) {
        return repository.findByEnrollmentId(enrollmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student profile not found"));
    }
    @Override
    public List<StudentProfile> getAllProfiles() {
        return repository.findAll();
    }
}
