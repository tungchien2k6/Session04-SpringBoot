package com.ra.coursemanagement.controller;

import com.ra.coursemanagement.dto.ApiResponse;
import com.ra.coursemanagement.model.Instructor;
import com.ra.coursemanagement.service.InstructorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/instructors")
public class InstructorController {

    private final InstructorService service;

    public InstructorController(InstructorService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Instructor>>> getAll() {
        return ResponseEntity.ok(ApiResponse.success("Fetched instructors successfully", service.getAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Instructor>> getById(@PathVariable Long id) {
        try {
            Instructor instructor = service.findById(id);
            return ResponseEntity.ok(ApiResponse.success("Fetched instructor successfully", instructor));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error(e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Instructor>> create(@RequestBody Instructor instructor) {
        Instructor saved = service.create(instructor);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Instructor created successfully", saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Instructor>> update(@PathVariable Long id, @RequestBody Instructor instructor) {
        try {
            Instructor updated = service.update(id, instructor);
            return ResponseEntity.ok(ApiResponse.success("Instructor updated successfully", updated));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error(e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        try {
            service.deleteById(id);
            return ResponseEntity.ok(ApiResponse.success("Instructor deleted successfully", null));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error(e.getMessage()));
        }
    }
}