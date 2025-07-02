package com.lms.progressservice.repository;

import com.lms.progressservice.entity.StudentProgress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentProgressRepository extends JpaRepository<StudentProgress, Long> {
    Optional<StudentProgress> findByStudentIdAndCourseId(Long studentId, Long courseId);

    @Query("SELECT AVG(sp.progressPercent) FROM StudentProgress sp WHERE sp.courseId = :courseId")
    Double findAverageProgressByCourseId(Long courseId);

    List<StudentProgress> findByCourseId(Long courseId);
} 