package com.Vns.LMS.entity;

import com.Vns.LMS.entity.Course;
import com.Vns.LMS.enums.SubmissionType;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "assignments")
public class Assignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
    @Column(name = "question_paper")
    private String questionPaperFileName;
    private String title;

    public String getQuestionPaperFileName() {
        return questionPaperFileName;
    }

    public void setQuestionPaperFileName(String questionPaperFileName) {
        this.questionPaperFileName = questionPaperFileName;
    }

    public Assignment(){

    }

    public Assignment(Long id,Course course,String title,String questionPaperFileName,String instructions,Integer maximumMarks,SubmissionType submissionType,LocalDateTime dueDateTime){
        this.id=id;
        this.course=course;
        this.title=title;
        this.instructions=instructions;
        this.submissionType=submissionType;
        this.dueDateTime=dueDateTime;
        this.questionPaperFileName=questionPaperFileName;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
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

    @Column(length = 1000)
    private String instructions;

    private Integer maximumMarks;

    @Enumerated(EnumType.STRING)
    private SubmissionType submissionType;

    private LocalDateTime dueDateTime;
}
