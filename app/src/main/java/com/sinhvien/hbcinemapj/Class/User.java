package com.sinhvien.hbcinemapj.Class;

public class User {
    private long id;
    private String _Name, _Pass, _Gender, _Email, _PhoneNum,_Birth;

    private int Pic;




    public User(long id, String _Name, String _Pass, String _Gender, String _Email, String _PhoneNum, String _Birth, int pic) {
        this.id = id;
        this._Name = _Name;
        this._Pass = _Pass;
        this._Gender = _Gender;
        this._Email = _Email;
        this._PhoneNum = _PhoneNum;
        this._Birth = _Birth;
        Pic = pic;
    }

    public User() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getPic() {
        return Pic;
    }

    public void setPic(int pic) {
        Pic = pic;
    }

    public String get_Name() {
        return _Name;
    }

    public void set_Name(String _Name) {
        this._Name = _Name;
    }

    public String get_Pass() {
        return _Pass;
    }

    public void set_Pass(String _Pass) {
        this._Pass = _Pass;
    }

    public String get_Gender() {
        return _Gender;
    }

    public void set_Gender(String _Gender) {
        this._Gender = _Gender;
    }

    public String get_Email() {
        return _Email;
    }

    public void set_Email(String _Email) {
        this._Email = _Email;
    }

    public String get_PhoneNum() {
        return _PhoneNum;
    }

    public void set_PhoneNum(String _PhoneNum) {
        this._PhoneNum = _PhoneNum;
    }

    public String get_Birth() {
        return _Birth;
    }

    public void set_Birth(String _Birth) {
        this._Birth = _Birth;
    }



}
