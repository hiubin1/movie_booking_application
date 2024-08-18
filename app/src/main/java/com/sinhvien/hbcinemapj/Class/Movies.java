package com.sinhvien.hbcinemapj.Class;

import java.io.Serializable;

public class Movies implements Serializable {
    private long id;
    private String MovieName, Time, Date, Age,Desc,type,Director,Actors,Languge;
    private int pic,banner;

    public Movies() {
    }

    public Movies(long id, String movieName, String time, String date, String age, String desc, String type, String director, String actors, String languge, int pic, int banner) {
        this.id = id;
        MovieName = movieName;
        Time = time;
        Date = date;
        Age = age;
        Desc = desc;
        this.type = type;
        Director = director;
        Actors = actors;
        Languge = languge;
        this.pic = pic;
        this.banner = banner;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMovieName() {
        return MovieName;
    }

    public void setMovieName(String movieName) {
        MovieName = movieName;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }


    public int getPic() {
        return pic;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDirector() {
        return Director;
    }

    public void setDirector(String director) {
        Director = director;
    }

    public String getActors() {
        return Actors;
    }

    public void setActors(String actors) {
        Actors = actors;
    }

    public String getLanguge() {
        return Languge;
    }

    public void setLanguge(String languge) {
        Languge = languge;
    }

    public int getBanner() {
        return banner;
    }

    public void setBanner(int banner) {
        this.banner = banner;
    }
}
