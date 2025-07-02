package com.lms.courseservice.controller;

import com.lms.courseservice.model.Course;
import com.lms.courseservice.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Optional;

@RestController
@RequestMapping("/courses")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @PostMapping
    public Mono<ResponseEntity<Course>> createCourse(@RequestBody Course course) {
        Course saved = courseService.createCourse(course);
        return Mono.just(ResponseEntity.ok(saved));
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Course>> getCourse(@PathVariable Long id) {
        Optional<Course> courseOpt = courseService.getCourseById(id);
        return courseOpt.map(course -> Mono.just(ResponseEntity.ok(course)))
                .orElseGet(() -> Mono.just(ResponseEntity.notFound().build()));
    }
} 