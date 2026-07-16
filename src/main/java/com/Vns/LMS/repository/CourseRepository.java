package com.Vns.LMS.repository;

import com.Vns.LMS.entity.Course;
import com.Vns.LMS.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {
    long countByTeacherId(Long teacherId);
    List<Enrollment> findByStudentId(Long studentId);
}
