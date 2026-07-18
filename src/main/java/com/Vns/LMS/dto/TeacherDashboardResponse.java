package com.Vns.LMS.dto;


import java.util.List;

public class TeacherDashboardResponse {

        private String teacherName;
        private String email;
        private long totalCoursesCreated;
        private Long totalStudents;
        private long totalStudentsEnrolled;
        private Long totalExams;
        private Long setTotalAssignments;

    public Long getSetTotalAssignments() {
        return setTotalAssignments;
    }

    public void setSetTotalAssignments(Long setTotalAssignments) {
        this.setTotalAssignments = setTotalAssignments;
    }

    public Long getTotalExams() {
        return totalExams;
    }

    public void setTotalExams(Long totalExams) {
        this.totalExams = totalExams;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getTotalStudents() {
        return totalStudents;
    }

    public void setTotalStudents(Long totalStudents) {
        this.totalStudents = totalStudents;
    }

    public List<CourseResponse> getCourses() {
        return courses;
    }

    public void setCourses(List<CourseResponse> courses) {
        this.courses = courses;
    }

    private long totalAssignmentsCreated;

        public TeacherDashboardResponse() {
        }
        private List<CourseResponse> courses;
        // Getters & Setters

        public String getTeacherName() {
            return teacherName;
        }

        public void setTeacherName(String teacherName) {
            this.teacherName = teacherName;
        }

        public long getTotalCoursesCreated() {
            return totalCoursesCreated;
        }

        public void setTotalCoursesCreated(long totalCoursesCreated) {
            this.totalCoursesCreated = totalCoursesCreated;
        }

        public long getTotalStudentsEnrolled() {
            return totalStudentsEnrolled;
        }

        public void setTotalStudentsEnrolled(long totalStudentsEnrolled) {
            this.totalStudentsEnrolled = totalStudentsEnrolled;
        }

        public long getTotalAssignmentsCreated() {
            return totalAssignmentsCreated;
        }

        public void setTotalAssignmentsCreated(long totalAssignmentsCreated) {
            this.totalAssignmentsCreated = totalAssignmentsCreated;
        }
    }

