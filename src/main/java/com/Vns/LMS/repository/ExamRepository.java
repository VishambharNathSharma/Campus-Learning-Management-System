package com.Vns.LMS.repository;

import com.Vns.LMS.entity.Attendance;
import com.Vns.LMS.entity.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamRepository extends JpaRepository<Exam,Long> {
}
