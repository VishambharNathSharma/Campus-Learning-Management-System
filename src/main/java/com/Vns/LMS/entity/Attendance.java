package com.Vns.LMS.entity;


import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "attendance")
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="student_id",nullable = false)
    private User student;

    @ManyToOne
    @JoinColumn(name="course_id",nullable = false)
    private Course course;


    @Column(nullable = false)
    private LocalDate attendanceDate;


    @Column(nullable = false)
    private Boolean present;

    public Attendance(){

    }

    public Attendance(Long id,User student,Course course,LocalDate attendanceDate,Boolean present){
        this.id=id;
        this.course=course;
        this.attendanceDate=attendanceDate;
        this.present=present;
        this.student=student;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getStudent() {
        return student;
    }

    public void setStudent(User student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public LocalDate getAttendanceDate() {
        return attendanceDate;
    }

    public void setAttendanceDate(LocalDate attendanceDate) {
        this.attendanceDate = attendanceDate;
    }

    public boolean getPresent() {
        return present;
    }

    public void setPresent(Boolean present) {
        this.present = present;
    }

}
