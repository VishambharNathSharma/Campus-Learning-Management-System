package com.Vns.LMS.controller;

import com.Vns.LMS.dto.StudentProfileResponse;
import com.Vns.LMS.dto.StudentResponse;
import com.Vns.LMS.service.StudentService;
import com.Vns.LMS.service.UserService;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/student")
public class StudentController {
    private final StudentService studentService;
    @GetMapping("/dashboard")
    public String dashboard(){
        return "Welcome Student!";
    }
    @GetMapping("/me")
    @PreAuthorize("hasRole('STUDENT')")
    public StudentResponse currentUser(Authentication authentication) {

        return userService.getCurrentStudent(authentication.getName());

    }
    private final UserService userService;

    public StudentController(UserService userService,StudentService studentService) {
        this.userService = userService;
        this.studentService=studentService;
    }
    @GetMapping("/profile")
    public StudentProfileResponse profile(Authentication authentication) {

        return studentService.getStudentProfile(
                authentication.getName());

    }
}
