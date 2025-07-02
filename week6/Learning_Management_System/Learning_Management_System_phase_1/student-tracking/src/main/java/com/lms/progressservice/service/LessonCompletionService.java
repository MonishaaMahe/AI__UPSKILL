package com.lms.progressservice.service;

import com.lms.progressservice.entity.LessonCompletion;
import com.lms.progressservice.repository.LessonCompletionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class LessonCompletionService {
    private final LessonCompletionRepository lessonCompletionRepository;

    @Async
    @Transactional
    public CompletableFuture<LessonCompletion> markLessonCompleted(Long studentId, Long courseId, Long lessonId) {
        LessonCompletion completion = new LessonCompletion();
        completion.setStudentId(studentId);
        completion.setCourseId(courseId);
        completion.setLessonId(lessonId);
        completion.setCompletedAt(LocalDateTime.now());
        return CompletableFuture.completedFuture(lessonCompletionRepository.save(completion));
    }

    @Cacheable("completedLessonCount")
    public Long getCompletedLessonCount(Long studentId, Long courseId) {
        return lessonCompletionRepository.countCompletedLessonsByStudentAndCourse(studentId, courseId);
    }
} 