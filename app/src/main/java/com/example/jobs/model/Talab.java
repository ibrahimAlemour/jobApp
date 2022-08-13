package com.example.jobs.model;

public class Talab {

    String id ;
    String name ;
    int Status ;

    public Talab(String id, String name, int Status) {
        this.id = id;
        this.name = name;
        this.Status = Status;
    }

    public Talab(String name, int Status) {

        this.name = name;
        this.Status = Status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }
}
