package com.sinhvien.hbcinemapj.Class;

import com.sinhvien.hbcinemapj.R;

import java.io.Serializable;
import java.util.ArrayList;

public class News implements Serializable {
    private long id;
    private String NewsName, NewsDate, NewsContent,NewsRapApDung;
    private int NewsImage;

    public News(long id, String newsName, String newsDate, String newsContent, String newsRapApDung, int newsImage) {
        this.id = id;
        NewsName = newsName;
        NewsDate = newsDate;
        NewsContent = newsContent;
        NewsRapApDung = newsRapApDung;
        NewsImage = newsImage;
    }

    public News() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNewsName() {
        return NewsName;
    }

    public void setNewsName(String newsName) {
        NewsName = newsName;
    }

    public String getNewsDate() {
        return NewsDate;
    }

    public void setNewsDate(String newsDate) {
        NewsDate = newsDate;
    }

    public int getNewsImage() {
        return NewsImage;
    }

    public void setNewsImage(int newsImage) {
        NewsImage = newsImage;
    }

    public String getNewsContent() {
        return NewsContent;
    }

    public void setNewsContent(String newsContent) {
        NewsContent = newsContent;
    }

    public String getNewsRapApDung() {
        return NewsRapApDung;
    }

    public void setNewsRapApDung(String newsRapApDung) {
        NewsRapApDung = newsRapApDung;
    }

}
