package com.example.jobs.model;

public class Login {

    private String token;
    private String status;
    private String errors;

    public String getErrors() {
        return errors;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
