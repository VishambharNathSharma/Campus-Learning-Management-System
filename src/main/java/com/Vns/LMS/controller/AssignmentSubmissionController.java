package com.Vns.LMS.controller;


import com.Vns.LMS.dto.SubmissionResponse;
import com.Vns.LMS.service.AssignmentSubmissionService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;



@RestController
@RequestMapping("/api/submissions")
public class AssignmentSubmissionController {


    private final AssignmentSubmissionService service;


    public AssignmentSubmissionController(
            AssignmentSubmissionService service
    ){
        this.service=service;
    }



    @PostMapping
    public ResponseEntity<SubmissionResponse> submitAssignment(

            @RequestParam Long assignmentId,

            @RequestParam Long studentId,

            @RequestParam(required=false) String notes,

            @RequestParam MultipartFile file

    ){

        return ResponseEntity.ok(

                service.submitAssignment(
                        assignmentId,
                        studentId,
                        notes,
                        file
                )

        );

    }



    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<SubmissionResponse>> getStudentSubmissions(

            @PathVariable Long studentId

    ){

        return ResponseEntity.ok(
                service.getStudentSubmissions(studentId)
        );

    }




    @GetMapping("/assignment/{assignmentId}")
    public ResponseEntity<List<SubmissionResponse>> getAssignmentSubmissions(

            @PathVariable Long assignmentId

    ){

        return ResponseEntity.ok(
                service.getAssignmentSubmissions(assignmentId)
        );

    }

}