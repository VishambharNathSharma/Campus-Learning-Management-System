package com.Vns.LMS.dto;


import java.time.LocalDateTime;


public class SubmissionResponse {


    private Long id;


    private String assignmentName;


    private String studentName;


    private String submissionFile;


    private String notes;


    private LocalDateTime submittedAt;


    private String status;



    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id=id;
    }


    public String getAssignmentName() {
        return assignmentName;
    }


    public void setAssignmentName(String assignmentName) {
        this.assignmentName=assignmentName;
    }


    public String getStudentName() {
        return studentName;
    }


    public void setStudentName(String studentName) {
        this.studentName=studentName;
    }


    public String getSubmissionFile() {
        return submissionFile;
    }


    public void setSubmissionFile(String submissionFile) {
        this.submissionFile=submissionFile;
    }


    public String getNotes() {
        return notes;
    }


    public void setNotes(String notes) {
        this.notes=notes;
    }


    public LocalDateTime getSubmittedAt() {
        return submittedAt;
    }


    public void setSubmittedAt(LocalDateTime submittedAt) {
        this.submittedAt=submittedAt;
    }


    public String getStatus() {
        return status;
    }


    public void setStatus(String status) {
        this.status=status;
    }

}
