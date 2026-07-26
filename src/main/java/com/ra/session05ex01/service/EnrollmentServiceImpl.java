package com.ra.session05ex01.service;

import com.ra.session05ex01.model.entity.Course;
import com.ra.session05ex01.model.entity.Enrollment;
import com.ra.session05ex01.repository.CourseRepository;
import com.ra.session05ex01.repository.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final CourseRepository courseRepository;

    @Autowired
    public EnrollmentServiceImpl(EnrollmentRepository enrollmentRepository, CourseRepository courseRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Enrollment> getAllEnrollments() {
        return enrollmentRepository.findAll();
    }

    @Override
    public Enrollment getEnrollmentById(int id) {
        return enrollmentRepository.findById(id);
    }

    @Override
    public Enrollment createEnrollment(String studentName, int courseId) {
        Course course = courseRepository.findById(courseId);
        if (course == null) {
            return null;
        }
        Enrollment newEnrollment = new Enrollment(0, studentName, courseId);
        return enrollmentRepository.create(newEnrollment);
    }

    @Override
    public Enrollment updateEnrollment(int id, String studentName, int courseId) {
        Course course = courseRepository.findById(courseId);
        if (course == null) {
            return null;
        }
        Enrollment updatedData = new Enrollment(0, studentName, courseId);
        return enrollmentRepository.update(id, updatedData);
    }

    @Override
    public Enrollment deleteEnrollmentById(int id) {
        return enrollmentRepository.deleteById(id);
    }
}
