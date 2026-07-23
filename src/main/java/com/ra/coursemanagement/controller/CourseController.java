package com.ra.coursemanagement.controller;

import com.ra.coursemanagement.dto.ApiResponse;
import com.ra.coursemanagement.model.Course;
import com.ra.coursemanagement.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService service;

    public CourseController(CourseService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Course>>> getAll() {
        List<Course> data = service.getAll();
        return ResponseEntity.ok(ApiResponse.success("Fetched courses successfully", data));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Course>> create(@RequestBody Course course) {
        Course saved = service.create(course);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Course created successfully", saved));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Course>> getById(@PathVariable Long id) {
        try {
            Course course = service.findCourseById(id);
            return ResponseEntity.ok(ApiResponse.success("Fetched course successfully", course));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.error(e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Course>> update(@PathVariable Long id, @RequestBody Course course) {
        try {
            Course updated = service.update(id, course);
            return ResponseEntity.ok(ApiResponse.success("Course updated successfully", updated));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.error(e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        try {
            service.deleteById(id);
            return ResponseEntity.ok(ApiResponse.success("Course deleted successfully", null));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.error(e.getMessage()));
        }
    }
}