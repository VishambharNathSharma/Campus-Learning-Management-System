package com.Vns.LMS.dto;

import com.Vns.LMS.entity.User;
import jakarta.persistence.ElementCollection;

import java.time.LocalDate;
import java.util.List;

public class CourseResponse {
    private Long id;
    private String courseCode;
    private String courseName;
    private String description;
    private Integer courseCredits;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<String> learningObjectives;
    private String teacherName;

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public CourseResponse(){

    }

    public List<String> getLearningObjectives() {
        return learningObjectives;
    }

    public void setLearningObjectives(List<String> learningObjectives) {
        this.learningObjectives = learningObjectives;
    }

    public Long getId() {
        return id;
    }

    public String getCourseCode(){
        return courseCode;
    }

    public String getCourseName(){
        return courseName;
    }

    public String getDescription(){
        return description;
    }

    public void setId(Long id){
        this.id=id;
    }

    public void setCourseCode(String courseCode){
        this.courseCode=courseCode;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setCourseName(String courseName){
        this.courseName=courseName;
    }

    public Integer getCourseCredits() {
        return courseCredits;
    }

    public void setCourseCredits(Integer courseCredits) {
        this.courseCredits = courseCredits;
    }

    public void setDescription(String description){
        this.description=description;
    }

}
