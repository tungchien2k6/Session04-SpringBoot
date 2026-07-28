package com.ra.session05ex01.service;

import com.ra.session05ex01.model.entity.Course;
import com.ra.session05ex01.model.entity.CourseStatus;
import com.ra.session05ex01.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Course getCourseById(Long id) {
        return courseRepository.findById(id).orElseThrow(()->new NoSuchElementException("Course không tồn tại với id: " + id));
    }

    @Override
    public Course createCourse(String title, CourseStatus status, Long instructorId) {
        Course newCourse = new Course(null, title, status, instructorId);
        return courseRepository.save(newCourse);
    }

    @Override
    public Course updateCourse(Long id, String title, CourseStatus status, Long instructorId) {
        Course existing = courseRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Course không tồn tại với id: " + id));
        existing.setTitle(title);
        existing.setStatus(status);
        existing.setInstructorId(instructorId);
        return courseRepository.save(existing);
    }

    @Override
    public Course deleteCourseById(Long id) {
        Course existing = courseRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Course không tồn tại với id: " + id));
        courseRepository.deleteById(id);
        return existing;
    }
}