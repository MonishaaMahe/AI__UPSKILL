package com.lms.progressservice.controller;

import com.lms.progressservice.entity.AssignmentSubmission;
import com.lms.progressservice.service.AssignmentSubmissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/assignments")
@RequiredArgsConstructor
public class AssignmentSubmissionController {
    private final AssignmentSubmissionService assignmentSubmissionService;

    @PostMapping("/submit")
    public CompletableFuture<ResponseEntity<AssignmentSubmission>> submitAssignment(@RequestParam Long studentId, @RequestParam Long courseId, @RequestParam Long assignmentId, @RequestParam Double score, @RequestParam String feedback) {
        return assignmentSubmissionService.submitAssignment(studentId, courseId, assignmentId, score, feedback)
                .thenApply(ResponseEntity::ok);
    }

    @GetMapping("/average-score")
    public ResponseEntity<Double> getAverageAssignmentScore(@RequestParam Long courseId) {
        return ResponseEntity.ok(assignmentSubmissionService.getAverageAssignmentScore(courseId));
    }
} 