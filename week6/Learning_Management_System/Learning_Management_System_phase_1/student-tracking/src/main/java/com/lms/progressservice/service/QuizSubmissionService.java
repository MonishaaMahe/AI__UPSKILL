package com.lms.progressservice.service;

import com.lms.progressservice.entity.QuizSubmission;
import com.lms.progressservice.repository.QuizSubmissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class QuizSubmissionService {
    private final QuizSubmissionRepository quizSubmissionRepository;

    @Async
    @Transactional
    public CompletableFuture<QuizSubmission> submitQuiz(Long studentId, Long courseId, Long quizId, Double score) {
        QuizSubmission submission = new QuizSubmission();
        submission.setStudentId(studentId);
        submission.setCourseId(courseId);
        submission.setQuizId(quizId);
        submission.setScore(score);
        submission.setSubmittedAt(LocalDateTime.now());
        return CompletableFuture.completedFuture(quizSubmissionRepository.save(submission));
    }

    @Cacheable("averageQuizScore")
    public Double getAverageQuizScore(Long courseId) {
        return quizSubmissionRepository.findAverageQuizScoreByCourseId(courseId);
    }
} 