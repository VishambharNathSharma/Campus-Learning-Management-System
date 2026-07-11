package com.Vns.LMS.dto;

import com.Vns.LMS.enums.ExamType;

import java.time.LocalDate;

public class ExamResponse {

    private Long id;
    private String courseName;
    private ExamType examType;
    private LocalDate examDate;

    public ExamResponse(){

    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Long getId() {
        return id;
    }

    public ExamType getExamType() {
        return examType;
    }

    public void setExamType(ExamType examType) {
        this.examType = examType;
    }

    public LocalDate getExamDate() {
        return examDate;
    }

    public void setExamDate(LocalDate examDate) {
        this.examDate = examDate;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
