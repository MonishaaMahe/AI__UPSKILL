package com.lms.progressservice.controller;

import com.lms.progressservice.entity.StudentProgress;
import com.lms.progressservice.service.StudentProgressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/progress")
@RequiredArgsConstructor
public class StudentProgressController {
    private final StudentProgressService studentProgressService;

    @PostMapping("/enroll")
    public CompletableFuture<ResponseEntity<StudentProgress>> enroll(@RequestParam Long studentId, @RequestParam Long courseId) {
        return studentProgressService.enrollStudent(studentId, courseId)
                .thenApply(ResponseEntity::ok);
    }

    @PostMapping("/update")
    public CompletableFuture<ResponseEntity<StudentProgress>> updateProgress(@RequestParam Long studentId, @RequestParam Long courseId, @RequestParam Double progressPercent) {
        return studentProgressService.updateProgress(studentId, courseId, progressPercent)
                .thenApply(ResponseEntity::ok);
    }

    @GetMapping
    public ResponseEntity<Optional<StudentProgress>> getProgress(@RequestParam Long studentId, @RequestParam Long courseId) {
        return ResponseEntity.ok(studentProgressService.getProgress(studentId, courseId));
    }

    @GetMapping("/analytics/average")
    public ResponseEntity<Double> getAverageProgress(@RequestParam Long courseId) {
        return ResponseEntity.ok(studentProgressService.getAverageProgressByCourse(courseId));
    }

    @GetMapping("/analytics/all")
    public ResponseEntity<List<StudentProgress>> getAllProgress(@RequestParam Long courseId) {
        return ResponseEntity.ok(studentProgressService.getAllProgressByCourse(courseId));
    }
} 