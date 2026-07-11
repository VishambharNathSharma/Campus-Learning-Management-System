package com.Vns.LMS.entity;

import com.Vns.LMS.enums.EnrollmentStatus;
import jakarta.persistence.*;

import java.time.LocalDate;
@Entity
@Table(
        name = "enrollments",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"student_id","course_id"})
        }
)
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id",nullable = false)
    private User student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="course_id",nullable = false)
    private Course course;

    @Column(nullable = false)
    private LocalDate enrollmentDate;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EnrollmentStatus status;

    public Enrollment(Long id,User student,Course course,LocalDate enrollmentDate) {
        this.Id = id;
        this.student=student;
        this.course=course;
        this.enrollmentDate=enrollmentDate;
    }

    public Enrollment(){

    }

    public EnrollmentStatus getStatus() {
        return status;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public void setStatus(EnrollmentStatus status) {
        this.status = status;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public User getStudent() {
        return student;
    }

    public void setStudent(User student) {
        this.student = student;
    }


    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(LocalDate enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }
}
