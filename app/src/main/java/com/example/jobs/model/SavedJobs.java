package com.example.jobs.model;


public class SavedJobs {

    public int id;
    public int user_id;
    public int job_id;
    public String created_at;
    public String updated_at;
    public JobDTO job;


    public static class JobDTO {
        private int id;
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
    }
}
