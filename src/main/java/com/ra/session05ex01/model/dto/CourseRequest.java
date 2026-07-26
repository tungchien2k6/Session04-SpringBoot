package com.ra.session05ex01.model.dto;

public class CourseRequest {
    private String title;
    private String status;
    private int instructorId;

    public CourseRequest() {}

    public CourseRequest(String title, String status, int instructorId) {
        this.title = title;
        this.status = status;
        this.instructorId = instructorId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(int instructorId) {
        this.instructorId = instructorId;
    }
}