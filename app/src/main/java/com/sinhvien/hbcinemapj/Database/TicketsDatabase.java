package com.sinhvien.hbcinemapj.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sinhvien.hbcinemapj.Class.Tickets;

public class TicketsDatabase {

    SQLiteDatabase database;

    DBHelper helper;

    public TicketsDatabase(Context context) {
        helper = new DBHelper(context);
        database = helper.getWritableDatabase();
    }

    public boolean KiemTraGhe(long idTKmovie, String rapPhim, String phong, String time, String date, StringBuilder seat) {
        boolean KiemTraGhe = false;
        String[] cot = {String.valueOf(idTKmovie), rapPhim, phong, time, date,"%" + seat.toString() +"%"};
        String query = "select * from Tickets where _IDMovie = ? and _Location = ? and _PhongChieu = ? and _Time = ? and _BookingDate = ? and _Seat like ?";
        Cursor cursor = database.rawQuery(query, cot);
        if (cursor != null && cursor.moveToFirst() && cursor.getCount() > 0) {
            KiemTraGhe = true;
        } else {
            KiemTraGhe = false;
        }
        return KiemTraGhe;
    }

    public Cursor layTatCaDuLieu(){
        String[] cot = { DBHelper.COT_ID_TICKETS,
                        DBHelper.COT_ID_TICKETS_MOVIE,
                        DBHelper.COT_IDCUS,
                        DBHelper.COT_PHONE_CUS,
                        DBHelper.COT_NAME_CUS_TICKETS,
                        DBHelper.COT_EMAIL_CUS_TICKETS,
                        DBHelper.COT_NAME_MOVIES_TICKETS,
                        DBHelper.COT_FORMAT_MOVIES_TICKETS,
                        DBHelper.COT_SEATS,
                        DBHelper.COT_TIME,
                        DBHelper.COT_QUANTITY,
                        DBHelper.COT_LOCATION,
                        DBHelper.COT_PHONGCHIEU,
                        DBHelper.COT_BOOKING_DATE,
                        DBHelper.COT_TOTAL};

        Cursor cursor = null;

        cursor = database.query(DBHelper.TEN_BANG_TICKETS,cot, null,null,null,null,
                DBHelper.COT_ID_TICKETS + " DESC");

        return  cursor;
    }

    public Cursor SumAllTotal(){
        String query = "select sum(cast(replace(_Total,',000đ','.000') as double)) from Tickets ";
        Cursor cursor = database.rawQuery(query,null);
        return cursor;
    }

    public Cursor SumCusTotal(long id){
        String[] cot = {String.valueOf(id)};
        String query = "select sum(cast(replace(_Total,',000đ','.000') as double)) from Tickets where _CusID = ? ";
        Cursor cursor = database.rawQuery(query,cot);
        return cursor;
    }

    public Cursor GetTicketsFromId(long id){
        String[] cot = {String.valueOf(id)};
        String query = "select * from Tickets where _CusID = ? order by _IDTicket DESC ";
        Cursor cursor = database.rawQuery(query,cot);
        return cursor;
    }


    public long them(Tickets tickets){
        ContentValues values = new ContentValues();
        values.put(DBHelper.COT_ID_TICKETS_MOVIE,tickets.getIdTicketsMovie());
        values.put(DBHelper.COT_IDCUS,tickets.getIdCus());
        values.put(DBHelper.COT_PHONE_CUS,tickets.getPhoneCus());
        values.put(DBHelper.COT_NAME_CUS_TICKETS, tickets.getNameCus());
        values.put(DBHelper.COT_EMAIL_CUS_TICKETS,tickets.getEmailCus());
        values.put(DBHelper.COT_NAME_MOVIES_TICKETS,tickets.getNameMovies());
        values.put(DBHelper.COT_FORMAT_MOVIES_TICKETS,tickets.getFormatMovies());
        values.put(DBHelper.COT_SEATS,tickets.getSeats());
        values.put(DBHelper.COT_TIME,tickets.getTime());
        values.put(DBHelper.COT_QUANTITY,tickets.getQuantity());
        values.put(DBHelper.COT_LOCATION,tickets.getLocation());
        values.put(DBHelper.COT_PHONGCHIEU,tickets.getPhongChieu());
        values.put(DBHelper.COT_BOOKING_DATE,tickets.getBookingDate());
        values.put(DBHelper.COT_TOTAL,tickets.getTotal());

        return database.insert(DBHelper.TEN_BANG_TICKETS,null,values);
    }
}


