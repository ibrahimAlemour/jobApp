package com.example.jobs.model;


public class OwnerReceiveTalab {


    public int id;
    public int job_id;
    public int emp_user_id;
    public String description;
    public int price;
    public String created_at;
    public String updated_at;
    public JobDTO job;
    public UserDTO user;


    public static class JobDTO {
        public int id;
        public String title;
        public String description;
        public int emp_user_id;
        public int job_type_id;
        public int user_id;
        public int job_status_id;
        public int city_id;
        public int district_id;
        public Object lat;
        public Object lng;
        public String created_at;
        public String updated_at;
        public Boolean is_saved;
    }


    public static class UserDTO {
        public int id;
        public String name;
        public String email;
        public Object email_verified_at;
        public String phone;
        public String user_type;
        public double rating;
        public String about_me;
        private Object lat;
        private Object lng;
        private Object photo;
        public int is_available;
        public String created_at;
        public String updated_at;
        public int city_id;
        public int district_id;
        public int job_title_id;
        public int job_type_id;
    }
}
