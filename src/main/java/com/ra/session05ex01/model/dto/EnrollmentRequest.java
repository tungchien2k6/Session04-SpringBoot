package com.ra.session05ex01.model.dto;

public class EnrollmentRequest {
    private String studentName;
    private Long courseId;

    public EnrollmentRequest() {
    }

    public EnrollmentRequest(String studentName, Long courseId) {
        this.studentName = studentName;
        this.courseId = courseId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
}
