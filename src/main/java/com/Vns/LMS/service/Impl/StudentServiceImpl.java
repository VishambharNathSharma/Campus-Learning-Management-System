package com.Vns.LMS.service.Impl;

import com.Vns.LMS.dto.AttendanceResponse;
import com.Vns.LMS.dto.MarksResponse;
import com.Vns.LMS.dto.StudentProfileResponse;

import com.Vns.LMS.entity.Attendance;
import com.Vns.LMS.entity.Marks;

import com.Vns.LMS.entity.User;
import com.Vns.LMS.repository.AttendanceRepository;
import com.Vns.LMS.repository.MarksRepository;

import com.Vns.LMS.repository.UserRepository;
import com.Vns.LMS.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final UserRepository userRepository;
    private final MarksRepository marksRepository;
    private final AttendanceRepository attendanceRepository;

    public StudentServiceImpl(UserRepository userRepository,
                              MarksRepository marksRepository,
                              AttendanceRepository attendanceRepository) {

        this.userRepository = userRepository;
        this.marksRepository=marksRepository;
        this.attendanceRepository = attendanceRepository;
    }

    @Override
    public StudentProfileResponse getStudentProfile(String email) {

        User student = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        StudentProfileResponse response = new StudentProfileResponse();

        response.setFirstName(student.getFirstName());
        response.setLastName(student.getLastName());
        response.setEmail(student.getEmail());
        response.setRollNo(student.getRollNo());
        response.setProfilePicture(student.getProfilePicture());

        List<Marks> results = marksRepository.findByStudentId(student.getId());

        List<MarksResponse> st1 = new ArrayList<>();
        List<MarksResponse> st2 = new ArrayList<>();
        List<MarksResponse> put = new ArrayList<>();

        for (Marks result : results) {

            MarksResponse marks = new MarksResponse();

            marks.setCourseName(result.getExam().getCourse().getCourseName());
            marks.setMarksObtained(result.getMarksObtained());
            marks.setMaximumMarks(result.getMaximumMarks());

            switch (result.getExam().getExamType()) {

                case ST1 -> st1.add(marks);

                case ST2 -> st2.add(marks);

                case PUT -> put.add(marks);

            }

        }

        response.setSt1Marks(st1);
        response.setSt2Marks(st2);
        response.setPutMarks(put);

        List<Attendance> attendanceList =
                attendanceRepository.findByStudent(student);

        List<AttendanceResponse> st1Attendance = new ArrayList<>();
        List<AttendanceResponse> st2Attendance = new ArrayList<>();
        List<AttendanceResponse> overallAttendance = new ArrayList<>();

        for (Attendance attendance : attendanceList) {

            AttendanceResponse attendanceResponse =
                    new AttendanceResponse();

            attendanceResponse.setCourseName(
                    attendance.getCourse().getCourseName());

            attendanceResponse.setPercentage(
                    attendance.getPercentage());

            overallAttendance.add(attendanceResponse);

        }

        response.setOverallAttendance(overallAttendance);
        response.setSt1Attendance(st1Attendance);
        response.setSt2Attendance(st2Attendance);

        return response;
    }

}