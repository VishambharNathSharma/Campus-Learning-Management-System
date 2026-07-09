package com.Vns.LMS.repository;

import com.Vns.LMS.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class CourseRepository extends JpaRepository<Course,Long> {

}
