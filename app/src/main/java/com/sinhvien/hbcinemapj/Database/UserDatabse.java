package com.sinhvien.hbcinemapj.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sinhvien.hbcinemapj.Class.AddMovies;
import com.sinhvien.hbcinemapj.Class.User;

public class UserDatabse {

    SQLiteDatabase database;
    DBHelper helper;

    public UserDatabse(Context context){
        helper = new DBHelper(context);
        database = helper.getWritableDatabase();

    }

    public Cursor layTatCaDuLieu(){
        String[] cot = {DBHelper.COT_ID_CUS,
        DBHelper.COT_NAME,
        DBHelper.COT_PASS,
        DBHelper.COT_GENDER,
        DBHelper.COT_EMAIL,
        DBHelper.COT_PHONE_NUM,
        DBHelper.COT_BIRTH,
        DBHelper.COT_IMAGE_USER};

        Cursor cursor = null;

        cursor = database.query(DBHelper.TEN_BANG_USER, cot, null,null,null,null,DBHelper.COT_ID_CUS + " DESC");

        return cursor;
    }


    public boolean KiemTraDKEmail(String email){
        boolean KiemTraDK = false;
        String [] cot = {email};
        String query = "select * from User where _Email = ?";
        Cursor cursor = database.rawQuery(query,cot);
        if (cursor != null && cursor.moveToFirst() && cursor.getCount() > 0){
            KiemTraDK = true;
        } else {
            KiemTraDK = false;
        }
        return KiemTraDK;
    }


    public boolean KiemTraDKPhone(String phone){
        boolean KiemTraDK = false;
        String [] cot = {phone};
        String query = "select * from User where _PhoneNum = ?";
        Cursor cursor = database.rawQuery(query,cot);
        if (cursor != null && cursor.moveToFirst() && cursor.getCount() > 0){
            KiemTraDK = true;
        } else {
            KiemTraDK = false;
        }
        return KiemTraDK;
    }




    public  boolean KiemTraDN(String email, String pass){
        boolean KiemtraDN = false;
        String[] COT = {email,pass};
        String query = "select * from User where _Email = ? and _Pass = ?";
        Cursor cursor = database.rawQuery(query,COT);
        if(cursor != null && cursor.moveToFirst() && cursor.getCount() > 0){
            KiemtraDN = true;
        } else {
            KiemtraDN = false;
        }
        cursor.close();
        return KiemtraDN;
    }

    public Cursor GetNameFromEmail(String email){
        String[] COT = {email};
        String query = "select _Name from User where _Email = ?";
        Cursor cursor = database.rawQuery(query,COT);
        return cursor;
    }

    public Cursor GetUserFromEmail(String email){
        String[] COT = {email};
        String query = "select * from User where _Email = ?";
        Cursor cursor = database.rawQuery(query,COT);
        return cursor;
    }

    public Cursor GetPicFromGender(String gender){
        String[] cot = {gender};
        String query = "select _ImageUser from User where _Gender = ?";
        Cursor cursor = database.rawQuery(query,cot);
        return cursor;
    }


    public long them(User user){
        ContentValues values = new ContentValues();
        values.put(DBHelper.COT_NAME,
                user.get_Name());

        values.put(DBHelper.COT_PASS,
                user.get_Pass());

        values.put(DBHelper.COT_GENDER,
                user.get_Gender());

        values.put(DBHelper.COT_EMAIL,
                user.get_Email());

        values.put(DBHelper.COT_PHONE_NUM,
                user.get_PhoneNum());

        values.put(DBHelper.COT_BIRTH,
                user.get_Birth());

        values.put(DBHelper.COT_IMAGE_USER,
                user.getPic());



        return database.insert(DBHelper.TEN_BANG_USER,null,values);
    }

    public long sua(User user){
        ContentValues values = new ContentValues();
        values.put(DBHelper.COT_NAME,
                user.get_Name());

        values.put(DBHelper.COT_GENDER,
                user.get_Gender());

        values.put(DBHelper.COT_PHONE_NUM,
                user.get_PhoneNum());

        values.put(DBHelper.COT_BIRTH,
                user.get_Birth());



        return database.update(DBHelper.TEN_BANG_USER, values,DBHelper.COT_ID_CUS + " = "
                                + user.getId(),null);
    }

    public int UpdatePass(String email, String pass){
        ContentValues values = new ContentValues();
        values.put("_Pass", pass);
        return database.update(DBHelper.TEN_BANG_USER, values,DBHelper.COT_EMAIL + "=?",new String[]{email});
    }

    public long delAcc(User user) {
        return database.delete(DBHelper.TEN_BANG_USER,
                DBHelper.COT_ID_CUS + " = " + "'" + user.getId() + "'",
                null);
    }
}
