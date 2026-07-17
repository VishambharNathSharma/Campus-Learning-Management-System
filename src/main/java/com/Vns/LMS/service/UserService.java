package com.Vns.LMS.service;

import com.Vns.LMS.dto.RegisterRequest;
import com.Vns.LMS.dto.StudentResponse;
import com.Vns.LMS.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UserService {
    public void register(RegisterRequest request);
    User findByEmail(String email);
    void uploadProfilePicture(Long userId, MultipartFile file) throws IOException;
    StudentResponse getCurrentStudent(String email);
}
