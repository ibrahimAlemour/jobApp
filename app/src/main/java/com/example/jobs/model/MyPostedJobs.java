package com.example.jobs.model;


public class MyPostedJobs {

    public int id;
    public String title;
    public String description;
    public int emp_user_id;
    public int job_type_id;
    public int user_id;
    public int job_status_id;
    public int city_id;
    public int district_id;
    public Double lat;
    public Double lng;
    public String created_at;
    public String updated_at;
    public Boolean is_saved;
    public int applicants_count;
    public Boolean is_applied_to_job;
    public CityDTO city;
    public DistrictDTO district;
    public JobStatusDTO job_status;
    public JobEmployeeDTO job_employee;


    public static class CityDTO {
        public int id;
        public String name;
        public String created_at;
        public String updated_at;
    }


    public static class DistrictDTO {
        public int id;
        public String name;
        public int city_id;
        public String created_at;
        public String updated_at;
    }


    public static class JobStatusDTO {
        public int id;
        public String name;
        public String created_at;
        public String updated_at;
    }


    public static class JobEmployeeDTO {
        public int id;
        public String name;
        public String email;
        public Object email_verified_at;
        public String phone;
        public String user_type;
        public double rating;
        public String about_me;
        public Object lat;
        public Object lng;
        public Object photo;
        public int is_available;
        public String created_at;
        public String updated_at;
        public int city_id;
        public int district_id;
        public int job_title_id;
        public int job_type_id;
    }
}
