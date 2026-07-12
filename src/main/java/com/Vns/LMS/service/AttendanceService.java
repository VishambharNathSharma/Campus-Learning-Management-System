package com.Vns.LMS.service;

import com.Vns.LMS.dto.AttendanceRequest;
import com.Vns.LMS.dto.AttendanceResponse;
import com.Vns.LMS.dto.AttendanceSummaryResponse;

import java.util.List;

public interface AttendanceService {
    AttendanceResponse markAttendance(AttendanceRequest request);
    List<AttendanceResponse> getAllAttendance();
    AttendanceResponse getAttendanceById(Long id);
    AttendanceResponse updateAttendance(Long id,AttendanceRequest request);
    void deleteAttendance(Long id);
    AttendanceSummaryResponse getAttendanceSummary(Long studentId,Long courseId);
}
