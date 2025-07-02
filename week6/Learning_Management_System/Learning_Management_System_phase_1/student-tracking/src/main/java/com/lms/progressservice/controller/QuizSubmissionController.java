package com.lms.progressservice.controller;

import com.lms.progressservice.entity.QuizSubmission;
import com.lms.progressservice.service.QuizSubmissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/quizzes")
@RequiredArgsConstructor
public class QuizSubmissionController {
    private final QuizSubmissionService quizSubmissionService;

    @PostMapping("/submit")
    public CompletableFuture<ResponseEntity<QuizSubmission>> submitQuiz(@RequestParam Long studentId, @RequestParam Long courseId, @RequestParam Long quizId, @RequestParam Double score) {
        return quizSubmissionService.submitQuiz(studentId, courseId, quizId, score)
                .thenApply(ResponseEntity::ok);
    }

    @GetMapping("/average-score")
    public ResponseEntity<Double> getAverageQuizScore(@RequestParam Long courseId) {
        return ResponseEntity.ok(quizSubmissionService.getAverageQuizScore(courseId));
    }
} 