package com.Vns.LMS.service.Impl;

import com.Vns.LMS.dto.AttendanceRequest;
import com.Vns.LMS.dto.AttendanceResponse;
import com.Vns.LMS.dto.AttendanceSummaryResponse;
import com.Vns.LMS.entity.Attendance;
import com.Vns.LMS.entity.Course;
import com.Vns.LMS.entity.Exam;
import com.Vns.LMS.entity.User;
import com.Vns.LMS.enums.ExamType;
import com.Vns.LMS.repository.AttendanceRepository;
import com.Vns.LMS.repository.CourseRepository;
import com.Vns.LMS.repository.ExamRepository;
import com.Vns.LMS.repository.UserRepository;
import com.Vns.LMS.service.AttendanceService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AttendanceServiceImpl implements AttendanceService {
    private final AttendanceRepository attendanceRepository;
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final ExamRepository examRepository;

    public AttendanceServiceImpl(AttendanceRepository attendanceRepository,CourseRepository courseRepository,UserRepository userRepository,ExamRepository examRepository){
        this.attendanceRepository=attendanceRepository;
        this.courseRepository=courseRepository;
        this.userRepository=userRepository;
        this.examRepository=examRepository;
    }

    private AttendanceResponse mapToResponse(Attendance attendance){
        AttendanceResponse response = new AttendanceResponse();
        response.setCourseName(attendance.getCourse().getCourseName());
        response.setAttendanceDate(attendance.getAttendanceDate());
        response.setPresent(attendance.getPresent());
        response.setId(attendance.getId());
        response.setStudentName(attendance.getStudent().getFirstName()+" "+attendance.getStudent().getLastName());
        response.setPercentage(attendance.getPercentage());
        return response;
    }

    private double calculatePercentage(long present,long total){
        if(total==0)
            return 0.0;

        return (present*100.0)/total;
    }

    private double calculateCurrentPercentage(Long studentId, Long courseId) {
        long total = attendanceRepository.countByStudentIdAndCourseId(studentId, courseId);
        long present = attendanceRepository.countByStudentIdAndCourseIdAndPresentTrue(studentId, courseId);

        return calculatePercentage(present, total);
    }

    private Exam getRequiredExam(Long courseId, ExamType examType) {
        Exam exam = examRepository.findByCourseIdAndExamType(courseId, examType);
        if (exam == null) {
            throw new RuntimeException(examType + " exam not found for this course");
        }
        return exam;
    }

    private void checkDuplicateAttendance(Long currentAttendanceId, AttendanceRequest request) {
        Optional<Attendance> existingAttendance = attendanceRepository
                .findByStudentIdAndCourseIdAndAttendanceDate(
                        request.getStudentId(),
                        request.getCourseId(),
                        request.getAttendanceDate()
                );

        existingAttendance.ifPresent(attendance -> {
            if (currentAttendanceId == null || !attendance.getId().equals(currentAttendanceId)) {
                throw new RuntimeException("Attendance already marked for this student, course, and date.");
            }
        });
    }

    @Override
    public AttendanceResponse markAttendance(AttendanceRequest request) {

        User student = userRepository.findById(request.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Course course = courseRepository.findById(request.getCourseId())
                .orElseThrow(() -> new RuntimeException("Course not found"));

        checkDuplicateAttendance(null, request);

        Attendance attendance = new Attendance();
        attendance.setStudent(student);
        attendance.setCourse(course);
        attendance.setAttendanceDate(request.getAttendanceDate());
        attendance.setPresent(request.getPresent());
        attendance.setPercentage(0.0);

        Attendance savedAttendance = attendanceRepository.save(attendance);
        savedAttendance.setPercentage(calculateCurrentPercentage(request.getStudentId(), request.getCourseId()));

        return mapToResponse(attendanceRepository.save(savedAttendance));
    }


    @Override
    public List<AttendanceResponse> getAllAttendance() {

        return attendanceRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public AttendanceResponse getAttendanceById(Long id){
        Attendance attendance = attendanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Attendance not found"));

        return mapToResponse(attendance);
    }

    @Override
    public AttendanceResponse updateAttendance(Long id,AttendanceRequest request){
        Attendance attendance = attendanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Attendance not found"));

        User student = userRepository.findById(request.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Course course = courseRepository.findById(request.getCourseId())
                .orElseThrow(() -> new RuntimeException("Course not found"));

        checkDuplicateAttendance(id, request);

        attendance.setStudent(student);
        attendance.setCourse(course);
        attendance.setAttendanceDate(request.getAttendanceDate());
        attendance.setPresent(request.getPresent());
        attendance.setPercentage(0.0);

        Attendance updatedAttendance = attendanceRepository.save(attendance);
        updatedAttendance.setPercentage(calculateCurrentPercentage(request.getStudentId(), request.getCourseId()));

        return mapToResponse(attendanceRepository.save(updatedAttendance));
    }

    @Override
    public void deleteAttendance(Long id){
        Attendance attendance = attendanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Attendance not found"));

        attendanceRepository.delete(attendance);
    }

    @Override
    public AttendanceSummaryResponse getAttendanceSummary(Long studentID,Long courseID){
        User student = userRepository.findById(studentID).orElseThrow(()-> new RuntimeException("Student not found"));
        Course course = courseRepository.findById(courseID).orElseThrow(()-> new RuntimeException("Course not found"));
        Exam st1Exam = getRequiredExam(courseID, ExamType.ST1);
        Exam st2Exam = getRequiredExam(courseID,ExamType.ST2);
        LocalDate semesterStart = course.getStartDate();
        LocalDate today= LocalDate.now();

        Long st1Total = attendanceRepository.countByStudentIdAndCourseIdAndAttendanceDateBetween(studentID,courseID,semesterStart,st1Exam.getExamDate());
        Long st1Present = attendanceRepository.countByStudentIdAndCourseIdAndPresentTrueAndAttendanceDateBetween(studentID,courseID,semesterStart,st1Exam.getExamDate());
        Long st2Total = attendanceRepository.countByStudentIdAndCourseIdAndAttendanceDateBetween(studentID,courseID,semesterStart,st2Exam.getExamDate());
        Long st2Present = attendanceRepository.countByStudentIdAndCourseIdAndPresentTrueAndAttendanceDateBetween(studentID,courseID,semesterStart,st2Exam.getExamDate());
        long overallTotal = attendanceRepository.countByStudentIdAndCourseIdAndAttendanceDateBetween(
                studentID, courseID, semesterStart, today);

        long overallPresent = attendanceRepository.countByStudentIdAndCourseIdAndPresentTrueAndAttendanceDateBetween(
                studentID, courseID, semesterStart, today);

        AttendanceSummaryResponse response = new AttendanceSummaryResponse();
        response.setStudentName(student.getFirstName() + " " + student.getLastName());

        response.setSt1Attendance(calculatePercentage(st1Present, st1Total));
        response.setSt2Attendance(calculatePercentage(st2Present, st2Total));
        response.setOverrallAttendance(calculatePercentage(overallPresent, overallTotal));

        return response;
    }
}
