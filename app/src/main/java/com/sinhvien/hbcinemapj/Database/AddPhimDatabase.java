package com.sinhvien.hbcinemapj.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sinhvien.hbcinemapj.Class.AddMovies;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddPhimDatabase {

    SQLiteDatabase database;
    DBHelper helper;

    public AddPhimDatabase(Context context) {
        helper = new DBHelper(context);
        database = helper.getWritableDatabase();
    }

    public Cursor layTatCaDuLieu() {
        String[] cot = {DBHelper.COT_ID,
                DBHelper.COT_TEN_PHIM,
                DBHelper.COT_TYPE_MOVIE,
                DBHelper.COT_MOVIE_IMAGE,
                DBHelper.COT_AGE,
                DBHelper.COT_MOVIELENGTH,
                DBHelper.COT_RELEASED_DATE,
                DBHelper.COT_DIRECTOR,
                DBHelper.COT_ACTORS,
                DBHelper.COT_LANGUAGE,
                DBHelper.COT_DESCRIPTION,
                DBHelper.COT_BANNER};
        Cursor cursor = null;
        cursor = database.query(DBHelper.TEN_BANG_MOVIES, cot, null, null, null, null, null);
        return cursor;
    }

    public Cursor layDataTheoID(long id) {
        String[] cot = {String.valueOf(id)};

        Cursor cursor = null;
        String query = "select * from Movies where _IDPhim =?";
        cursor = database.rawQuery(query, cot);
        return cursor;
    }



    // phương thức strftime() là dùng để format lại dịnh dạng date ví dụ &Y-%m-%d có định dạng là yyyy-mm-dd
    // 'now' là lấy thời gian hiện tại
    // hàm GetDataFormDateAfter() để lấy nhựng bộ phim có ngày chiếu lớn hơn ngày hiện tại (ở fragment sắp chiếu)
    public Cursor GetDataFormDateAfter(){


        Cursor cursor = null;
        String query = "select * from Movies where _ReleasedDate > strftime('%Y-%m-%d', 'now')";
        cursor = database.rawQuery(query,null);
        return cursor;
    }

    // hàm GetDataFormDateBefore() để lấy những bộ phim có ngày chiếu trước hoặc bằng ngày hiện tại ( ở fragment đang chiếu)
    public Cursor GetDataFormDateBefore(){

        Cursor cursor = null;
        String query = "select * from Movies where _ReleasedDate <= strftime('%Y-%m-%d', 'now')";
        cursor = database.rawQuery(query,null);
        return cursor;
    }

    public boolean KiemTraPhim(String nameMovie, int pic, int banner){
        boolean KiemTraPhim = false;
        String[] cot = {nameMovie, String.valueOf(pic), String.valueOf(banner)};
        String query = "select * from Movies where _MovieName = ? or _MovieImage = ? or _Banner = ?";
        Cursor cursor = database.rawQuery(query,cot);
        if (cursor != null && cursor.moveToFirst() && cursor.getCount() > 0){
            KiemTraPhim = true;
        } else {
            KiemTraPhim = false;
        }
        cursor.close();
        return KiemTraPhim;
    }

    public boolean KiemTraEditPhim(String nameMovie){
        boolean KiemTraEditPhim = false;
        String[] cot = {nameMovie};
        String query = "select * from Movies where _MovieName = ?";
        Cursor cursor = database.rawQuery(query,cot);
        if (cursor != null && cursor.moveToFirst() && cursor.getCount() > 0){
            KiemTraEditPhim = true;
        } else {
            KiemTraEditPhim = false;
        }
        cursor.close();
        return KiemTraEditPhim;
    }



    public long addPhim(AddMovies addMovies) {
        ContentValues values = new ContentValues();

        values.put(DBHelper.COT_TEN_PHIM, addMovies.getAddMovieName());
        values.put(DBHelper.COT_MOVIE_IMAGE, addMovies.getAddMoviePic());
        values.put(DBHelper.COT_TYPE_MOVIE, addMovies.getAddMovieType());
        values.put(DBHelper.COT_AGE, addMovies.getAddMovieAge());
        values.put(DBHelper.COT_MOVIELENGTH, addMovies.getAddMovieLength());
        values.put(DBHelper.COT_RELEASED_DATE, addMovies.getAddMovieReleasedDate());
        values.put(DBHelper.COT_DIRECTOR, addMovies.getAddMovieDirector());
        values.put(DBHelper.COT_ACTORS, addMovies.getAddMoiveActors());
        values.put(DBHelper.COT_DESCRIPTION, addMovies.getAddMovieDesc());
        values.put(DBHelper.COT_LANGUAGE, addMovies.getAddMovieLanguage());
        values.put(DBHelper.COT_BANNER, addMovies.getAddMoiveBanner());


        return database.insert(DBHelper.TEN_BANG_MOVIES, null, values);
    }

    public long EditPhim(AddMovies addMovies){
        ContentValues values = new ContentValues();
        values.put(DBHelper.COT_TEN_PHIM, addMovies.getAddMovieName());
        values.put(DBHelper.COT_TYPE_MOVIE, addMovies.getAddMovieType());
        values.put(DBHelper.COT_AGE, addMovies.getAddMovieAge());
        values.put(DBHelper.COT_MOVIELENGTH, addMovies.getAddMovieLength());
        values.put(DBHelper.COT_RELEASED_DATE, addMovies.getAddMovieReleasedDate());
        values.put(DBHelper.COT_DIRECTOR, addMovies.getAddMovieDirector());
        values.put(DBHelper.COT_ACTORS, addMovies.getAddMoiveActors());
        values.put(DBHelper.COT_DESCRIPTION, addMovies.getAddMovieDesc());
        values.put(DBHelper.COT_LANGUAGE, addMovies.getAddMovieLanguage());


        return database.update(DBHelper.TEN_BANG_MOVIES, values,DBHelper.COT_ID + " = "
                + addMovies.getAddMovieID(),null);
    }

    public long delPhim(AddMovies addMovies) {
        return database.delete(DBHelper.TEN_BANG_MOVIES,
                DBHelper.COT_ID + " = " + addMovies.getAddMovieID(),
                null);
    }
}
