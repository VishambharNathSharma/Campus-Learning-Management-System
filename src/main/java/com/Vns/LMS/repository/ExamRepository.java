package com.Vns.LMS.repository;

import com.Vns.LMS.entity.Attendance;
import com.Vns.LMS.entity.Exam;
import com.Vns.LMS.enums.ExamType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExamRepository extends JpaRepository<Exam,Long> {
    Exam findByCourseIdAndExamType(Long courseId, ExamType examType);
    Integer countByCourseIdIn(List<Long> courseIds);
}
