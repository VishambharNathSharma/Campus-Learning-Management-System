package com.Vns.LMS.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class AttendanceRequest {
    private Long studentId;
    private Long courseId;
    private LocalDate attendanceDate;
    private Boolean present;

    public AttendanceRequest(){

    }

    public Long getStudentId() {
        return studentId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public LocalDate getAttendanceDate() {
        return attendanceDate;
    }

    public void setAttendanceDate(LocalDate attendanceDate) {
        this.attendanceDate = attendanceDate;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Boolean getPresent() {
        return present;
    }

    public void setPresent(Boolean present) {
        this.present = present;
    }
}
