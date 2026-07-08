package com.Vns.LMS.controller;

import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/student")
public class StudentController {
    @GetMapping("/dashboard")
    public String dashboard(){
        return "Welcome Student!";
    }
    @GetMapping("/me")
    public String currentUser(Authentication authentication){
        return "Logged in user: " + authentication.getName();
    }

}
