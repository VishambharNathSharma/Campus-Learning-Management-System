package com.Vns.LMS.service.Impl;

import com.Vns.LMS.dto.*;
import com.Vns.LMS.entity.Course;
import com.Vns.LMS.entity.Enrollment;
import com.Vns.LMS.entity.Marks;
import com.Vns.LMS.entity.User;
import com.Vns.LMS.enums.Role;
import com.Vns.LMS.repository.*;
import com.Vns.LMS.service.DashboardService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DashboardServiceImpl implements DashboardService {
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;
    private final AttendanceRepository attendanceRepository;
    private final MarksRepository marksRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final ExamRepository examRepository;
    private final AssignmentRepository assignmentRepository;

    public DashboardServiceImpl(UserRepository userRepository,CourseRepository courseRepository,AttendanceRepository attendanceRepository, MarksRepository marksRepository,EnrollmentRepository enrollmentRepository,ExamRepository examRepository,AssignmentRepository assignmentRepository){
        this.userRepository=userRepository;
        this.courseRepository=courseRepository;
        this.attendanceRepository=attendanceRepository;
        this.marksRepository=marksRepository;
        this.enrollmentRepository=enrollmentRepository;
        this.examRepository=examRepository;
        this.assignmentRepository=assignmentRepository;
    }

    @Override
    public DashboardResponse getDashboardStatistics(){
        DashboardResponse response = new DashboardResponse();
        response.setTotalAttendanceRecords(attendanceRepository.count());
        response.setTotalCourses(courseRepository.count());
        response.setTotalEnrollments(enrollmentRepository.count());
        response.setTotalExams(examRepository.count());
        response.setTotalMarksRecords(marksRepository.count());
        response.setTotalTeachers(userRepository.countByRole(Role.TEACHER));
        response.setTotalStudents(userRepository.countByRole(Role.STUDENT));

        return response;
    }

    @Override
    public StudentDashboardResponse getStudentDashboard(Long studentId){
        User student = userRepository.findById(studentId).orElseThrow(()-> new RuntimeException("User not found"));
        StudentDashboardResponse response = new StudentDashboardResponse();
        response.setStudentName(student.getFirstName()+""+student.getLastName());
        response.setRollno(student.getRollNo());
        response.setTotalCourses(enrollmentRepository.countByStudentId(studentId));
        List<Long> courseIds = enrollmentRepository
                .findByStudentId(studentId)
                .stream()
                .map(enrollment -> enrollment.getCourse().getId())
                .toList();

        response.setTotalAssignments(
                assignmentRepository.countByCourseIdIn(courseIds)
        );

        response.setTotalExams(
                examRepository.countByCourseIdIn(courseIds)
        );
        Long totalAttendance=attendanceRepository.countByStudentId(studentId);
        Long presentAttendance=attendanceRepository.countByStudentIdAndPresentTrue(studentId);
        Long absentAttendance = totalAttendance - presentAttendance;
        Double attendancePercentage=0.0;

        if(totalAttendance>0){
            attendancePercentage=(presentAttendance*100.0)/totalAttendance;
        }
        response.setAbsentAttendance(absentAttendance);
        response.setTotalAttendance(attendancePercentage);
        response.setPresentAttendance(presentAttendance);
        List<Marks> marksList = marksRepository.findByStudentId(studentId);
        Double averagePercentage = 0.0;

        if(!marksList.isEmpty()){
            averagePercentage = marksList.stream().mapToDouble(mark->(mark.getMarksObtained()*100)/mark.getMaximumMarks()).average().orElse(0.0);
        }
        response.setAveragePercentage(averagePercentage);
        List<Enrollment> enrollments =
                enrollmentRepository.findByStudentId(studentId);

        List<StudentCourseResponse> courseResponses =
                enrollments.stream().map(enrollment -> {

                    Course course = enrollment.getCourse();

                    StudentCourseResponse response1 =
                            new StudentCourseResponse();

                    response1.setCourseId(course.getId());
                    response1.setCourseCode(course.getCourseCode());
                    response1.setCourseName(course.getCourseName());

                    response1.setTeacherName(
                            course.getTeacher().getFirstName() + " "
                                    + course.getTeacher().getLastName());

                    response1.setCourseCredits(course.getCourseCredits());

                    return response1;

                }).toList();

        response.setEnrolledCourses(courseResponses);
        return response;
    }
        @Override
        public TeacherDashboardResponse getTeacherDashboard() {

            Authentication authentication =
                    SecurityContextHolder.getContext().getAuthentication();

            String email = authentication.getName();
            User teacher = userRepository.findByEmail(email)
                    .orElseThrow(() -> new RuntimeException("Teacher not found"));

            TeacherDashboardResponse response =
                    new TeacherDashboardResponse();

            response.setTeacherName(
                    teacher.getFirstName() + " " + teacher.getLastName());

            response.setEmail(teacher.getEmail());

            Long teacherId1 = teacher.getId();

            // Total Courses
            response.setTotalCoursesCreated(
                    courseRepository.countByTeacherId(teacherId1));

            // Total Assignments
            response.setTotalAssignmentsCreated(
                    assignmentRepository.countByCourseTeacherId(teacherId1));

            // Courses
            List<Course> courses =
                    courseRepository.findByTeacherId(teacherId1);

            List<CourseResponse> courseResponses =
                    new ArrayList<>();

            long totalStudents = 0;

            for (Course course : courses) {

                CourseResponse courseResponse =
                        new CourseResponse();

                courseResponse.setId(course.getId());

                courseResponse.setCourseName(course.getCourseName());

                Long studentCount =
                        enrollmentRepository.countByCourseId(course.getId());

                courseResponse.setStudentCount(studentCount);

                totalStudents += studentCount;

                courseResponses.add(courseResponse);
            }

            response.setCourses(courseResponses);

            response.setTotalStudents(totalStudents);

            response.setTotalExams(response.getTotalExams());

            return response;
        }
    }

