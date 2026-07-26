package com.ra.session05ex01.model.dto;

public class EnrollmentRequest {
    private String studentName;
    private int courseId;

    public EnrollmentRequest() {
    }

    public EnrollmentRequest(String studentName, int courseId) {
        this.studentName = studentName;
        this.courseId = courseId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
}
