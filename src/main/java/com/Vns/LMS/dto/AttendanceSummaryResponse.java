package com.Vns.LMS.dto;

public class AttendanceSummaryResponse {
    private String studentName;
    private double st1Attendance;
    private double st2Attendance;
    private double overrallAttendance;

    public AttendanceSummaryResponse(){

    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public double getSt1Attendance() {
        return st1Attendance;
    }

    public void setSt1Attendance(double st1Attendance) {
        this.st1Attendance = st1Attendance;
    }

    public double getSt2Attendance() {
        return st2Attendance;
    }

    public void setSt2Attendance(double st2Attendance) {
        this.st2Attendance = st2Attendance;
    }

    public double getOverrallAttendance() {
        return overrallAttendance;
    }

    public void setOverrallAttendance(double overrallAttendance) {
        this.overrallAttendance = overrallAttendance;
    }

}
