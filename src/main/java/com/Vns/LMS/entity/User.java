package com.Vns.LMS.entity;

import com.Vns.LMS.enums.Role;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private long id;
@Column(nullable = false)
private String firstName;
@Column(nullable = false)
private String lastName;
@Column(unique = true , nullable = false)
private String email;
@Column(unique = true , nullable = false)
private String rollNo;
@Column(nullable = false)
private String password;
@Enumerated(EnumType.STRING)
@Column(nullable = false)
private Role role;
//No-Args Constructor
    public User(){
    }
    //All-Args Constructor
    public User(Long id,String firstName,String lastName,String email,String rollNo,String password,Role role){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.rollNo = rollNo;
        this.password = password;
        this.role = role;
    }
    //getters

    public long getId() {
        return id;
    }

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

    public void setId(long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
