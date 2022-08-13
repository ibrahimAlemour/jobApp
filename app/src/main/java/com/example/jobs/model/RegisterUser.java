package com.example.jobs.model;

public class RegisterUser {

    int id ;
    String name ;
    String email ;
    String phone ;
    String user_type ;
    String updated_at ;
    String created_at ;
    String token ;

    public RegisterUser(int id, String name, String email, String phone, String user_type, String updated_at, String created_at, String token) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.user_type = user_type;
        this.updated_at = updated_at;
        this.created_at = created_at;
        this.token = token;
    }

    public RegisterUser(int id, String name, String email, String phone, String user_type,  String token) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.user_type = user_type;

        this.token = token;
    }

    public RegisterUser(int id, String name, String email, String phone, String user_type) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.user_type = user_type;


    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
