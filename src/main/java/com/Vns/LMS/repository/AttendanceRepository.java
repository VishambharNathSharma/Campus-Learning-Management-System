package com.Vns.LMS.repository;

import com.Vns.LMS.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface AttendanceRepository extends JpaRepository<Attendance,Long> {
    Optional<Attendance> findByStudentIdAndCourseIdAndAttendanceDate(
            Long studentId,
            Long courseId,
            LocalDate attendanceDate
    );
    Long countByStudentIdAndAttendanceDateBetween(Long studentId,LocalDate startDate,LocalDate endDate);
    Long countByStudentIdAndPresentTrueAndAttendanceDateBetween(Long studentId,LocalDate startDate,LocalDate endDate);
}
