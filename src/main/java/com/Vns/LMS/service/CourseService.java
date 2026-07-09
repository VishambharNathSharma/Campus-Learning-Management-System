package com.Vns.LMS.service;

import com.Vns.LMS.dto.CourseRequest;
import com.Vns.LMS.dto.CourseResponse;

import java.util.List;

public interface CourseService {
     List<CourseResponse> getAllCourses();
     CourseResponse getCourseById(Long id);
     CourseResponse updateCourse(Long id,CourseRequest request);
     CourseResponse createCourse(CourseRequest request);
    void deleteCourse(Long id);
}
