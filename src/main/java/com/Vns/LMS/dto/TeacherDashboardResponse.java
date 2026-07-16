package com.Vns.LMS.dto;


    public class TeacherDashboardResponse {

        private String teacherName;

        private long totalCoursesCreated;

        private long totalStudentsEnrolled;

        private long totalAssignmentsCreated;

        public TeacherDashboardResponse() {
        }

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

