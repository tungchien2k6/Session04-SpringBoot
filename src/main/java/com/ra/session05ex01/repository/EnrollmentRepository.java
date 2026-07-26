package com.ra.session05ex01.repository;

import com.ra.session05ex01.model.entity.Enrollment;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EnrollmentRepository {
    private final List<Enrollment> enrollments = new ArrayList<>();

    public EnrollmentRepository() {
        enrollments.add(new Enrollment(1, "Nguyen Van A", 1));
        enrollments.add(new Enrollment(2, "Nguyen Van B", 1));
        enrollments.add(new Enrollment(3, "Nguyen Van C", 2));
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
}
