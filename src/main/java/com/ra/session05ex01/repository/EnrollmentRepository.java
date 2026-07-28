package com.ra.session05ex01.repository;

import com.ra.session05ex01.model.entity.Enrollment;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Repository
public class EnrollmentRepository {
    private final List<Enrollment> enrollments = new ArrayList<>();
    private int nextId = 1;

    public List<Enrollment> findAll() {
        return enrollments;
    }

    public Optional<Enrollment> findById(int id) {
        return enrollments.stream().filter(e -> e.getId() == id).findFirst();
    }

    public Enrollment create(Enrollment enrollment) {
        enrollment.setId(nextId++);
        enrollments.add(enrollment);
        return enrollment;
    }

    public Enrollment update(int id, Enrollment updatedEnrollment) {
        Enrollment existing = findById(id).orElseThrow(() -> new NoSuchElementException("Enrollment không tồn tại với id: " + id));
        existing.setStudentName(updatedEnrollment.getStudentName());
        existing.setCourseId(updatedEnrollment.getCourseId());
        return existing;
    }

    public Enrollment deleteById(int id) {
        Enrollment existing = findById(id).orElseThrow(() -> new NoSuchElementException("Enrollment không tồn tại với id: " + id));
        enrollments.remove(existing);
        return existing;
    }
}
