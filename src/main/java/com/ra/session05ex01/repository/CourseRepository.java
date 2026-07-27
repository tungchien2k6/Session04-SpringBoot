package com.ra.session05ex01.repository;

import com.ra.session05ex01.model.entity.Course;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Repository
public class CourseRepository {

    private final List<Course> courses = new ArrayList<>();
    private int nextId = 1;

    public List<Course> findAll() {
        return courses;
    }

    public Optional<Course> findById(int id) {
        return courses.stream().filter(c -> c.getId() == id).findFirst();
    }

    public Course create(Course course) {
        course.setId(nextId++);
        courses.add(course);
        return course;
    }

    public Course update(int id, Course updatedCourse) {
        Course existing = findById(id).orElseThrow(() -> new NoSuchElementException("Course không tồn tại với id: " + id));
        existing.setTitle(updatedCourse.getTitle());
        existing.setStatus(updatedCourse.getStatus());
        existing.setInstructorId(updatedCourse.getInstructorId());
        return existing;
    }

    public Course deleteById(int id) {
        Course existing = findById(id).orElseThrow(() -> new NoSuchElementException("Course không tồn tại với id: " + id));
        courses.remove(existing);
        return existing;
    }
}