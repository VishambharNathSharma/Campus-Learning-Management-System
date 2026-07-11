package com.Vns.LMS.entity;

import jakarta.persistence.*;
import org.hibernate.id.factory.spi.GenerationTypeStrategy;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String courseCode;

    @Column(nullable = false)
    private String courseName;

    @Column(length = 1000)
    private String description;

    @Column(nullable = false)
    private Integer courseCredits;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @Column(length = 2000)
    @ElementCollection
    private List<String> learningObjectives;

    public Integer getCourseCredits() {
        return courseCredits;
    }

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private User teacher;

    public LocalDate getEndDate() {
        return endDate;
    }

    public List<String> getLearningObjectives() {
        return learningObjectives;
    }

    public void setLearningObjectives(List<String> learningObjectives) {
        this.learningObjectives = learningObjectives;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setCourseCredits(Integer courseCredits) {
        this.courseCredits = courseCredits;
    }

    public Course(){

    }

    //Getters and Setters

    public Long getId() {
        return id;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getDescription() {
        return description;
    }

    public User getTeacher() {
        return teacher;
    }
    public void setId(Long id){
        this.id=id;
    }
    public void setCourseName(String courseName){
        this.courseName=courseName;
    }
    public void setCourseCode(String courseCode){
        this.courseCode=courseCode;
    }
    public void setDescription(String description){
        this.description=description;
    }
    public void setTeacher(User teacher){
        this.teacher=teacher;
    }

}
