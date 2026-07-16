package com.Vns.LMS.dto;

public class DashboardResponse {
    private Long totalStudents;
    private Long totalTeachers;
    private Long totalEnrollments;
    private Long totalAttendanceRecords;
    private Long totalMarksRecords;
    private Long totalExams;
    private Long totalCourses;

    public DashboardResponse(){

    }

    public Long getTotalStudents() {
        return totalStudents;
    }

    public void setTotalStudents(Long totalStudents) {
        this.totalStudents = totalStudents;
    }

    public Long getTotalTeachers() {
        return totalTeachers;
    }

    public void setTotalTeachers(Long totalTeachers) {
        this.totalTeachers = totalTeachers;
    }

    public Long getTotalEnrollments() {
        return totalEnrollments;
    }

    public void setTotalEnrollments(Long totalEnrollments) {
        this.totalEnrollments = totalEnrollments;
    }

    public Long getTotalAttendanceRecords() {
        return totalAttendanceRecords;
    }

    public void setTotalAttendanceRecords(Long totalAttendanceRecords) {
        this.totalAttendanceRecords = totalAttendanceRecords;
    }

    public Long getTotalMarksRecords() {
        return totalMarksRecords;
    }

    public void setTotalMarksRecords(Long totalMarksRecords) {
        this.totalMarksRecords = totalMarksRecords;
    }

    public Long getTotalExams() {
        return totalExams;
    }

    public void setTotalExams(Long totalExams) {
        this.totalExams = totalExams;
    }

    public Long getTotalCourses() {
        return totalCourses;
    }

    public void setTotalCourses(Long totalCourses) {
        this.totalCourses = totalCourses;
    }
}
