package com.ra.session05ex01.repository;

import com.ra.session05ex01.model.entity.Instructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Repository
public class InstructorRepository {

    private final List<Instructor> instructors = new ArrayList<>();
    private int nextId = 1;

    public InstructorRepository() {
        instructors.add(new Instructor(nextId++, "Nguyen Van A", "vana@vimaru.edu.vn"));
        instructors.add(new Instructor(nextId++, "Tran Thi B", "thib@vimaru.edu.vn"));
    }

    public List<Instructor> findAll() {
        return instructors;
    }

    public Optional<Instructor> findById(int id) {
        return instructors.stream().filter(i -> i.getId() == id).findFirst();
    }

    public Instructor create(Instructor instructor) {
        instructor.setId(nextId++);
        instructors.add(instructor);
        return instructor;
    }

    public Instructor update(int id, Instructor updatedInstructor) {
        Instructor existing = findById(id).orElseThrow(() -> new NoSuchElementException("Instructor không tồn tại với id: " + id));
        existing.setName(updatedInstructor.getName());
        existing.setEmail(updatedInstructor.getEmail());
        return existing;
    }

    public Instructor deleteById(int id) {
        Instructor  existing = findById(id).orElseThrow(() -> new NoSuchElementException("Instructor không tồn tại với id: " + id));
            instructors.remove(existing);
        return existing;
    }
}