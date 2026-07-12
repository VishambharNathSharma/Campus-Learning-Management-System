package com.Vns.LMS.dto;

public class MarksRequest {
    private Long studentId;
    private Long courseId;
    private Long examId;
    private Double marksObtained;
    private Double maximumMarks;

    public MarksRequest(){

    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Double getMarksObtained() {
        return marksObtained;
    }

    public void setMarksObtained(Double marksObtained) {
        this.marksObtained = marksObtained;
    }

    public Long getExamId() {
        return examId;
    }

    public void setExamId(Long examId) {
        this.examId = examId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Double getMaximumMarks() {
        return maximumMarks;
    }

    public void setMaximumMarks(Double maximumMarks) {
        this.maximumMarks = maximumMarks;
    }
}
