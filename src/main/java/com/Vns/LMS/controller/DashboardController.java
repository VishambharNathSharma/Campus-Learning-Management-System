package com.Vns.LMS.controller;

import com.Vns.LMS.dto.DashboardResponse;
import com.Vns.LMS.dto.StudentDashboardResponse;
import com.Vns.LMS.dto.TeacherDashboardResponse;
import com.Vns.LMS.service.DashboardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService){
        this.dashboardService=dashboardService;
    }

    @GetMapping
    public ResponseEntity<DashboardResponse> getDashboardStatistics(){
        return ResponseEntity.ok(dashboardService.getDashboardStatistics());
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<StudentDashboardResponse> getStudentDashboard(@PathVariable Long studentId){
        return ResponseEntity.ok(dashboardService.getStudentDashboard(studentId));
    }
    @GetMapping("/teacher/{teacherId}")
    public ResponseEntity<TeacherDashboardResponse> getTeacherDashboard(
            @PathVariable Long teacherId) {

        return ResponseEntity.ok(
                dashboardService.getTeacherDashboard(teacherId));
    }
}
