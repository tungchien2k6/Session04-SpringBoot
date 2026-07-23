package com.ra.coursemanagement.service;

import com.ra.coursemanagement.model.StudentEnrollment;
import com.ra.coursemanagement.repository.EnrollmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;


@Service
public class EnrollmentService {

    private final EnrollmentRepository repository;

    public EnrollmentService(EnrollmentRepository repository) {
        this.repository = repository;
    }

    public List<StudentEnrollment> getAll() {
        return repository.findAll();
    }

    public StudentEnrollment create(StudentEnrollment enrollment) {
        return repository.save(enrollment);
    }

    public StudentEnrollment findEnrollmentById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Enrollment not found with id: " + id));
    }

    public StudentEnrollment update(Long id, StudentEnrollment newData) {
        return repository.update(id, newData);
    }

    public boolean deleteById(Long id) {
        return repository.deleteById(id);
    }
}