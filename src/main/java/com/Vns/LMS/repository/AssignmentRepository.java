package com.Vns.LMS.repository;

import com.Vns.LMS.entity.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssignmentRepository extends JpaRepository<com.Vns.LMS.entity.Assignment,Long> {

    List<Assignment> findByCourseId(Long courseId);

    long countByCourseTeacherId(Long teacherId);
    Integer countByCourseIdIn(List<Long> courseIds);
}
