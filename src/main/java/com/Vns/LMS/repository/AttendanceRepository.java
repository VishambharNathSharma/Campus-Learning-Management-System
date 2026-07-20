package com.Vns.LMS.repository;

import com.Vns.LMS.entity.Attendance;
import com.Vns.LMS.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AttendanceRepository extends JpaRepository<Attendance,Long> {
    Optional<Attendance> findByStudentIdAndCourseIdAndAttendanceDate(
            Long studentId,
            Long courseId,
            LocalDate attendanceDate
    );
    Long countByStudentIdAndAttendanceDateBetween(Long studentId,LocalDate startDate,LocalDate endDate);
    Long countByStudentIdAndPresentTrueAndAttendanceDateBetween(Long studentId,LocalDate startDate,LocalDate endDate);
    Long countByStudentIdAndCourseIdAndAttendanceDateBetween(Long studentId, Long courseId, LocalDate startDate, LocalDate endDate);
    Long countByStudentIdAndCourseIdAndPresentTrueAndAttendanceDateBetween(Long studentId, Long courseId, LocalDate startDate, LocalDate endDate);

    Long countByStudentId(Long studentId);

    Long countByStudentIdAndPresentTrue(Long studentId);
    Long countByStudentIdAndCourseId(Long studentId, Long courseId);
    Long countByStudentIdAndCourseIdAndPresentTrue(Long studentId, Long courseId);
    List<Attendance> findByStudent(User user);
}
