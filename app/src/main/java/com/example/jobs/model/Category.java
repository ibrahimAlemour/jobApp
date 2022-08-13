package com.example.jobs.model;

public class Category {

    String id ;
    int img ;
    String jobTitle ;

    public Category(String id, int img, String jobTitle) {
        this.id = id;
        this.img = img;
        this.jobTitle = jobTitle;
    }

    public Category( int img, String jobTitle) {
        this.img = img;
        this.jobTitle = jobTitle;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }
}


