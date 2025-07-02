package com.lms.enrollmentservice.service;

import com.lms.enrollmentservice.model.Enrollment;
import com.lms.enrollmentservice.repository.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class EnrollmentService {
    @Autowired
    private EnrollmentRepository enrollmentRepository;

    public Enrollment enroll(Enrollment enrollment) {
        enrollment.setEnrolledAt(Instant.now());
        return enrollmentRepository.save(enrollment);
    }

    public List<Enrollment> getEnrollmentsByUserId(Long userId) {
        return enrollmentRepository.findByUserId(userId);
    }

    public Optional<Enrollment> getEnrollmentById(Long id) {
        return enrollmentRepository.findById(id);
    }
} 