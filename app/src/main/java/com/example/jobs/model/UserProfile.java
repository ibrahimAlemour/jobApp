package com.example.jobs.model;


public class UserProfile {

    public int id;
    public String name;
    public String email;
    public Object email_verified_at;
    public String phone;
    public String user_type;
    public Object rating;
    public String about_me;
    public Object lat;
    public Object lng;
    public Object photo;
    public int is_available;
    public Object created_at;
    public String updated_at;
    public int city_id;
    public int district_id;
    public Object job_title_id;
    public int job_type_id;

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getUser_type() {
        return user_type;
    }
}
