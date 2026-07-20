package com.Vns.LMS.service.Impl;

import com.Vns.LMS.dto.CourseRequest;
import com.Vns.LMS.dto.CourseResponse;
import com.Vns.LMS.entity.Course;
import com.Vns.LMS.entity.User;
import com.Vns.LMS.repository.CourseRepository;
import com.Vns.LMS.repository.UserRepository;
import com.Vns.LMS.service.CourseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;
    public CourseServiceImpl(CourseRepository courseRepository,UserRepository userRepository){
        this.courseRepository=courseRepository;
        this.userRepository=userRepository;
    }

    @Override
    public CourseResponse createCourse(CourseRequest request){
        Course course = new Course();
        course.setCourseCode(request.getCourseCode());
        course.setCourseName(request.getCourseName());
        course.setDescription(request.getDescription());
        course.setCourseCredits(request.getCourseCredits());
        course.setStartDate(request.getStartDate());
        course.setEndDate(request.getEndDate());
        User teacher = userRepository.findById(request.getTeacherId())
                .orElseThrow(() -> new RuntimeException("Teacher not found"));

        course.setTeacher(teacher);
        course.setLearningObjectives(request.getLearningObjectives());
        Course savedCourse = courseRepository.save(course);
        CourseResponse response = new CourseResponse();
        response.setId(savedCourse.getId());
        response.setCourseCode(savedCourse.getCourseCode());
        response.setCourseName(savedCourse.getCourseName());
        response.setDescription(savedCourse.getDescription());
        response.setCourseCredits(savedCourse.getCourseCredits());
        response.setStartDate(savedCourse.getStartDate());
        response.setEndDate(savedCourse.getEndDate());
        response.setLearningObjectives(savedCourse.getLearningObjectives());

        return response;
    }

    @Override
    public CourseResponse getCourseById(Long id){
        Course course = courseRepository.findById(id).orElseThrow(()-> new RuntimeException("Course Not Found"));
        CourseResponse response = new CourseResponse();
        response.setId(course.getId());
        response.setCourseCode(course.getCourseCode());
        response.setCourseName(course.getCourseName());
        response.setDescription(course.getDescription());
        response.setCourseCredits(course.getCourseCredits());
        response.setStartDate(course.getStartDate());
        response.setEndDate(course.getEndDate());
        response.setLearningObjectives(course.getLearningObjectives());

        return response;
    }


    @Override
    public List<CourseResponse> getAllCourses(){
        return courseRepository.findAll().stream().map(course-> {
                    CourseResponse response = new CourseResponse();
                    response.setId(course.getId());
                    response.setCourseCode(course.getCourseCode());
                    response.setCourseName(course.getCourseName());
                    response.setDescription(course.getDescription());
                    response.setCourseCredits(course.getCourseCredits());
                    response.setStartDate(course.getStartDate());
                    response.setEndDate(course.getEndDate());
                    response.setLearningObjectives(course.getLearningObjectives());
                    response.setTeacherName(
                            course.getTeacher().getFirstName() + " " +
                                    course.getTeacher().getLastName()
                    );
                    return response;
                }
        )
                .toList();
    }

    @Override
    public CourseResponse updateCourse(Long id,CourseRequest request){
        Course course = courseRepository.findById(id).orElseThrow(()-> new RuntimeException("Course Not Found"));
        course.setCourseCode(request.getCourseCode());
        course.setCourseName(request.getCourseName());
        course.setDescription(request.getDescription());
        course.setCourseCredits(request.getCourseCredits());
        course.setStartDate(request.getStartDate());
        course.setEndDate(request.getEndDate());
        course.setLearningObjectives(request.getLearningObjectives());

        Course updatedCourse = courseRepository.save(course);

        CourseResponse response = new CourseResponse();
        response.setId(updatedCourse.getId());
        response.setCourseCode(updatedCourse.getCourseCode());
        response.setCourseName(updatedCourse.getCourseName());
        response.setDescription(updatedCourse.getDescription());
        response.setCourseCredits(updatedCourse.getCourseCredits());
        response.setStartDate(updatedCourse.getStartDate());
        response.setEndDate(updatedCourse.getEndDate());
        response.setLearningObjectives(updatedCourse.getLearningObjectives());

        return response;
    }

    @Override
    public void deleteCourse(Long id){
    if(!courseRepository.existsById(id)){
        throw new RuntimeException("Course not found");
    }
    courseRepository.deleteById(id);
    }
    @Override
    public List<CourseResponse> getCoursesByTeacher(Long teacherId) {

        return courseRepository.findByTeacherId(teacherId)
                .stream()
                .map(this::mapToResponse)
                .toList();

    }
    private CourseResponse mapToResponse(Course course) {

        CourseResponse response = new CourseResponse();

        response.setId(course.getId());
        response.setCourseCode(course.getCourseCode());
        response.setCourseName(course.getCourseName());
        response.setDescription(course.getDescription());
        response.setCourseCredits(course.getCourseCredits());
        response.setLearningObjectives(course.getLearningObjectives());
        response.setId(course.getId());
        response.setStartDate(course.getStartDate());
        response.setEndDate(course.getEndDate());

        if (course.getTeacher() != null) {
            response.setTeacherName(
                    course.getTeacher().getFirstName()
                            + " "
                            + course.getTeacher().getLastName()
            );
        }

        return response;
    }

}
