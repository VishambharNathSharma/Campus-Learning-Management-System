package com.Vns.LMS.service.Impl;



import com.Vns.LMS.dto.AssignmentRequest;
import com.Vns.LMS.dto.AssignmentResponse;
import com.Vns.LMS.entity.Assignment;
import com.Vns.LMS.entity.Course;
import com.Vns.LMS.repository.AssignmentRepository;
import com.Vns.LMS.repository.CourseRepository;
import com.Vns.LMS.service.AssignmentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssignmentServiceImpl implements AssignmentService {

    private final AssignmentRepository assignmentRepository;
    private final CourseRepository courseRepository;

    public AssignmentServiceImpl(AssignmentRepository assignmentRepository,
                                 CourseRepository courseRepository) {
        this.assignmentRepository = assignmentRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public AssignmentResponse createAssignment(AssignmentRequest request) {

        Course course = courseRepository.findById(request.getCourseId())
                .orElseThrow(() ->
                        new RuntimeException("Course not found"));
        Assignment assignment = new Assignment();

        assignment.setCourse(course);
        assignment.setTitle(request.getTitle());
        assignment.setInstructions(request.getInstructions());
        assignment.setMaximumMarks(request.getMaximumMarks());
        assignment.setSubmissionType(request.getSubmissionType());
        assignment.setDueDateTime(request.getDueDateTime());

        Assignment savedAssignment =
                assignmentRepository.save(assignment);

        return mapToResponse(savedAssignment);
    }

    @Override
    public List<AssignmentResponse> getAllAssignments() {

        return assignmentRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public AssignmentResponse getAssignmentById(Long id) {

        Assignment assignment = assignmentRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Assignment not found"));

        return mapToResponse(assignment);
    }

    private AssignmentResponse mapToResponse(Assignment assignment) {

        AssignmentResponse response = new AssignmentResponse();

        response.setId(assignment.getId());

        response.setCourseName(
                assignment.getCourse().getCourseName());

        response.setTitle(
                assignment.getTitle());

        response.setInstructions(
                assignment.getInstructions());

        response.setMaximumMarks(
                assignment.getMaximumMarks());

        response.setSubmissionType(
                assignment.getSubmissionType().name());

        response.setDueDateTime(
                assignment.getDueDateTime());
        response.setQuestionPaperFileName(
                assignment.getQuestionPaperFileName()
        );

        return response;
    }
    @Override
    public List<AssignmentResponse> getAssignmentsByCourse(Long courseId) {

        return assignmentRepository.findByCourseId(courseId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }
    @Override
    public AssignmentResponse updateAssignment(Long id,
                                               AssignmentRequest request) {

        Assignment assignment = assignmentRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Assignment not found"));

        Course course = courseRepository.findById(request.getCourseId())
                .orElseThrow(() ->
                        new RuntimeException("Course not found"));

        assignment.setCourse(course);
        assignment.setTitle(request.getTitle());
        assignment.setInstructions(request.getInstructions());
        assignment.setMaximumMarks(request.getMaximumMarks());
        assignment.setSubmissionType(request.getSubmissionType());
        assignment.setDueDateTime(request.getDueDateTime());

        Assignment updatedAssignment =
                assignmentRepository.save(assignment);

        return mapToResponse(updatedAssignment);
    }

    @Override
    public void deleteAssignment(Long id) {

        Assignment assignment = assignmentRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Assignment not found"));

        assignmentRepository.delete(assignment);
    }


}
