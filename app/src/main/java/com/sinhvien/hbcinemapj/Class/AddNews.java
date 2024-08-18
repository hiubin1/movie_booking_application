package com.sinhvien.hbcinemapj.Class;

public class AddNews {

    private int AddNewsPic;
    private long AddNewsID;
    private String AddNewsName,AddNewsDate,AddNewsContent,AddNewsRapApDung;

    public AddNews(int addNewsPic, long addNewsID, String addNewsName, String addNewsDate, String addNewsContent, String addNewsRapApDung) {
        AddNewsPic = addNewsPic;
        AddNewsID = addNewsID;
        AddNewsName = addNewsName;
        AddNewsDate = addNewsDate;
        AddNewsContent = addNewsContent;
        AddNewsRapApDung = addNewsRapApDung;
    }

    public AddNews() {
    }

    public int getAddNewsPic() {
        return AddNewsPic;
    }

    public void setAddNewsPic(int addNewsPic) {
        AddNewsPic = addNewsPic;
    }

    public long getAddNewsID() {
        return AddNewsID;
    }

    public void setAddNewsID(long addNewsID) {
        AddNewsID = addNewsID;
    }

    public String getAddNewsName() {
        return AddNewsName;
    }

    public void setAddNewsName(String addNewsName) {
        AddNewsName = addNewsName;
    }

    public String getAddNewsDate() {
        return AddNewsDate;
    }

    public void setAddNewsDate(String addNewsDate) {
        AddNewsDate = addNewsDate;
    }

    public String getAddNewsContent() {
        return AddNewsContent;
    }

    public void setAddNewsContent(String addNewsContent) {
        AddNewsContent = addNewsContent;
    }

    public String getAddNewsRapApDung() {
        return AddNewsRapApDung;
    }

    public void setAddNewsRapApDung(String addNewsRapApDung) {
        AddNewsRapApDung = addNewsRapApDung;
    }
}
