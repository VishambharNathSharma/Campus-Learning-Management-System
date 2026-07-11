package com.Vns.LMS.service.Impl;

import com.Vns.LMS.dto.AttendanceRequest;
import com.Vns.LMS.dto.AttendanceResponse;
import com.Vns.LMS.dto.ExamResponse;
import com.Vns.LMS.entity.Attendance;
import com.Vns.LMS.entity.Course;
import com.Vns.LMS.entity.Exam;
import com.Vns.LMS.entity.User;
import com.Vns.LMS.repository.AttendanceRepository;
import com.Vns.LMS.repository.CourseRepository;
import com.Vns.LMS.repository.UserRepository;
import com.Vns.LMS.service.AttendanceService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendanceServiceImpl implements AttendanceService {
    private final AttendanceRepository attendanceRepository;
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    public AttendanceServiceImpl(AttendanceRepository attendanceRepository,CourseRepository courseRepository,UserRepository userRepository){
        this.attendanceRepository=attendanceRepository;
        this.courseRepository=courseRepository;
        this.userRepository=userRepository;
    }

    private AttendanceResponse mapToResponse(Attendance attendance){
        AttendanceResponse response = new AttendanceResponse();
        response.setCourseName(attendance.getCourse().getCourseName());
        response.setAttendanceDate(attendance.getAttendanceDate());
        response.setPresent(attendance.getPresent());
        response.setId(attendance.getId());
        response.setStudentName(attendance.getStudent().getFirstName()+""+attendance.getStudent().getLastName());

        return response;
    }

        @Override
        public AttendanceResponse markAttendance(AttendanceRequest request) {

            User student = userRepository.findById(request.getStudentId())
                    .orElseThrow(() -> new RuntimeException("Student not found"));

            Course course = courseRepository.findById(request.getCourseId())
                    .orElseThrow(() -> new RuntimeException("Course not found"));

            Attendance attendance = new Attendance();
            attendance.setStudent(student);
            attendance.setCourse(course);
            attendance.setAttendanceDate(request.getAttendanceDate());
            attendance.setPresent(request.getPresent());

            Attendance savedAttendance = attendanceRepository.save(attendance);

            return mapToResponse(savedAttendance);
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

        attendance.setStudent(student);
        attendance.setCourse(course);
        attendance.setAttendanceDate(request.getAttendanceDate());
        attendance.setPresent(request.getPresent());

        Attendance updatedAttendance = attendanceRepository.save(attendance);

        return mapToResponse(updatedAttendance);
    }

    @Override
    public void deleteAttendance(Long id){
        Attendance attendance = attendanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Attendance not found"));

        attendanceRepository.delete(attendance);
    }
}
