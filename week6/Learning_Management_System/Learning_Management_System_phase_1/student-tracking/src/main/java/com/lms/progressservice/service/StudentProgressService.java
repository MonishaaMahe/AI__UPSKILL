package com.lms.progressservice.service;

import com.lms.progressservice.entity.StudentProgress;
import com.lms.progressservice.repository.StudentProgressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class StudentProgressService {
    private final StudentProgressRepository studentProgressRepository;

    @Async
    @Transactional
    public CompletableFuture<StudentProgress> enrollStudent(Long studentId, Long courseId) {
        StudentProgress progress = new StudentProgress();
        progress.setStudentId(studentId);
        progress.setCourseId(courseId);
        progress.setProgressPercent(0.0);
        progress.setStatus("IN_PROGRESS");
        return CompletableFuture.completedFuture(studentProgressRepository.save(progress));
    }

    @Async
    @Transactional
    public CompletableFuture<StudentProgress> updateProgress(Long studentId, Long courseId, Double progressPercent) {
        Optional<StudentProgress> optional = studentProgressRepository.findByStudentIdAndCourseId(studentId, courseId);
        if (optional.isPresent()) {
            StudentProgress progress = optional.get();
            progress.setProgressPercent(progressPercent);
            if (progressPercent >= 100.0) {
                progress.setStatus("COMPLETED");
            }
            return CompletableFuture.completedFuture(studentProgressRepository.save(progress));
        }
        return CompletableFuture.completedFuture(null);
    }

    @Cacheable("studentProgress")
    public Optional<StudentProgress> getProgress(Long studentId, Long courseId) {
        return studentProgressRepository.findByStudentIdAndCourseId(studentId, courseId);
    }

    @Cacheable("courseProgressAnalytics")
    public Double getAverageProgressByCourse(Long courseId) {
        return studentProgressRepository.findAverageProgressByCourseId(courseId);
    }

    public List<StudentProgress> getAllProgressByCourse(Long courseId) {
        return studentProgressRepository.findByCourseId(courseId);
    }
} 