package com.Vns.LMS.dto;


import lombok.Data;


@Data
public class SubmissionRequest {


    private Long assignmentId;


    private Long studentId;


    private String notes;

}
