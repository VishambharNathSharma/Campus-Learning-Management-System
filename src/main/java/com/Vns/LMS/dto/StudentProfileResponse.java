package com.Vns.LMS.dto;

import java.util.List;

public class StudentProfileResponse {

    private String firstName;
    private String lastName;
    private String email;
    private String rollNo;
    private String profilePicture;

    private List<MarksResponse> st1Marks;
    private List<MarksResponse> st2Marks;
    private List<MarksResponse> putMarks;

    private List<AttendanceResponse> st1Attendance;
    private List<AttendanceResponse> st2Attendance;
    private List<AttendanceResponse> overallAttendance;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRollNo() {
        return rollNo;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public List<MarksResponse> getSt1Marks() {
        return st1Marks;
    }

    public void setSt1Marks(List<MarksResponse> st1Marks) {
        this.st1Marks = st1Marks;
    }

    public List<MarksResponse> getSt2Marks() {
        return st2Marks;
    }

    public void setSt2Marks(List<MarksResponse> st2Marks) {
        this.st2Marks = st2Marks;
    }

    public List<MarksResponse> getPutMarks() {
        return putMarks;
    }

    public void setPutMarks(List<MarksResponse> putMarks) {
        this.putMarks = putMarks;
    }

    public List<AttendanceResponse> getSt1Attendance() {
        return st1Attendance;
    }

    public void setSt1Attendance(List<AttendanceResponse> st1Attendance) {
        this.st1Attendance = st1Attendance;
    }

    public List<AttendanceResponse> getSt2Attendance() {
        return st2Attendance;
    }

    public void setSt2Attendance(List<AttendanceResponse> st2Attendance) {
        this.st2Attendance = st2Attendance;
    }

    public List<AttendanceResponse> getOverallAttendance() {
        return overallAttendance;
    }

    public void setOverallAttendance(List<AttendanceResponse> overallAttendance) {
        this.overallAttendance = overallAttendance;
    }
// getters and setters
}
