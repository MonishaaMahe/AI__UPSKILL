package com.lms.progressservice.service;

import com.lms.progressservice.entity.AssignmentSubmission;
import com.lms.progressservice.repository.AssignmentSubmissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class AssignmentSubmissionService {
    private final AssignmentSubmissionRepository assignmentSubmissionRepository;

    @Async
    @Transactional
    public CompletableFuture<AssignmentSubmission> submitAssignment(Long studentId, Long courseId, Long assignmentId, Double score, String feedback) {
        AssignmentSubmission submission = new AssignmentSubmission();
        submission.setStudentId(studentId);
        submission.setCourseId(courseId);
        submission.setAssignmentId(assignmentId);
        submission.setScore(score);
        submission.setFeedback(feedback);
        submission.setSubmittedAt(LocalDateTime.now());
        return CompletableFuture.completedFuture(assignmentSubmissionRepository.save(submission));
    }

    @Cacheable("averageAssignmentScore")
    public Double getAverageAssignmentScore(Long courseId) {
        return assignmentSubmissionRepository.findAverageAssignmentScoreByCourseId(courseId);
    }
} 