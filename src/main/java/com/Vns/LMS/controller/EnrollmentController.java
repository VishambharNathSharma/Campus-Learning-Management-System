package com.Vns.LMS.controller;

import com.Vns.LMS.dto.EnrollmentRequest;
import com.Vns.LMS.dto.EnrollmentResponse;
import com.Vns.LMS.service.EnrollmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {
    private final EnrollmentService enrollmentService;
    public EnrollmentController(EnrollmentService enrollmentService){
        this.enrollmentService=enrollmentService;
    }

    @PostMapping
    public ResponseEntity<EnrollmentResponse> enrollStudent(@RequestBody @Valid EnrollmentRequest request){
    EnrollmentResponse response = enrollmentService.enrollStudent(request);

    return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<EnrollmentResponse>> getAllEnrollment(){
        return ResponseEntity.ok(enrollmentService.getAllEnrollments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnrollmentResponse> getEnrollmentById(@PathVariable Long id){
        return ResponseEntity.ok(enrollmentService.getEnrollmentById(id));
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<EnrollmentResponse>> getEnrollmentsByStudent(
            @PathVariable Long studentId) {

        return ResponseEntity.ok(
                enrollmentService.getEnrollmentsByStudent(studentId));
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<EnrollmentResponse>> getEnrollmentsByCourse(
            @PathVariable Long courseId) {

        return ResponseEntity.ok(
                enrollmentService.getEnrollmentsByCourse(courseId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEnrollment(
            @PathVariable Long id) {

        enrollmentService.deleteEnrollment(id);

        return ResponseEntity.ok("Enrollment deleted successfully");
    }
    @GetMapping("/teacher/{teacherId}")
    public ResponseEntity<List<EnrollmentResponse>> getEnrollmentsByTeacher(
            @PathVariable Long teacherId) {

        return ResponseEntity.ok(
                enrollmentService.getEnrollmentsByTeacher(teacherId)
        );
    }
}
