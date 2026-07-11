package com.Vns.LMS.service.Impl;

import com.Vns.LMS.dto.AttendanceRequest;
import com.Vns.LMS.dto.AttendanceResponse;
import com.Vns.LMS.dto.ExamRequest;
import com.Vns.LMS.dto.ExamResponse;
import com.Vns.LMS.entity.Course;
import com.Vns.LMS.entity.Exam;
import com.Vns.LMS.repository.AttendanceRepository;
import com.Vns.LMS.repository.CourseRepository;
import com.Vns.LMS.repository.ExamRepository;
import com.Vns.LMS.service.ExamService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamServiceImpl implements ExamService{
    private final ExamRepository examRepository;
    private final CourseRepository courseRepository;

    public ExamServiceImpl(ExamRepository examRepository,CourseRepository courseRepository){
        this.examRepository=examRepository;
        this.courseRepository=courseRepository;
    }

    private ExamResponse mapToResponse(Exam exam){
        ExamResponse response = new ExamResponse();
        response.setCourseName(exam.getCourse().getCourseName());
        response.setExamDate(exam.getExamDate());
        response.setExamType(exam.getExamType());
        response.setId(exam.getId());

        return response;
    }

    @Override
    public ExamResponse createExam(ExamRequest request){
        Course course = courseRepository.findById(request.getCourseId()).orElseThrow(()->new RuntimeException("Course not found"));
        Exam exam = new Exam();
        exam.setCourse(course);
        exam.setExamDate(request.getExamDate());
        exam.setExamType(request.getExamType());
        Exam savedExam =examRepository.save(exam);
        return mapToResponse(savedExam);
    }

    @Override
    public List<ExamResponse> getAllExams(){
        return examRepository.findAll().stream().map(this::mapToResponse).toList();
    }

    @Override
    public ExamResponse getExamById(Long id){
        Exam exam = examRepository.findById(id).orElseThrow(()-> new RuntimeException("Exam not found"));
        return mapToResponse(exam);
    }

    @Override
    public ExamResponse updateExam(Long id,ExamRequest request){
        Exam exam = examRepository.findById(id).orElseThrow(()-> new RuntimeException("Exam not found"));
        Course course = courseRepository.findById(request.getCourseId()).orElseThrow(()->new RuntimeException("Course not found"));
        exam.setCourse(course);
        exam.setExamDate(request.getExamDate());
        exam.setExamType(request.getExamType());
        Exam updatedExam =examRepository.save(exam);
        return mapToResponse(updatedExam);
    }

    @Override
    public void deleteExam(Long id){
        Exam exam = examRepository.findById(id).orElseThrow(()-> new RuntimeException("Exam not found"));
        examRepository.delete(exam);
    }
}
