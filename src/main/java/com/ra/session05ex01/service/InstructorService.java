package com.ra.session05ex01.service;

import com.ra.session05ex01.model.entity.Instructor;

import java.util.List;

public interface InstructorService {
    List<Instructor> getAllInstructors();
    Instructor getInstructorById(Long id);
    Instructor createInstructor(String name, String email);
    Instructor updateInstructor(Long id, String name, String email);
    Instructor deleteInstructorById(Long id);
}
