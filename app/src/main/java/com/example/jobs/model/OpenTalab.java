package com.example.jobs.model;

public class OpenTalab {

    String id ;
    String nameJob ;
    String text ;

    public OpenTalab(String id, String name, String text) {
        this.id = id;
        this.nameJob = name;
        this.text = text;
    }

    public OpenTalab(String name, String text) {

        this.nameJob = name;
        this.text = text;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return nameJob;
    }

    public void setName(String name) {
        this.nameJob = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
