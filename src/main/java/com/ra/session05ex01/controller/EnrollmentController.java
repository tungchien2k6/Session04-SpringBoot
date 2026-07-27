package com.ra.session05ex01.controller;

import com.ra.session05ex01.model.dto.ApiResponse;
import com.ra.session05ex01.model.dto.EnrollmentRequest;
import com.ra.session05ex01.model.entity.Enrollment;
import com.ra.session05ex01.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@Controller
@RequestMapping("/api/v1/enrollments")
public class EnrollmentController {
    private final EnrollmentService enrollmentService;

    @Autowired
    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Enrollment>>> getAllEnrollments() {
        List<Enrollment> enrollments = enrollmentService.getAllEnrollments();
        return ResponseEntity.ok(ApiResponse.success("Lấy danh sách đăng ký thành công", enrollments));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Enrollment>> getEnrollmentById(@PathVariable int id) {
        try {
            Enrollment enrollment = enrollmentService.getEnrollmentById(id);
            return ResponseEntity.ok(ApiResponse.success("Lấy thông tin đăng ký thành công",enrollment));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.error(e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Enrollment>> createEnrollment(@RequestBody EnrollmentRequest request) {
        Enrollment created = enrollmentService.createEnrollment(request.getStudentName(), request.getCourseId());
        if (created == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.error("Không tìm thấy khóa học hoặc instructorId không hợp lệ"));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success("Tạo đăng ký thành công", created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Enrollment>> updateEnrollment(@PathVariable int id, @RequestBody EnrollmentRequest request) {
        Enrollment updated = enrollmentService.updateEnrollment(id, request.getStudentName(), request.getCourseId());
        if (updated == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.error("Không tìm thấy đăng ký hoặc instructorId không hợp lệ"));
        }
        return ResponseEntity.ok(ApiResponse.success("Cập nhật đăng ký thành công", updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteEnrollment(@PathVariable int id) {
        Enrollment deleted = enrollmentService.deleteEnrollmentById(id);
        if (deleted == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.error("Không tìm thấy đăng ký với id: " + id));
        }
        return ResponseEntity.ok(ApiResponse.success("Xóa đăng ký thành công", null));
    }
}
