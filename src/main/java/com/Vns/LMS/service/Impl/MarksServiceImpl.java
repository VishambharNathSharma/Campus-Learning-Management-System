package com.Vns.LMS.service.Impl;

import com.Vns.LMS.dto.ExamResponse;
import com.Vns.LMS.dto.MarksRequest;
import com.Vns.LMS.dto.MarksResponse;
import com.Vns.LMS.entity.Course;
import com.Vns.LMS.entity.Exam;
import com.Vns.LMS.entity.Marks;
import com.Vns.LMS.entity.User;
import com.Vns.LMS.repository.CourseRepository;
import com.Vns.LMS.repository.ExamRepository;
import com.Vns.LMS.repository.MarksRepository;
import com.Vns.LMS.repository.UserRepository;
import com.Vns.LMS.service.MarksService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarksServiceImpl implements MarksService {

    private final MarksRepository marksRepository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;
    private final ExamRepository examRepository;

    public MarksServiceImpl(MarksRepository marksRepository,UserRepository userRepository,CourseRepository courseRepository,ExamRepository examRepository){
        this.marksRepository=marksRepository;
        this.userRepository=userRepository;
        this.courseRepository=courseRepository;
        this.examRepository=examRepository;
    }

    private Double calculatePercentage(Double obtained,Double maximum){
        if(maximum==null||maximum==0.0)
            return 0.0;

        return (obtained*100)/maximum;
    }
    private MarksResponse mapToResponse(Marks marks){
        MarksResponse response = new MarksResponse();

        response.setId(marks.getId());

        response.setStudentName(
                marks.getStudent().getFirstName() + " " +
                        marks.getStudent().getLastName());

        response.setCourseName(
                marks.getCourse().getCourseName());

        response.setExamType(
                marks.getExam().getExamType().name());

        response.setMarksObtained(
                marks.getMarksObtained());

        response.setMaximumMarks(
                marks.getMaximumMarks());

        response.setPercentage(
                calculatePercentage(
                        marks.getMarksObtained(),
                        marks.getMaximumMarks()));

        return response;
    }
    @Override
    public MarksResponse createMarks(MarksRequest request) {
        marksRepository.findByStudentIdAndExamId(
                        request.getStudentId(),
                        request.getExamId())
                .ifPresent(m -> {
                    throw new RuntimeException("Marks already exist for this student and exam.");
                });
        User student = userRepository.findById(request.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Course course = courseRepository.findById(request.getCourseId())
                .orElseThrow(() -> new RuntimeException("Course not found"));

        Exam exam = examRepository.findById(request.getExamId())
                .orElseThrow(() -> new RuntimeException("Exam not found"));

        Marks marks = new Marks();

        marks.setStudent(student);
        marks.setCourse(course);
        marks.setExam(exam);
        marks.setMarksObtained(request.getMarksObtained());
        marks.setMaximumMarks(request.getMaximumMarks());

        Marks savedMarks = marksRepository.save(marks);

        return mapToResponse(savedMarks);
    }

    @Override
    public List<MarksResponse> getAllMarks() {

        return marksRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public MarksResponse getMarksById(Long id) {

        Marks marks = marksRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Marks not found"));

        return mapToResponse(marks);
    }

    @Override
    public MarksResponse updateMarks(Long id, MarksRequest request) {

        Marks marks = marksRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Marks not found"));

        User student = userRepository.findById(request.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Course course = courseRepository.findById(request.getCourseId())
                .orElseThrow(() -> new RuntimeException("Course not found"));

        Exam exam = examRepository.findById(request.getExamId())
                .orElseThrow(() -> new RuntimeException("Exam not found"));

        marks.setStudent(student);
        marks.setCourse(course);
        marks.setExam(exam);
        marks.setMarksObtained(request.getMarksObtained());
        marks.setMaximumMarks(request.getMaximumMarks());

        Marks updatedMarks = marksRepository.save(marks);

        return mapToResponse(updatedMarks);
    }

    @Override
    public void deleteMarks(Long id) {

        Marks marks = marksRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Marks not found"));

        marksRepository.delete(marks);
    }

    @Override
    public List<MarksResponse> getMarksByStudent(Long studentId) {

        return marksRepository.findByStudentId(studentId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

}
