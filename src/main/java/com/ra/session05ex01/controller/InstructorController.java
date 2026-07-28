package com.ra.session05ex01.controller;

import com.ra.session05ex01.model.dto.ApiResponse;
import com.ra.session05ex01.model.dto.InstructorRequest;
import com.ra.session05ex01.model.entity.Instructor;
import com.ra.session05ex01.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@Controller
@RequestMapping("/api/v1/instructors")
public class InstructorController {

    private final InstructorService instructorService;

    @Autowired
    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Instructor>>> getAllInstructors() {
        List<Instructor> instructors = instructorService.getAllInstructors();
        return ResponseEntity.ok(ApiResponse.success("Lấy danh sách giảng viên thành công",  instructors));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Instructor>> getInstructorById(@PathVariable Long id) {
        try {
            Instructor instructor = instructorService.getInstructorById(id);
            return ResponseEntity.ok(ApiResponse.success("Lấy thông tin giảng viên thành công",instructor));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.error(e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Instructor>> createInstructor(@RequestBody InstructorRequest request) {
        Instructor created = instructorService.createInstructor(request.getName(), request.getEmail());
        if (created == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.error("Email đã tồn tại"));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success("Tạo giảng viên thành công", created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Instructor>> updateInstructor(@PathVariable Long id, @RequestBody InstructorRequest request) {
        Instructor updated = instructorService.updateInstructor(id, request.getName(), request.getEmail());
        if (updated == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.error("Không tìm thấy giảng viên hoặc email đã tồn tại"));
        }
        return ResponseEntity.ok(ApiResponse.success("Cập nhật giảng viên thành công", updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteInstructor(@PathVariable Long id) {
        Instructor deleted = instructorService.deleteInstructorById(id);
        if (deleted == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.error("Không tìm thấy giảng viên với id: " + id));
        }
        return ResponseEntity.ok(ApiResponse.success("Xóa giảng viên thành công", null));
    }
}