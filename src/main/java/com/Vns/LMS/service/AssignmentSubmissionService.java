package com.Vns.LMS.service;


import com.Vns.LMS.dto.SubmissionResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface AssignmentSubmissionService {


    SubmissionResponse submitAssignment(
            Long assignmentId,
            Long studentId,
            String notes,
            MultipartFile file
    );


    List<SubmissionResponse> getStudentSubmissions(Long studentId);


    List<SubmissionResponse> getAssignmentSubmissions(Long assignmentId);

}