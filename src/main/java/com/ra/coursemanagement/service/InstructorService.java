package com.ra.coursemanagement.service;

import com.ra.coursemanagement.model.Instructor;
import com.ra.coursemanagement.repository.InstructorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InstructorService {

    private final InstructorRepository repository;

    public InstructorService(InstructorRepository repository) {
        this.repository = repository;
    }

    public List<Instructor> getAll() {
        return repository.findAll();
    }

    public Optional<Instructor> getById(Long id) {
        return repository.findById(id);
    }

    public Instructor create(Instructor instructor) {
        return repository.save(instructor);
    }

    public Instructor update(Long id, Instructor newData) {
        return repository.update(id, newData);
    }

    public boolean deleteById(Long id) {
        return repository.deleteById(id);
    }

    public Instructor findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Instructor not found with id: " + id));
    }
}