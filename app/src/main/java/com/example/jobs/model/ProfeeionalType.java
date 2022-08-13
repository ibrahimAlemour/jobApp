package com.example.jobs.model;

public class ProfeeionalType {

    String id ;
    int img ;
    String name ;
    String place ;
    String rate ;
    String avaliable ;

    public ProfeeionalType(String id, int img, String name, String place, String rate, String avaliable) {
        this.id = id;
        this.img = img;
        this.name = name;
        this.place = place;
        this.rate = rate;
        this.avaliable = avaliable;
    }

    public ProfeeionalType( int img, String name, String place, String rate, String avaliable) {
        this.img = img;
        this.name = name;
        this.place = place;
        this.rate = rate;
        this.avaliable = avaliable;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getAvaliable() {
        return avaliable;
    }

    public void setAvaliable(String avaliable) {
        this.avaliable = avaliable;
    }
}


