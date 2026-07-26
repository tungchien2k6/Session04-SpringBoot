package com.ra.session05ex01.repository;

import com.ra.session05ex01.model.entity.Course;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CourseRepository {

    private final List<Course> courses = new ArrayList<>();
    private int nextId = 1;

    public List<Course> findAll() {
        return courses;
    }

    public Course findById(int id) {
        for (Course course : courses) {
            if (course.getId() == id) {
                return course;
            }
        }
        return null;
    }

    public Course create(Course course) {
        course.setId(nextId++);
        courses.add(course);
        return course;
    }

    public Course update(int id, Course updatedCourse) {
        Course existing = findById(id);
        if (existing == null) {
            return null;
        }
        existing.setTitle(updatedCourse.getTitle());
        existing.setStatus(updatedCourse.getStatus());
        existing.setInstructorId(updatedCourse.getInstructorId());
        return existing;
    }

    public Course deleteById(int id) {
        Course existing = findById(id);
        if (existing != null) {
            courses.remove(existing);
        }
        return existing;
    }
}