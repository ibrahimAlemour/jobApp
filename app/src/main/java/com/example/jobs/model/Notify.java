package com.example.jobs.model;

public class Notify {

    String id ;
    String name ;
    String text ;

    public Notify(String id, String name, String text) {
        this.id = id;
        this.name = name;
        this.text = text;
    }

    public Notify( String name, String text) {

        this.name = name;
        this.text = text;
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
