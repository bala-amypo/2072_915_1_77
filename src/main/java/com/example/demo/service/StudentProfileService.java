package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.StudentProfile;

public interface StudentProfileService {

    StudentProfile createProfile(StudentProfile profile);
    StudentProfile getById(Long id);
    StudentProfile getByEnrollmentId(String enrollmentId);
    List<StudentProfile> getAllProfiles();
}
