package com.lms.progressservice.controller;

import com.lms.progressservice.entity.LessonCompletion;
import com.lms.progressservice.service.LessonCompletionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/lessons")
@RequiredArgsConstructor
public class LessonCompletionController {
    private final LessonCompletionService lessonCompletionService;

    @PostMapping("/complete")
    public CompletableFuture<ResponseEntity<LessonCompletion>> completeLesson(@RequestParam Long studentId, @RequestParam Long courseId, @RequestParam Long lessonId) {
        return lessonCompletionService.markLessonCompleted(studentId, courseId, lessonId)
                .thenApply(ResponseEntity::ok);
    }

    @GetMapping("/completed-count")
    public ResponseEntity<Long> getCompletedLessonCount(@RequestParam Long studentId, @RequestParam Long courseId) {
        return ResponseEntity.ok(lessonCompletionService.getCompletedLessonCount(studentId, courseId));
    }
} 