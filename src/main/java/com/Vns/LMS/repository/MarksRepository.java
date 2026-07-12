package com.Vns.LMS.repository;

import com.Vns.LMS.entity.Marks;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MarksRepository extends JpaRepository<Marks,Long> {
    Optional<Marks> findByStudentIdAndExamId(Long studentId, Long examId);
    List<Marks> findByStudentId(Long studentId);
}
