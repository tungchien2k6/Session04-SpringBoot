package com.ra.coursemanagement.service;

import com.ra.coursemanagement.model.Course;
import com.ra.coursemanagement.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CourseService {

    private final CourseRepository repository;

    public CourseService(CourseRepository repository) {
        this.repository = repository;
    }

    public List<Course> getAll() {
        return repository.findAll();
    }

    public Course findCourseById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Course not found with id: " + id));
    }

    public Course create(Course course) {
        return repository.save(course);
    }

    public Course update(Long id, Course newData) {
        return repository.update(id, newData); // ném exception tự nhiên nếu không tìm thấy
    }

    public boolean deleteById(Long id) {
        return repository.deleteById(id);
    }
}