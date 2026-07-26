package com.ra.session05ex01.repository;

import com.ra.session05ex01.model.entity.Course;
import com.ra.session05ex01.model.entity.Enrollment;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EnrollmentRepository {
    private final List<Enrollment> enrollments = new ArrayList<>();
    private int nextId = 1;

    public EnrollmentRepository() {
        enrollments.add(new Enrollment(nextId++, "Nguyen Van A", 1));
        enrollments.add(new Enrollment(nextId++, "Nguyen Van B", 1));
        enrollments.add(new Enrollment(nextId++, "Nguyen Van C", 2));
    }

    public List<Enrollment> findAll() {
        return enrollments;
    }

    public Enrollment findById(int id) {
        for (Enrollment enrollment : enrollments) {
            if (enrollment.getId() == id) {
                return enrollment;
            }
        }
        return null;
    }

    public Enrollment create(Enrollment enrollment) {
        enrollment.setId(nextId++);
        enrollments.add(enrollment);
        return enrollment;
    }

    public Enrollment update(int id, Enrollment updatedEnrollment) {
        Enrollment existing = findById(id);
        if (existing == null) {
            return null;
        }
        existing.setStudentName(updatedEnrollment.getStudentName());
        existing.setCourseId(updatedEnrollment.getCourseId());
        return existing;
    }

    public Enrollment deleteById(int id) {
        Enrollment existing = findById(id);
        if (existing != null) {
            enrollments.remove(existing);
        }
        return existing;
    }
}
