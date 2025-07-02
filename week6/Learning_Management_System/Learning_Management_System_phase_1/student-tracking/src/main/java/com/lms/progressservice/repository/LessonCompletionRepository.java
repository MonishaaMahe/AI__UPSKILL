package com.lms.progressservice.repository;

import com.lms.progressservice.entity.LessonCompletion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonCompletionRepository extends JpaRepository<LessonCompletion, Long> {
    @Query("SELECT COUNT(lc) FROM LessonCompletion lc WHERE lc.studentId = :studentId AND lc.courseId = :courseId")
    Long countCompletedLessonsByStudentAndCourse(Long studentId, Long courseId);
} 