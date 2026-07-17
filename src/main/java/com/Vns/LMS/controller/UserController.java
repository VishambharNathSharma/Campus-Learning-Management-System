package com.Vns.LMS.controller;

import com.Vns.LMS.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/users/profile-picture")
public class UserController {

    private final UserService userService;
    public UserController(UserService userService){
        this.userService=userService;
    }
    @PostMapping("/profile-picture")
    public ResponseEntity<String> uploadProfilePicture(
            @RequestParam Long userId,
            @RequestParam MultipartFile file)
            throws IOException {


        userService.uploadProfilePicture(userId, file);

        return ResponseEntity.ok("Profile picture uploaded successfully");
    }
}
