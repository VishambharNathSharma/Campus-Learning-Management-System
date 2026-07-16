package com.Vns.LMS.controller;

import com.Vns.LMS.dto.LoginRequest;
import com.Vns.LMS.dto.LoginResponse;
import com.Vns.LMS.dto.RegisterRequest;
import com.Vns.LMS.entity.User;
import com.Vns.LMS.service.JwtService;
import com.Vns.LMS.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
   public AuthController(UserService userService,AuthenticationManager authenticationManager,JwtService jwtService){
       this.userService = userService;
       this.authenticationManager = authenticationManager;
       this.jwtService = jwtService;
   }
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request){
        userService.register(request);
        return new ResponseEntity<>("User Registered successfully", HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @RequestBody LoginRequest loginRequest) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        User user = userService.findByEmail(loginRequest.getEmail());

        String token = jwtService.generateToken(user.getEmail());

        return ResponseEntity.ok(
                new LoginResponse(
                        token,
                        "Login Successful",
                        user.getId(),
                        user.getRole().name()
                )
        );
    }
}
