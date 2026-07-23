package com.ra.coursemanagement.repository;

import com.ra.coursemanagement.model.Course;
import com.ra.coursemanagement.model.CourseStatus;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.NoSuchElementException;

@Repository
public class CourseRepository {

    private final List<Course> courses = new ArrayList<>();

    public CourseRepository() {
        courses.add(new Course(1L, "Introduction to Java Programming", CourseStatus.ACTIVE, 1L));
        courses.add(new Course(2L, "Advanced Spring Boot", CourseStatus.ACTIVE, 2L));
        courses.add(new Course(3L, "Database Design and SQL", CourseStatus.INACTIVE, 1L));
    }

    public List<Course> findAll() {
        return new ArrayList<>(courses);
    }

    public Optional<Course> findById(Long id) {
        return courses.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst();
    }

    public Course save(Course course) {
        if (course.getId() == null) {
            long newId = courses.stream()
                    .mapToLong(Course::getId)
                    .max()
                    .orElse(0L) + 1;
            course.setId(newId);
        }
        courses.add(course);
        return course;
    }

    public Course update(Long id, Course newData) {
        Course existing = findById(id)
                .orElseThrow(() -> new NoSuchElementException("Course not found with id: " + id));
        existing.setTitle(newData.getTitle());
        existing.setStatus(newData.getStatus());
        existing.setInstructorId(newData.getInstructorId());
        return existing;
    }

    public boolean deleteById(Long id) {
        Course existing = findById(id)
                .orElseThrow(() -> new NoSuchElementException("Course not found with id: " + id));
        return courses.remove(existing);
    }
}