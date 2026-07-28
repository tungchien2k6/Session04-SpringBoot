package com.ra.session05ex01.service;

import com.ra.session05ex01.model.entity.Course;
import com.ra.session05ex01.model.entity.CourseStatus;

import java.util.List;

public interface CourseService {
    List<Course> getAllCourses();

    Course getCourseById(Long id);

    Course createCourse(String title, CourseStatus status, Long instructorId);

    Course updateCourse(Long id, String title, CourseStatus status, Long instructorId);

    Course deleteCourseById(Long id);
}