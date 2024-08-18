package com.sinhvien.hbcinemapj.Class;

import android.net.Uri;

import java.io.Serializable;

public class AddMovies implements Serializable {

    private String AddMovieName, AddMovieType, AddMovieAge, AddMovieLength, AddMovieReleasedDate,
            AddMovieDirector, AddMoiveActors, AddMovieLanguage, AddMovieDesc;
    private int AddMoviePic;
    private int AddMoiveBanner;
    private long AddMovieID;


    public AddMovies(String addMovieName, String addMovieType, String addMovieAge, String addMovieLength, String addMovieReleasedDate, String addMovieDirector, String addMoiveActors, String addMovieLanguage, String addMovieDesc, int addMoviePic, int addMoiveBanner, long addMovieID) {
        AddMovieName = addMovieName;
        AddMovieType = addMovieType;
        AddMovieAge = addMovieAge;
        AddMovieLength = addMovieLength;
        AddMovieReleasedDate = addMovieReleasedDate;
        AddMovieDirector = addMovieDirector;
        AddMoiveActors = addMoiveActors;
        AddMovieLanguage = addMovieLanguage;
        AddMovieDesc = addMovieDesc;
        AddMoviePic = addMoviePic;
        AddMoiveBanner = addMoiveBanner;
        AddMovieID = addMovieID;
    }

    public AddMovies() {
    }

    public String getAddMovieName() {
        return AddMovieName;
    }

    public void setAddMovieName(String addMovieName) {
        AddMovieName = addMovieName;
    }

    public String getAddMovieType() {
        return AddMovieType;
    }

    public void setAddMovieType(String addMovieType) {
        AddMovieType = addMovieType;
    }

    public String getAddMovieAge() {
        return AddMovieAge;
    }

    public void setAddMovieAge(String addMovieAge) {
        AddMovieAge = addMovieAge;
    }

    public String getAddMovieLength() {
        return AddMovieLength;
    }

    public void setAddMovieLength(String addMovieLength) {
        AddMovieLength = addMovieLength;
    }

    public String getAddMovieReleasedDate() {
        return AddMovieReleasedDate;
    }

    public void setAddMovieReleasedDate(String addMovieReleasedDate) {
        AddMovieReleasedDate = addMovieReleasedDate;
    }

    public int getAddMoviePic() {
        return AddMoviePic;
    }

    public void setAddMoviePic(int addMoviePic) {
        AddMoviePic = addMoviePic;
    }

    public String getAddMovieDirector() {
        return AddMovieDirector;
    }

    public void setAddMovieDirector(String addMovieDirector) {
        AddMovieDirector = addMovieDirector;
    }

    public String getAddMoiveActors() {
        return AddMoiveActors;
    }

    public void setAddMoiveActors(String addMoiveActors) {
        AddMoiveActors = addMoiveActors;
    }

    public String getAddMovieLanguage() {
        return AddMovieLanguage;
    }

    public void setAddMovieLanguage(String addMovieLanguage) {
        AddMovieLanguage = addMovieLanguage;
    }

    public String getAddMovieDesc() {
        return AddMovieDesc;
    }

    public void setAddMovieDesc(String addMovieDesc) {
        AddMovieDesc = addMovieDesc;
    }

    public int getAddMoiveBanner() {
        return AddMoiveBanner;
    }

    public void setAddMoiveBanner(int addMoiveBanner) {
        AddMoiveBanner = addMoiveBanner;
    }

    public long getAddMovieID() {
        return AddMovieID;
    }

    public void setAddMovieID(long addMovieID) {
        AddMovieID = addMovieID;
    }

}
