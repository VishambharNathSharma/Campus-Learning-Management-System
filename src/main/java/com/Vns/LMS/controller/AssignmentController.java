package com.Vns.LMS.controller;


import com.Vns.LMS.dto.AssignmentRequest;
import com.Vns.LMS.dto.AssignmentResponse;
import com.Vns.LMS.service.AssignmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assignments")
public class AssignmentController {

    private final AssignmentService assignmentService;

    public AssignmentController(AssignmentService assignmentService) {
        this.assignmentService = assignmentService;
    }

    @PostMapping
    public ResponseEntity<AssignmentResponse> createAssignment(
            @RequestBody AssignmentRequest request) {

        return new ResponseEntity<>(
                assignmentService.createAssignment(request),
                HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AssignmentResponse>> getAllAssignments() {

        return ResponseEntity.ok(
                assignmentService.getAllAssignments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AssignmentResponse> getAssignmentById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                assignmentService.getAssignmentById(id));
    }
    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<AssignmentResponse>> getAssignmentsByCourse(
            @PathVariable Long courseId) {

        return ResponseEntity.ok(
                assignmentService.getAssignmentsByCourse(courseId)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<AssignmentResponse> updateAssignment(
            @PathVariable Long id,
            @RequestBody AssignmentRequest request) {

        return ResponseEntity.ok(
                assignmentService.updateAssignment(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAssignment(
            @PathVariable Long id) {

        assignmentService.deleteAssignment(id);

        return ResponseEntity.ok("Assignment deleted successfully");
    }
}
