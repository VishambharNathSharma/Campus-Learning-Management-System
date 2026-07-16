package com.Vns.LMS.entity;

import com.Vns.LMS.enums.SubmissionStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;



import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "assignment_submissions")
public class AssignmentSubmission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "assignment_id")
    private Assignment assignment;


    @ManyToOne
    @JoinColumn(name = "student_id")
    private User student;


    private String submissionFile;


    @Column(length = 1000)
    private String notes;


    private LocalDateTime submittedAt;


    @Enumerated(EnumType.STRING)
    private SubmissionStatus status;


    public AssignmentSubmission(){

    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public Assignment getAssignment() {
        return assignment;
    }


    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
    }


    public User getStudent() {
        return student;
    }


    public void setStudent(User student) {
        this.student = student;
    }


    public String getSubmissionFile() {
        return submissionFile;
    }


    public void setSubmissionFile(String submissionFile) {
        this.submissionFile = submissionFile;
    }


    public String getNotes() {
        return notes;
    }


    public void setNotes(String notes) {
        this.notes = notes;
    }


    public LocalDateTime getSubmittedAt() {
        return submittedAt;
    }


    public void setSubmittedAt(LocalDateTime submittedAt) {
        this.submittedAt = submittedAt;
    }


    public SubmissionStatus getStatus() {
        return status;
    }


    public void setStatus(SubmissionStatus status) {
        this.status = status;
    }
}