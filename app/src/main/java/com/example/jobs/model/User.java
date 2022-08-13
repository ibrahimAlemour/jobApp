package com.example.jobs.model;

public class User {

    public UserData user;
    public String token;

    public static class UserData {

        public UserData(String name, String email, String phone, String user_type, String password, String confirmPassword) {
            this.name = name;
            this.email = email;
            this.phone = phone;
            this.user_type = user_type;
            this.password = password;
            this.confirmPassword = confirmPassword;
        }

        public int id;
        public String name;
        public String email;
        private Object email_verified_at;
        public String phone;
        public String password;
        public String confirmPassword;
        public String user_type;
        private Object rating;
        private Object about_me;
        private Object lat;
        private Object lng;
        private Object photo;
        private int is_available;
        private String created_at;
        private String updated_at;
        private Object city_id;
        private Object district_id;
        private Object job_title_id;
        private Object job_type_id;
    }
}
