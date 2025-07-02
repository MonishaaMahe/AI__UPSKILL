package com.lms.enrollmentservice.controller;

import com.lms.enrollmentservice.model.Enrollment;
import com.lms.enrollmentservice.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/enrollments")
public class EnrollmentController {
    @Autowired
    private EnrollmentService enrollmentService;

    @PostMapping
    public Mono<ResponseEntity<Enrollment>> enroll(@RequestBody Enrollment enrollment) {
        Enrollment saved = enrollmentService.enroll(enrollment);
        return Mono.just(ResponseEntity.ok(saved));
    }

    @GetMapping
    public Flux<Enrollment> getEnrollmentsByUserId(@RequestParam Long userId) {
        List<Enrollment> enrollments = enrollmentService.getEnrollmentsByUserId(userId);
        return Flux.fromIterable(enrollments);
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Enrollment>> getEnrollment(@PathVariable Long id) {
        Optional<Enrollment> enrollmentOpt = enrollmentService.getEnrollmentById(id);
        return enrollmentOpt.map(enrollment -> Mono.just(ResponseEntity.ok(enrollment)))
                .orElseGet(() -> Mono.just(ResponseEntity.notFound().build()));
    }
} 