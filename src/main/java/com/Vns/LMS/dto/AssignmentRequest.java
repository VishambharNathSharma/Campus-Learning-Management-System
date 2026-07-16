package com.Vns.LMS.dto;

import com.Vns.LMS.enums.SubmissionType;

import java.time.LocalDateTime;
public class AssignmentRequest {

    private Long courseId;

    private String title;

    private String instructions;

    private Integer maximumMarks;

    private SubmissionType submissionType;

    private LocalDateTime dueDateTime;
    public AssignmentRequest(){

    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
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

    public SubmissionType getSubmissionType() {
        return submissionType;
    }

    public void setSubmissionType(SubmissionType submissionType) {
        this.submissionType = submissionType;
    }

    public LocalDateTime getDueDateTime() {
        return dueDateTime;
    }

    public void setDueDateTime(LocalDateTime dueDateTime) {
        this.dueDateTime = dueDateTime;
    }
}
