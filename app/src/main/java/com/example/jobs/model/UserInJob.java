package com.example.jobs.model;


public class UserInJob {

    private int id;
    private String name;
    private String email;
    private Object email_verified_at;
    private String phone;
    private String user_type;
    private Object rating;
    private Object about_me;
    private Object lat;
    private Object lng;
    private Object photo;
    private int is_available;
    private Object created_at;
    private Object updated_at;
    private int city_id;
    private int district_id;
    private int job_title_id;
    private int job_type_id;


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Object getEmail_verified_at() {
        return email_verified_at;
    }

    public String getPhone() {
        return phone;
    }

    public String getUser_type() {
        return user_type;
    }

    public Object getRating() {
        return rating;
    }

    public Object getAbout_me() {
        return about_me;
    }

    public Object getLat() {
        return lat;
    }

    public Object getLng() {
        return lng;
    }

    public Object getPhoto() {
        return photo;
    }

    public int getIs_available() {
        return is_available;
    }

    public Object getCreated_at() {
        return created_at;
    }

    public Object getUpdated_at() {
        return updated_at;
    }

    public int getCity_id() {
        return city_id;
    }

    public int getDistrict_id() {
        return district_id;
    }

    public int getJob_title_id() {
        return job_title_id;
    }

    public int getJob_type_id() {
        return job_type_id;
    }
}
