package com.example.jobs.model;

public class Archive {

    String id ;
    String nameJob ;
    String text ;

    public Archive(String id, String name, String text) {
        this.id = id;
        this.nameJob = name;
        this.text = text;
    }

    public Archive(String name, String text) {

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
