package com.ra.coursemanagement.controller;

import com.ra.coursemanagement.dto.ApiResponse;
import com.ra.coursemanagement.model.StudentEnrollment;
import com.ra.coursemanagement.service.EnrollmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    private final EnrollmentService service;

    public EnrollmentController(EnrollmentService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<StudentEnrollment>>> getAll() {
        List<StudentEnrollment> data = service.getAll();
        return ResponseEntity.ok(ApiResponse.success("Fetched enrollments successfully", data));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<StudentEnrollment>> getById(@PathVariable Long id) {
        try {
            StudentEnrollment enrollment = service.findEnrollmentById(id);
            return ResponseEntity.ok(ApiResponse.success("Fetched enrollment successfully", enrollment));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.error(e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse<StudentEnrollment>> create(@RequestBody StudentEnrollment enrollment) {
        StudentEnrollment saved = service.create(enrollment);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Enrollment created successfully", saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<StudentEnrollment>> update(@PathVariable Long id, @RequestBody StudentEnrollment enrollment) {
        StudentEnrollment updated = service.update(id, enrollment);
        if (updated != null) {
            return ResponseEntity.ok(ApiResponse.success("Enrollment updated successfully", updated));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.error("Enrollment not found"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        boolean deleted = service.deleteById(id);
        if (deleted) {
            return ResponseEntity.ok(ApiResponse.success("Enrollment deleted successfully", null));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.error("Enrollment not found"));
    }
}