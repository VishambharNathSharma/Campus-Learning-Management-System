package com.Vns.LMS.service;

import com.Vns.LMS.dto.StudentProfileResponse;

public interface StudentService {

    StudentProfileResponse getStudentProfile(String email);

}