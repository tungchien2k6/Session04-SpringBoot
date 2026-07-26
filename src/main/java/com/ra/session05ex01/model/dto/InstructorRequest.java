package com.ra.session05ex01.model.dto;

public class InstructorRequest {
    private String name;
    private String email;

    public InstructorRequest() {}

    public InstructorRequest(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}