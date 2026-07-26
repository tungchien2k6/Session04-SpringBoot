package com.ra.session05ex01.repository;

import com.ra.session05ex01.model.entity.Course;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CourseRepository {
    private final List<Course> courses = new ArrayList<>();

    public CourseRepository() {
        courses.add(new Course(1, "Java", "Active", 1));
        courses.add(new Course(2, "C++", "Active", 2));
        courses.add(new Course(3, "Python", "Active", 1));
    }

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
}
