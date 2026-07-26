package com.ra.session05ex01.repository;

import com.ra.session05ex01.model.entity.Instructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InstructorRepository {

    private final List<Instructor> instructors = new ArrayList<>();

    public InstructorRepository() {
        instructors.add(new Instructor(1, "Nguyen Van A", "vana@vimaru.edu.vn"));
        instructors.add(new Instructor(2, "Tran Thi B", "thib@vimaru.edu.vn"));
    }

    public List<Instructor> findAll() {
        return instructors;
    }

    public Instructor findById(int id) {
        for (Instructor instructor : instructors) {
            if (instructor.getId() == id) {
                return instructor;
            }
        }
        return null;
    }
}