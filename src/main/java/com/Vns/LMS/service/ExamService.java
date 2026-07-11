package com.Vns.LMS.service;

import com.Vns.LMS.dto.ExamRequest;
import com.Vns.LMS.dto.ExamResponse;

import java.util.List;

public interface ExamService {
    ExamResponse createExam(ExamRequest request);
    List<ExamResponse> getAllExams();
    ExamResponse getExamById(Long id);
    ExamResponse updateExam(Long id, ExamRequest request);
    void deleteExam(Long id);
}
