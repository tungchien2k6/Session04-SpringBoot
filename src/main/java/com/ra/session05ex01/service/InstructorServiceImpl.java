package com.ra.session05ex01.service;

import com.ra.session05ex01.model.entity.Instructor;
import com.ra.session05ex01.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class InstructorServiceImpl implements InstructorService {

    private final InstructorRepository instructorRepository;

    @Autowired
    public InstructorServiceImpl(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }

    @Override
    public List<Instructor> getAllInstructors() {
        return instructorRepository.findAll();
    }

    @Override
    public Instructor getInstructorById(Long id) {
        return instructorRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Không tìm thấy giảng viên với id: " + id));
    }

    @Override
    public Instructor createInstructor(String name, String email) {
        boolean emailExists = instructorRepository.findAll().stream()
                .anyMatch(i -> i.getEmail().equalsIgnoreCase(email));
        if (emailExists) {
            return null;
        }
        Instructor newInstructor = new Instructor(null, name, email);
        return instructorRepository.save(newInstructor);
    }

    @Override
    public Instructor updateInstructor(Long id, String name, String email) {
        Instructor existing = instructorRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Không tìm thấy giảng viên với id: " + id));

        boolean emailUsedByOther = instructorRepository.findAll().stream()
                .anyMatch(i -> i.getEmail().equalsIgnoreCase(email));
        if (emailUsedByOther) {
            return null;
        }

        existing.setName(name);
        existing.setEmail(email);
        return instructorRepository.save(existing);
    }

    @Override
    public Instructor deleteInstructorById(Long id) {
        Instructor existing = instructorRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Không tìm thấy giảng viên với id: " + id));
        instructorRepository.deleteById(id);
        return existing;
    }
}