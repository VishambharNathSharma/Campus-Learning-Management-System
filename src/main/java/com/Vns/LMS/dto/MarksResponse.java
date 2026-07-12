package com.Vns.LMS.dto;

public class MarksResponse {

    private Long id;
    private String studentName;
    private String courseName;
    private String examType;
    private Double marksObtained;
    private Double maximumMarks;
    private Double percentage;

    public MarksResponse(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getExamType() {
        return examType;
    }

    public void setExamType(String examType) {
        this.examType = examType;
    }

    public Double getMarksObtained() {
        return marksObtained;
    }

    public void setMarksObtained(Double marksObtained) {
        this.marksObtained = marksObtained;
    }

    public Double getMaximumMarks() {
        return maximumMarks;
    }

    public void setMaximumMarks(Double maximumMarks) {
        this.maximumMarks = maximumMarks;
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }

}
