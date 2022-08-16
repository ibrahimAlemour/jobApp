package com.example.jobs.model;


public class District {

    private int id;
    private String name;
    private int city_id;
    private String created_at;
    private String updated_at;

    public int getCity_id() {
        return city_id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }
}
