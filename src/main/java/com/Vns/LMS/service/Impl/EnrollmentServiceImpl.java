package com.Vns.LMS.service.Impl;


import com.Vns.LMS.dto.EnrollmentRequest;
import com.Vns.LMS.dto.EnrollmentResponse;
import com.Vns.LMS.entity.Course;
import com.Vns.LMS.entity.Enrollment;
import com.Vns.LMS.entity.User;
import com.Vns.LMS.enums.EnrollmentStatus;
import com.Vns.LMS.enums.Role;
import com.Vns.LMS.repository.CourseRepository;
import com.Vns.LMS.repository.EnrollmentRepository;
import com.Vns.LMS.repository.UserRepository;
import com.Vns.LMS.service.EnrollmentService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    public EnrollmentServiceImpl(EnrollmentRepository enrollmentRepository, CourseRepository courseRepository,UserRepository userRepository){
        this.enrollmentRepository=enrollmentRepository;
        this.courseRepository=courseRepository;
        this.userRepository=userRepository;
    }

    @Override
    public EnrollmentResponse enrollStudent(EnrollmentRequest request){
        User student = userRepository.findById(request.getStudentId()).orElseThrow(()-> new RuntimeException("Student not found with id:"+request.getStudentId()));
        if(student.getRole()!= Role.STUDENT){
            throw new IllegalArgumentException("User is not a student");
        }
        Course course = courseRepository.findById(request.getCourseId()).orElseThrow(()-> new RuntimeException("Course not found with id:"+request.getCourseId()));

        if(enrollmentRepository.existsByStudentIdAndCourseId(request.getStudentId(), request.getCourseId())){
            throw new IllegalArgumentException("Student is already enrolled");
        }
        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setCourse(course);
        enrollment.setEnrollmentDate(LocalDate.now());
        enrollment.setStatus(EnrollmentStatus.ACTIVE);
        Enrollment savedEnrollment = enrollmentRepository.save(enrollment);
        EnrollmentResponse response=new EnrollmentResponse();
        response.setStudentId(student.getId());
        response.setCourseId(course.getId());
        response.setId(savedEnrollment.getId());
        response.setStatus(savedEnrollment.getStatus());
        response.setEnrollmentDate(savedEnrollment.getEnrollmentDate());
        response.setStudentName(student.getFirstName() + " " + student.getLastName());
        response.setCourseTitle(course.getCourseName());

    return response;
    }
    @Override
    public List<EnrollmentResponse> getAllEnrollments() {

        return enrollmentRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }
    @Override
    public EnrollmentResponse getEnrollmentById(Long id) {

        Enrollment enrollment = enrollmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Enrollment not found"));

        return mapToResponse(enrollment);
    }

    @Override
    public List<EnrollmentResponse> getEnrollmentsByStudent(Long studentId) {

        return enrollmentRepository.findByStudentId(studentId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public List<EnrollmentResponse> getEnrollmentsByCourse(Long courseId) {

        return enrollmentRepository.findByCourseId(courseId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }
    @Override
    public void deleteEnrollment(Long id) {

        Enrollment enrollment = enrollmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Enrollment not found"));

        enrollmentRepository.delete(enrollment);
    }

    private EnrollmentResponse mapToResponse(Enrollment enrollment){
        EnrollmentResponse response=new EnrollmentResponse();
        response.setCourseTitle(enrollment.getCourse().getCourseName());
        response.setStudentName(
                enrollment.getStudent().getFirstName() + " " +
                        enrollment.getStudent().getLastName()
        );
        response.setCourseId(enrollment.getCourse().getId());
        response.setId(enrollment.getId());
        response.setEnrollmentDate(enrollment.getEnrollmentDate());
        response.setStatus(enrollment.getStatus());
        response.setStudentId(enrollment.getStudent().getId());

        return response;
    }

}
