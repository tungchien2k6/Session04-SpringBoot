package com.ra.session05ex01.service;

import com.ra.session05ex01.model.entity.Course;

import java.util.List;

public interface CourseService {
    List<Course> getAllCourses();
    Course getCourseById(int id);
    Course createCourse(String title, String status, int instructorId);
    Course updateCourse(int id, String title, String status, int instructorId);
    Course deleteCourseById(int id);
}