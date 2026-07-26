package com.ra.session05ex01.service;

import com.ra.session05ex01.model.entity.Instructor;
import com.ra.session05ex01.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public Instructor getInstructorById(int id) {
        return instructorRepository.findById(id);
    }

    @Override
    public Instructor createInstructor(String name, String email) {
        Instructor newInstructor = new Instructor(0, name, email);
        return instructorRepository.create(newInstructor);
    }

    @Override
    public Instructor updateInstructor(int id, String name, String email) {
        Instructor updatedData = new Instructor(0, name, email);
        return instructorRepository.update(id, updatedData);
    }

    @Override
    public Instructor deleteInstructorById(int id) {
        return instructorRepository.deleteById(id);
    }
}