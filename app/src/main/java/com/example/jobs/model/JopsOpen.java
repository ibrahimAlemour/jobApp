package com.example.jobs.model;


public class JopsOpen {

    public int id;
    public String title;
    public String description;
    public Object emp_user_id;
    public int job_type_id;
    public int user_id;
    public int job_status_id;
    public int city_id;
    public int district_id;
    public Double lat;
    public Double lng;
    public String created_at;
    public String updated_at;
    public CityDTO city;
    public DistrictDTO district;


    public static class CityDTO {
        public int id;
        public String name;
        public Object created_at;
        public Object updated_at;
    }


    public static class DistrictDTO {
        public int id;
        public String name;
        public int city_id;
        public Object created_at;
        public Object updated_at;
    }
}
