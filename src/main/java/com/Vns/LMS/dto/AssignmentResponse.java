package com.Vns.LMS.dto;

import java.time.LocalDateTime;

public class AssignmentResponse {
    private Long id;

    private Long courseId;

    private String courseName;

    private String title;

    private String instructions;

    private Integer maximumMarks;

    private String submissionType;

    private LocalDateTime dueDateTime;

    public String getQuestionPaperFileName() {
        return questionPaperFileName;
    }

    public void setQuestionPaperFileName(String questionPaperFileName) {
        this.questionPaperFileName = questionPaperFileName;
    }

    private String questionPaperFileName;
    public AssignmentResponse(){

    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public Integer getMaximumMarks() {
        return maximumMarks;
    }

    public void setMaximumMarks(Integer maximumMarks) {
        this.maximumMarks = maximumMarks;
    }

    public String getSubmissionType() {
        return submissionType;
    }

    public void setSubmissionType(String submissionType) {
        this.submissionType = submissionType;
    }

    public LocalDateTime getDueDateTime() {
        return dueDateTime;
    }

    public void setDueDateTime(LocalDateTime dueDateTime) {
        this.dueDateTime = dueDateTime;
    }
}
