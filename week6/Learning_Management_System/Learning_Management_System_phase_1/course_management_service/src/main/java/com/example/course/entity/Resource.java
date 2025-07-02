package com.example.course.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Resource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String url;

    private String fileType;

    @ManyToOne
    @JoinColumn(name = "lesson_id")
    private Lesson lesson;
} 