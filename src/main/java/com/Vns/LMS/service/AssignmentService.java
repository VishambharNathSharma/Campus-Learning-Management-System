package com.Vns.LMS.service;

import com.Vns.LMS.dto.AssignmentRequest;
import com.Vns.LMS.dto.AssignmentResponse;

import java.util.List;

public interface AssignmentService {

    AssignmentResponse createAssignment(AssignmentRequest request);

    List<AssignmentResponse> getAllAssignments();

    AssignmentResponse getAssignmentById(Long id);

    AssignmentResponse updateAssignment(Long id,
                                        AssignmentRequest request);

    void deleteAssignment(Long id);
    List<AssignmentResponse> getAssignmentsByCourse(Long courseId);

}