package com.sinhvien.hbcinemapj.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sinhvien.hbcinemapj.Class.Banks;

public class BankDatabase {

    SQLiteDatabase database;

    DBHelper helper;

    public BankDatabase (Context context){
        helper = new DBHelper(context);

        database = helper.getWritableDatabase();
    }

    public Cursor layTatCaDuLieu(){
        String[] cot = { DBHelper.COT_IDBANK,
                        DBHelper.COT_IDCUSBANK,
                        DBHelper.COT_BANKNAME,
                        DBHelper.COT_BANKNUM,
                        DBHelper.COT_DATEBANK,
                        DBHelper.COT_NAMEUSERBANK};

        Cursor cursor = null;

        cursor = database.query(DBHelper.TEN_BANG_BANKACCOUNT, cot,null,null,null,null,DBHelper.COT_IDBANK + " DESC");
        return cursor;
    }

    public Cursor GetAllFromIdCus(long id){
        String[] cot = {String.valueOf(id)};
        String query = "select * from BankAccount where _IDCusBank = ?";
        Cursor cursor = database.rawQuery(query,cot);
        return cursor;
    }

    public Cursor GetAllFromIdBank(long id){
        String[] cot = {String.valueOf(id)};
        String query = "select * from BankAccount where _IDBank = ?";
        Cursor cursor = database.rawQuery(query,cot);
        return cursor;
    }



    public long them(Banks banks){
        ContentValues values = new ContentValues();
        values.put(DBHelper.COT_BANKNAME,banks.getBankName());

        values.put(DBHelper.COT_IDCUSBANK,banks.getIdCusBank());

        values.put(DBHelper.COT_BANKNUM,banks.getBankNum());

        values.put(DBHelper.COT_DATEBANK,banks.getNgayPhatHanh());

        values.put(DBHelper.COT_NAMEUSERBANK,banks.getBankAccName());

        return database.insert(DBHelper.TEN_BANG_BANKACCOUNT,null,values);
    }

}
