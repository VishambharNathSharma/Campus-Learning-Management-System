package com.Vns.LMS.service;

import com.Vns.LMS.dto.DashboardResponse;
import com.Vns.LMS.dto.StudentDashboardResponse;
import com.Vns.LMS.dto.TeacherDashboardResponse;

public interface DashboardService {
    DashboardResponse getDashboardStatistics();
    StudentDashboardResponse getStudentDashboard(Long studentId);
    TeacherDashboardResponse getTeacherDashboard(Long teacherId);
}
