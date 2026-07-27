package com.ra.session05ex01.service;

import com.ra.session05ex01.model.entity.Course;
import com.ra.session05ex01.model.entity.Instructor;
import com.ra.session05ex01.repository.CourseRepository;
import com.ra.session05ex01.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final InstructorRepository instructorRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository, InstructorRepository instructorRepository) {
        this.courseRepository = courseRepository;
        this.instructorRepository = instructorRepository;
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Course getCourseById(int id) {
        return courseRepository.findById(id).orElseThrow(()->new NoSuchElementException("Course không tồn tại với id: " + id));
    }

    @Override
    public Course createCourse(String title, String status, int instructorId) {
        Instructor instructor = instructorRepository.findById(instructorId).orElseThrow(() -> new NoSuchElementException("Instructor không tồn tại với id: " + instructorId));
        Course newCourse = new Course(0, title, status, instructorId);
        return courseRepository.create(newCourse);
    }

    @Override
    public Course updateCourse(int id, String title, String status, int instructorId) {
        Instructor instructor = instructorRepository.findById(instructorId).orElseThrow(()-> new NoSuchElementException("Instructor không tồn tại với id: " + instructorId));
        Course updatedData = new Course(0, title, status, instructorId);
        return courseRepository.update(id, updatedData);
    }

    @Override
    public Course deleteCourseById(int id) {
        return courseRepository.deleteById(id);
    }
}