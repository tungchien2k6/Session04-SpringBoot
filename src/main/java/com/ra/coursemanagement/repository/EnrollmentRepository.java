package com.ra.coursemanagement.repository;

import com.ra.coursemanagement.model.Enrollment;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.NoSuchElementException;

@Repository
public class EnrollmentRepository {

    private final List<Enrollment> enrollments = new ArrayList<>();

    public EnrollmentRepository() {
        enrollments.add(new Enrollment(1L, "Nguyễn Văn An", 1L));
        enrollments.add(new Enrollment(2L, "Trần Thị Bình", 1L));
        enrollments.add(new Enrollment(3L, "Lê Hoàng Cường", 2L));
        enrollments.add(new Enrollment(4L, "Phạm Minh Đức", 2L));
    }

    public List<Enrollment> findAll() {
        return new ArrayList<>(enrollments);
    }

    public Optional<Enrollment> findById(Long id) {
        return enrollments.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst();
    }

    public Enrollment save(Enrollment enrollment) {
        if (enrollment.getId() == null) {
            long newId = enrollments.stream()
                    .mapToLong(Enrollment::getId)
                    .max()
                    .orElse(0L) + 1;
            enrollment.setId(newId);
        }
        enrollments.add(enrollment);
        return enrollment;
    }

    public Enrollment update(Long id, Enrollment newData) {
        Enrollment existing = findById(id)
                .orElseThrow(() -> new NoSuchElementException("Enrollment not found with id: " + id));
        existing.setStudentName(newData.getStudentName());
        existing.setCourseId(newData.getCourseId());
        return existing;
    }

    public boolean deleteById(Long id) {
        Enrollment existing = findById(id)
                .orElseThrow(() -> new NoSuchElementException("Enrollment not found with id: " + id));
        return enrollments.remove(existing);
    }
}