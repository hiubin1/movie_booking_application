package com.sinhvien.hbcinemapj.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sinhvien.hbcinemapj.Class.AddNews;

public class AddNewsDatabase {
    SQLiteDatabase database;
    DBHelper helper;

    public AddNewsDatabase(Context context){
        helper = new DBHelper(context);
        database = helper.getWritableDatabase();
    }

    public Cursor layTatCaDuLieu(){
        String[] cot = {DBHelper.COT_NEWS_ID,
        DBHelper.COT_NEWS_NAME,
        DBHelper.COT_NEWS_BANNER,
        DBHelper.COT_NEWS_DATE,
        DBHelper.COT_NEWS_DESCRIPTION,
        DBHelper.COT_NEWS_RAP};
        Cursor cursor = null;
        cursor = database.query(DBHelper.TEN_BANG_NEWS, cot,null,null,null,null,null);
        return cursor;
    }

    public Cursor getDataFromID(long id){
        String[] cot = {String.valueOf(id)};

        Cursor cursor = null;
        String query = "select * from News where _NewsID =?";
        cursor = database.rawQuery(query,cot);
        return cursor;
    }

    public boolean KiemTraTinTuc(String nameNew, int pic){
        boolean KiemTraTinTuc = false;
        String[] cot = {nameNew, String.valueOf(pic)};
        String query = "select * from News where _NewsName = ? or _NewsBanner = ?";
        Cursor cursor = database.rawQuery(query,cot);
        if (cursor != null && cursor.moveToFirst() && cursor.getCount() > 0){
            KiemTraTinTuc = true;
        } else {
            KiemTraTinTuc = false;
        }
        cursor.close();
        return KiemTraTinTuc;
    }

    public boolean KiemTraEditTinTuc(String nameNews){
        boolean KiemTraEditTinTuc = false;
        String[] cot = {nameNews};
        String query = "select * from News where _NewsName = ?";
        Cursor cursor = database.rawQuery(query,cot);
        if (cursor != null && cursor.moveToFirst() && cursor.getCount() > 0){
            KiemTraEditTinTuc = true;
        } else {
            KiemTraEditTinTuc = false;
        }
        cursor.close();
        return KiemTraEditTinTuc;
    }

    public long addTinTuc(AddNews addNews){
        ContentValues values = new ContentValues();

        values.put(DBHelper.COT_NEWS_NAME, addNews.getAddNewsName());
        values.put(DBHelper.COT_NEWS_BANNER, addNews.getAddNewsPic());
        values.put(DBHelper.COT_NEWS_DESCRIPTION,addNews.getAddNewsContent());
        values.put(DBHelper.COT_NEWS_DATE,addNews.getAddNewsDate());
        values.put(DBHelper.COT_NEWS_RAP,addNews.getAddNewsRapApDung());

        return database.insert(DBHelper.TEN_BANG_NEWS,null,values);
    }

    public long EditTinTuc(AddNews addNews){
        ContentValues values = new ContentValues();

        values.put(DBHelper.COT_NEWS_NAME, addNews.getAddNewsName());
        values.put(DBHelper.COT_NEWS_DESCRIPTION,addNews.getAddNewsContent());
        values.put(DBHelper.COT_NEWS_DATE,addNews.getAddNewsDate());
        values.put(DBHelper.COT_NEWS_RAP,addNews.getAddNewsRapApDung());

        return database.update(DBHelper.TEN_BANG_NEWS, values,DBHelper.COT_NEWS_ID + " = "
                + addNews.getAddNewsID(),null);
    }

    public long delTinTuc(AddNews addNews){
        return database.delete(DBHelper.TEN_BANG_NEWS,
                DBHelper.COT_NEWS_ID + " = " + addNews.getAddNewsID(),
                null);
    }
}
