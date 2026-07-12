package com.Vns.LMS.controller;

import com.Vns.LMS.dto.ExamRequest;
import com.Vns.LMS.dto.ExamResponse;
import com.Vns.LMS.service.ExamService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exams")
public class ExamController {
    private final ExamService examService;
    public ExamController(ExamService examService){
        this.examService = examService;
    }

    @PostMapping
    public ResponseEntity<ExamResponse> createExam(@RequestBody ExamRequest request){
        return ResponseEntity.ok(examService.createExam(request));
    }

    @GetMapping
    public ResponseEntity<List<ExamResponse>> getAllExams(){
        return ResponseEntity.ok(examService.getAllExams());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExamResponse> getExamById(@PathVariable Long id){
        return ResponseEntity.ok(examService.getExamById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExamResponse> updateExam(@PathVariable Long id,@RequestBody ExamRequest request){
        return ResponseEntity.ok(examService.updateExam(id,request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteExam(@PathVariable Long id){
        examService.deleteExam(id);
        return ResponseEntity.ok("Exam deleted successfully");
    }

}
