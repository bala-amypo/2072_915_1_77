package com.example.demo.serviceimpl;

import java.util.List;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.example.demo.entity.StudentProfile;
import com.example.demo.repository.StudentProfileRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.StudentProfileService;

@Service
public class StudentProfileServiceImpl implements StudentProfileService {

    private final StudentProfileRepository studentProfileRepository;
    private UserRepository userRepository;

    // REQUIRED BY TESTS
    public StudentProfileServiceImpl(
            @Lazy StudentProfileRepository studentProfileRepository) {
        this.studentProfileRepository = studentProfileRepository;
    }

    // keep existing constructor
    public StudentProfileServiceImpl(
            @Lazy StudentProfileRepository studentProfileRepository,
            @Lazy UserRepository userRepository) {
        this.studentProfileRepository = studentProfileRepository;
        this.userRepository = userRepository;
    }

    @Override
    public StudentProfile createOrUpdateProfile(StudentProfile profile) {
        return studentProfileRepository.save(profile);
    }

    @Override
    public StudentProfile getProfileById(Long id) {
        return studentProfileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student profile not found"));
    }

    @Override
    public StudentProfile getProfileByEnrollmentId(String enrollmentId) {
        return studentProfileRepository.findByEnrollmentId(enrollmentId)
                .orElseThrow(() -> new RuntimeException("Student profile not found"));
    }

    @Override
    public List<StudentProfile> getAllProfiles() {
        return studentProfileRepository.findAll();
    }
}
