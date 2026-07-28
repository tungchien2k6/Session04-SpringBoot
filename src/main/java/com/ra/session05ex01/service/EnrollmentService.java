package com.ra.session05ex01.service;

import com.ra.session05ex01.model.entity.Enrollment;

import java.util.List;

public interface EnrollmentService {
    List<Enrollment> getAllEnrollments();
    Enrollment getEnrollmentById(int id);
    Enrollment createEnrollment(String studentName, Long courseId);
    Enrollment updateEnrollment(int id, String studentName, Long courseId);
    Enrollment deleteEnrollmentById(int id);
}
