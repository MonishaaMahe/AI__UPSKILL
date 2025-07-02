package com.lms.progressservice.repository;

import com.lms.progressservice.entity.AssignmentSubmission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AssignmentSubmissionRepository extends JpaRepository<AssignmentSubmission, Long> {
    @Query("SELECT AVG(a.score) FROM AssignmentSubmission a WHERE a.courseId = :courseId")
    Double findAverageAssignmentScoreByCourseId(Long courseId);
} 