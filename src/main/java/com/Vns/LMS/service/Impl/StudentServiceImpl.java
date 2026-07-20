package com.Vns.LMS.service.Impl;

import com.Vns.LMS.dto.AttendanceResponse;
import com.Vns.LMS.dto.MarksResponse;
import com.Vns.LMS.dto.StudentProfileResponse;

import com.Vns.LMS.entity.Attendance;
import com.Vns.LMS.entity.Exam;
import com.Vns.LMS.entity.Marks;

import com.Vns.LMS.entity.User;
import com.Vns.LMS.repository.AttendanceRepository;
import com.Vns.LMS.repository.ExamRepository;
import com.Vns.LMS.repository.MarksRepository;

import com.Vns.LMS.repository.UserRepository;
import com.Vns.LMS.enums.ExamType;
import com.Vns.LMS.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class StudentServiceImpl implements StudentService {

    private final UserRepository userRepository;
    private final MarksRepository marksRepository;
    private final AttendanceRepository attendanceRepository;
    private final ExamRepository examRepository;

    public StudentServiceImpl(UserRepository userRepository,
                              MarksRepository marksRepository,
                              AttendanceRepository attendanceRepository,
                              ExamRepository examRepository) {

        this.userRepository = userRepository;
        this.marksRepository=marksRepository;
        this.attendanceRepository = attendanceRepository;
        this.examRepository = examRepository;
    }

    private double calculatePercentage(long present, long total) {
        if (total == 0) {
            return 0.0;
        }
        return (present * 100.0) / total;
    }

    private AttendanceResponse createAttendanceResponse(Attendance attendance, double percentage) {
        AttendanceResponse attendanceResponse = new AttendanceResponse();

        attendanceResponse.setCourseName(
                attendance.getCourse().getCourseName());

        attendanceResponse.setPercentage(percentage);

        return attendanceResponse;
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
        Set<Long> processedCourseIds = new HashSet<>();

        for (Attendance attendance : attendanceList) {
            Long courseId = attendance.getCourse().getId();
            if (!processedCourseIds.add(courseId)) {
                continue;
            }

            Exam st1Exam = examRepository.findByCourseIdAndExamType(courseId, ExamType.ST1);
            Exam st2Exam = examRepository.findByCourseIdAndExamType(courseId, ExamType.ST2);

            long overallTotal = attendanceRepository.countByStudentIdAndCourseId(student.getId(), courseId);
            long overallPresent = attendanceRepository.countByStudentIdAndCourseIdAndPresentTrue(student.getId(), courseId);

            overallAttendance.add(
                    createAttendanceResponse(attendance, calculatePercentage(overallPresent, overallTotal)));

            if (st1Exam != null) {
                long st1Total = attendanceRepository.countByStudentIdAndCourseIdAndAttendanceDateBetween(
                        student.getId(), courseId, attendance.getCourse().getStartDate(), st1Exam.getExamDate());
                long st1Present = attendanceRepository.countByStudentIdAndCourseIdAndPresentTrueAndAttendanceDateBetween(
                        student.getId(), courseId, attendance.getCourse().getStartDate(), st1Exam.getExamDate());

                st1Attendance.add(
                        createAttendanceResponse(attendance, calculatePercentage(st1Present, st1Total)));
            }

            if (st2Exam != null) {
                long st2Total = attendanceRepository.countByStudentIdAndCourseIdAndAttendanceDateBetween(
                        student.getId(), courseId, attendance.getCourse().getStartDate(), st2Exam.getExamDate());
                long st2Present = attendanceRepository.countByStudentIdAndCourseIdAndPresentTrueAndAttendanceDateBetween(
                        student.getId(), courseId, attendance.getCourse().getStartDate(), st2Exam.getExamDate());

                st2Attendance.add(
                        createAttendanceResponse(attendance, calculatePercentage(st2Present, st2Total)));
            }

        }

        response.setOverallAttendance(overallAttendance);
        response.setSt1Attendance(st1Attendance);
        response.setSt2Attendance(st2Attendance);

        return response;
    }

}
