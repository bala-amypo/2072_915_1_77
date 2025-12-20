package com.example.demo.serviceimpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.StudentProfile;
import com.example.demo.entity.User;
import com.example.demo.repository.StudentProfileRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.StudentProfileService;

@Service
public class StudentProfileServiceImpl implements StudentProfileService {

    private final StudentProfileRepository studentProfileRepository;
    private final UserRepository userRepository;

    public StudentProfileServiceImpl(StudentProfileRepository studentProfileRepository,
                                     UserRepository userRepository) {
        this.studentProfileRepository = studentProfileRepository;
        this.userRepository = userRepository;
    }

    @Override
    public StudentProfile createOrUpdateProfile(StudentProfile profile) {

        // 🔑 AUTH → USER mapping (MANDATORY)
        Long userId = profile.getUser().getId();

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        profile.setUser(user);

        // ❌ DO NOT set lastUpdatedAt here
        // Entity @PreUpdate / @PrePersist will handle it

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
