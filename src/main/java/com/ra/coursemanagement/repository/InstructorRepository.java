package com.ra.coursemanagement.repository;

import com.ra.coursemanagement.model.Instructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Repository
public class InstructorRepository {

    private final List<Instructor> instructors = new ArrayList<>();

    public InstructorRepository() {
        instructors.add(new Instructor(1L, "Dr. Alice Smith", "alice.smith@university.edu"));
        instructors.add(new Instructor(2L, "Prof. Bob Johnson", "bob.johnson@university.edu"));
        instructors.add(new Instructor(3L, "Assoc. Prof. Carol Williams", "carol.williams@university.edu"));
    }

    public List<Instructor> findAll() {
        return new ArrayList<>(instructors);
    }

    public Optional<Instructor> findById(Long id) {
        return instructors.stream()
                .filter(i -> i.getId().equals(id))
                .findFirst();
    }

    public Instructor save(Instructor instructor) {
        if (instructor.getId() == null) {
            long newId = instructors.stream()
                    .mapToLong(Instructor::getId)
                    .max()
                    .orElse(0L) + 1;
            instructor.setId(newId);
        }
        instructors.add(instructor);
        return instructor;
    }

    public Instructor update(Long id, Instructor newData) {
        Instructor existing = findById(id)
                .orElseThrow(() -> new NoSuchElementException("Instructor not found with id: " + id));
        existing.setName(newData.getName());
        existing.setEmail(newData.getEmail());
        return existing;
    }

    public boolean deleteById(Long id) {
        Instructor existing = findById(id)
                .orElseThrow(() -> new NoSuchElementException("Instructor not found with id: " + id));
        return instructors.remove(existing);
    }
}