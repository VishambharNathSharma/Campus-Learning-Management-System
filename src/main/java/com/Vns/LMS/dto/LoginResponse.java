package com.Vns.LMS.dto;

public class LoginResponse {
    private String token;
    private String message;
    private Long id;
    private String role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public LoginResponse() {
    }
   public LoginResponse(String token,String message,Long id,String role){
       this.token = token;
       this.message = message;
       this.id=id;
       this.role=role;
   }
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
