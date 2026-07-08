package com.Vns.LMS.dto;

import com.Vns.LMS.enums.Role;

public class RegisterRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String rollNo;
    private String password;
    private Role role;
    //Constructors
    public RegisterRequest(){

    }
    public RegisterRequest(String firstName,String lastName,String email,String rollNo,String password,Role role){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.rollNo = rollNo;
        this.password = password;
        this.role = role;
    }
    //getters

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }
    public String getRollNo() {
        return rollNo;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }
    //setters

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName){
        this.lastName  = lastName;
    }
    public void setEmail(String email){
        this.email=email;
    }
    public void setRollNo(String rollNo){
        this.rollNo = rollNo;
    }
    public void setPassword(String password){
        this.password=password;
    }
    public void setRole(Role role){
        this.role=role;
    }
}
