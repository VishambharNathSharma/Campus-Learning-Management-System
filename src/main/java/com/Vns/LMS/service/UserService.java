package com.Vns.LMS.service;

import com.Vns.LMS.dto.RegisterRequest;
import com.Vns.LMS.entity.User;

public interface UserService {
    public void register(RegisterRequest request);
    User findByEmail(String email);
}
