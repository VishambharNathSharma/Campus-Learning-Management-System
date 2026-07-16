package com.Vns.LMS.service.Impl;


import com.Vns.LMS.dto.SubmissionResponse;
import com.Vns.LMS.entity.*;
import com.Vns.LMS.enums.SubmissionStatus;
import com.Vns.LMS.repository.*;
import com.Vns.LMS.service.AssignmentSubmissionService;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class AssignmentSubmissionServiceImpl
        implements AssignmentSubmissionService {


    private final AssignmentSubmissionRepository submissionRepository;

    private final AssignmentRepository assignmentRepository;

    private final UserRepository userRepository;



    private final String uploadDir =
            "uploads/submissions/";



    public AssignmentSubmissionServiceImpl(
            AssignmentSubmissionRepository submissionRepository,
            AssignmentRepository assignmentRepository,
            UserRepository userRepository
    ){

        this.submissionRepository = submissionRepository;
        this.assignmentRepository = assignmentRepository;
        this.userRepository = userRepository;

    }



    @Override
    public SubmissionResponse submitAssignment(
            Long assignmentId,
            Long studentId,
            String notes,
            MultipartFile file
    ){


        try {


            Assignment assignment =
                    assignmentRepository.findById(assignmentId)
                            .orElseThrow(
                                    ()->new RuntimeException("Assignment not found")
                            );



            User student =
                    userRepository.findById(studentId)
                            .orElseThrow(
                                    ()->new RuntimeException("Student not found")
                            );



            // create folder

            Path path =
                    Paths.get(uploadDir);


            if(!Files.exists(path)){
                Files.createDirectories(path);
            }



            // save file

            String fileName =
                    System.currentTimeMillis()
                            +"_"
                            +file.getOriginalFilename();



            Path filePath =
                    path.resolve(fileName);



            Files.copy(
                    file.getInputStream(),
                    filePath,
                    StandardCopyOption.REPLACE_EXISTING
            );



            com.Vns.LMS.entity.AssignmentSubmission submission =
                    new com.Vns.LMS.entity.AssignmentSubmission();


            submission.setAssignment(assignment);

            submission.setStudent(student);

            submission.setSubmissionFile(fileName);

            submission.setNotes(notes);

            submission.setSubmittedAt(
                    LocalDateTime.now()
            );



            // check late submission

            if(LocalDateTime.now()
                    .isAfter(assignment.getDueDateTime())){


                submission.setStatus(
                        SubmissionStatus.LATE
                );


            }else{


                submission.setStatus(
                        SubmissionStatus.SUBMITTED
                );

            }



            AssignmentSubmission saved =
                    submissionRepository.save(submission);



            return mapToResponse(saved);



        }
        catch(IOException e){

            throw new RuntimeException(
                    "File upload failed"
            );

        }


    }





    @Override
    public List<SubmissionResponse> getStudentSubmissions(
            Long studentId
    ){


        return submissionRepository
                .findByStudentId(studentId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());

    }





    @Override
    public List<SubmissionResponse> getAssignmentSubmissions(
            Long assignmentId
    ){


        return submissionRepository
                .findByAssignmentId(assignmentId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());


    }





    private SubmissionResponse mapToResponse(
            AssignmentSubmission submission
    ){


        SubmissionResponse response =
                new SubmissionResponse();



        response.setId(
                submission.getId()
        );


        response.setAssignmentName(
                submission.getAssignment()
                        .getTitle()
        );


        response.setStudentName(

                submission.getStudent()
                        .getFirstName()
                        +" "
                        +submission.getStudent()
                        .getLastName()

        );


        response.setSubmissionFile(
                submission.getSubmissionFile()
        );


        response.setNotes(
                submission.getNotes()
        );


        response.setSubmittedAt(
                submission.getSubmittedAt()
        );


        response.setStatus(
                submission.getStatus()
                        .name()
        );


        return response;

    }

}
