package com.Vns.LMS.dto;

import com.Vns.LMS.enums.EnrollmentStatus;

import java.time.LocalDate;

public class EnrollmentResponse {
    private Long Id;
    private String studentName;
    private Long studentId;
    private Long courseId;
    private String courseTitle;
    private LocalDate enrollmentDate;
    private EnrollmentStatus status;

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public EnrollmentResponse(){

    }

    public Long getId() {
        return Id;
    }

    public EnrollmentStatus getStatus() {
        return status;
    }

    public void setStatus(EnrollmentStatus status) {
        this.status = status;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(LocalDate enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }
}
