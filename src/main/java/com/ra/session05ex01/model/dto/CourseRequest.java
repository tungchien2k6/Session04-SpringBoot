package com.ra.session05ex01.model.dto;

import com.ra.session05ex01.model.entity.CourseStatus;

public class CourseRequest {
    private String title;
    private CourseStatus status;
    private Long instructorId;

    public CourseRequest() {}

    public CourseRequest(String title, CourseStatus status, Long instructorId) {
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

    public CourseStatus getStatus() {
        return status;
    }

    public void setStatus(CourseStatus status) {
        this.status = status;
    }

    public Long getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(Long instructorId) {
        this.instructorId = instructorId;
    }
}