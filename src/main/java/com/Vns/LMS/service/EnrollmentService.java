package com.Vns.LMS.service;

import com.Vns.LMS.dto.EnrollmentRequest;
import com.Vns.LMS.dto.EnrollmentResponse;

import java.util.List;

public interface EnrollmentService {
    EnrollmentResponse enrollStudent(EnrollmentRequest request);
    List<EnrollmentResponse> getAllEnrollments();
    EnrollmentResponse getEnrollmentById(Long id);
    List<EnrollmentResponse> getEnrollmentsByStudent(Long studentId);
    List<EnrollmentResponse> getEnrollmentsByCourse(Long courseId);
    void deleteEnrollment(Long id);
}
