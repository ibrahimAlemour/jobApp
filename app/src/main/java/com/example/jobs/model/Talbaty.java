package com.example.jobs.model;

public class Talbaty {

    String id ;
    String status ;
    String jobTitle ;

    public Talbaty(String id, String status, String jobTitle) {
        this.id = id;
        this.status = status;
        this.jobTitle = jobTitle;
    }

    public Talbaty(String status, String jobTitle) {
        this.status = status;
        this.jobTitle = jobTitle;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }
}


