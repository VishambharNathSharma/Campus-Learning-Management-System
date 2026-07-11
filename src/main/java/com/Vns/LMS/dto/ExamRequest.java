package com.Vns.LMS.dto;

import com.Vns.LMS.enums.ExamType;

import java.time.LocalDate;

public class ExamRequest {
    private Long courseId;
    private ExamType examType;
    private LocalDate examDate;

    public ExamRequest(){

    }

    public Long getCourseId() {
        return courseId;
    }

    public ExamType getExamType() {
        return examType;
    }

    public LocalDate getExamDate() {
        return examDate;
    }

    public void setExamDate(LocalDate examDate) {
        this.examDate = examDate;
    }

    public void setExamType(ExamType examType) {
        this.examType = examType;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }
}
