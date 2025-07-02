package com.lms.progressservice.repository;

import com.lms.progressservice.entity.QuizSubmission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizSubmissionRepository extends JpaRepository<QuizSubmission, Long> {
    @Query("SELECT AVG(qs.score) FROM QuizSubmission qs WHERE qs.courseId = :courseId")
    Double findAverageQuizScoreByCourseId(Long courseId);
} 