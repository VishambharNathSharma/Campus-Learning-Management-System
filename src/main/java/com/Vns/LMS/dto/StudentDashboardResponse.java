package com.Vns.LMS.dto;

import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.List;

public class StudentDashboardResponse {
    private String studentName;

    private Long totalCourses;
    private String rollno;
    private double totalAttendance;
    private Double averagePercentage;
    private Integer totalAssignments;
    private Integer totalExams;
    private Long presentAttendance;
    private List<StudentCourseResponse> enrolledCourses;

    public List<StudentCourseResponse> getEnrolledCourses() {
        return enrolledCourses;
    }

    public void setEnrolledCourses(List<StudentCourseResponse> enrolledCourses) {
        this.enrolledCourses = enrolledCourses;
    }

    public Long getPresentAttendance() {
        return presentAttendance;
    }

    public void setPresentAttendance(Long presentAttendance) {
        this.presentAttendance = presentAttendance;
    }

    public Long getAbsentAttendance() {
        return absentAttendance;
    }

    public void setAbsentAttendance(Long absentAttendance) {
        this.absentAttendance = absentAttendance;
    }

    private Long absentAttendance;

    public Integer getTotalAssignments() {
        return totalAssignments;
    }

    public void setTotalAssignments(Integer totalAssignments) {
        this.totalAssignments = totalAssignments;
    }

    public Integer getTotalExams() {
        return totalExams;
    }

    public void setTotalExams(Integer totalExams) {
        this.totalExams = totalExams;
    }

    public Double getAveragePercentage() {
        return averagePercentage;
    }

    public void setAveragePercentage(Double averagePercentage) {
        this.averagePercentage = averagePercentage;
    }

    public StudentDashboardResponse(){

    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Long getTotalCourses() {
        return totalCourses;
    }

    public void setTotalCourses(Long totalCourses) {
        this.totalCourses = totalCourses;
    }

    public String getRollno() {
        return rollno;
    }

    public void setRollno(String rollno) {
        this.rollno = rollno;
    }

    public double getTotalAttendance() {
        return totalAttendance;
    }

    public void setTotalAttendance(double totalAttendance) {
        this.totalAttendance = totalAttendance;
    }


}
