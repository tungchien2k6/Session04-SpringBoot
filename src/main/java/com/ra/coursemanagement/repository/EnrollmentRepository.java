package com.ra.coursemanagement.repository;

import com.ra.coursemanagement.model.StudentEnrollment;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.NoSuchElementException;

@Repository
public class EnrollmentRepository {

    private final List<StudentEnrollment> enrollments = new ArrayList<>();

    public EnrollmentRepository() {
        enrollments.add(new StudentEnrollment(1L, "Nguyễn Văn An", 1L));
        enrollments.add(new StudentEnrollment(2L, "Trần Thị Bình", 1L));
        enrollments.add(new StudentEnrollment(3L, "Lê Hoàng Cường", 2L));
        enrollments.add(new StudentEnrollment(4L, "Phạm Minh Đức", 2L));
    }

    public List<StudentEnrollment> findAll() {
        return new ArrayList<>(enrollments);
    }

    public Optional<StudentEnrollment> findById(Long id) {
        return enrollments.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst();
    }

    public StudentEnrollment save(StudentEnrollment enrollment) {
        if (enrollment.getId() == null) {
            long newId = enrollments.stream()
                    .mapToLong(StudentEnrollment::getId)
                    .max()
                    .orElse(0L) + 1;
            enrollment.setId(newId);
        }
        enrollments.add(enrollment);
        return enrollment;
    }

    public StudentEnrollment update(Long id, StudentEnrollment newData) {
        StudentEnrollment existing = findById(id)
                .orElseThrow(() -> new NoSuchElementException("Enrollment not found with id: " + id));
        existing.setStudentName(newData.getStudentName());
        existing.setCourseId(newData.getCourseId());
        return existing;
    }

    public boolean deleteById(Long id) {
        StudentEnrollment existing = findById(id)
                .orElseThrow(() -> new NoSuchElementException("Enrollment not found with id: " + id));
        return enrollments.remove(existing);
    }
}