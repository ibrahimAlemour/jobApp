package com.example.jobs.model;


public class EmpSendTalab {


    public int id;
    public int job_id;
    public int emp_user_id;
    public String description;
    public int price;
    public String created_at;
    public String updated_at;
    public JobDTO job;

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
}
