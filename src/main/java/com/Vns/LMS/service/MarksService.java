package com.Vns.LMS.service;
import java.util.List;
import com.Vns.LMS.dto.MarksRequest;
import com.Vns.LMS.dto.MarksResponse;

public interface MarksService {
    MarksResponse createMarks(MarksRequest request);
    List<MarksResponse> getAllMarks();
    MarksResponse getMarksById(Long id);
    MarksResponse updateMarks(Long id,MarksRequest request);
    void deleteMarks(Long id);
    List<MarksResponse> getMarksByStudent(Long studentId);
}
