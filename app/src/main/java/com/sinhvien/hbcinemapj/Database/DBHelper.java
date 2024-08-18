package com.sinhvien.hbcinemapj.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHelper extends SQLiteOpenHelper {

    //Tên csdl
    private static final String TEN_DATABASE = "QuanLyRapPhimHBcinema";



    //Tên bảng movies
    public static final  String TEN_BANG_MOVIES = "Movies";

    public static final String COT_ID = "_IDPhim";
    public static final String COT_TEN_PHIM = "_MovieName";
    public static final String COT_MOVIE_IMAGE = "_MovieImage";
    public static final String COT_TYPE_MOVIE = "_TypeID";
    public static final String COT_AGE = "_Age";
    public static final String COT_MOVIELENGTH = "_MovieLength";
    public static final String COT_RELEASED_DATE = "_ReleasedDate";
    public static final String COT_DIRECTOR = "_Director";
    public static final String COT_ACTORS = "_Actors";
    public static final String COT_DESCRIPTION = "_Desription";
    public static final String COT_LANGUAGE = "_Language";
    public static final String COT_BANNER = "_Banner";

    private static final String TAO_BANG_MOVIES = ""
            + "create table " + TEN_BANG_MOVIES + " ( "
            + COT_ID + " integer primary key autoincrement ,"
            + COT_TEN_PHIM + " text not null, "
            + COT_MOVIE_IMAGE + " integer not null, "
            + COT_TYPE_MOVIE + " text not null, "
            + COT_AGE + " text not null ,"
            + COT_MOVIELENGTH + " text not null, "
            + COT_RELEASED_DATE + " text not null, "
            + COT_DIRECTOR + " text not null, "
            + COT_ACTORS + " text not null, "
            + COT_DESCRIPTION + " text not null, "
            + COT_LANGUAGE + " tetx not null, "
            + COT_BANNER + " integer not null);";


    //Bảng User
    public static final String TEN_BANG_USER = "User";

    public static final String COT_ID_CUS = "_IDCUs";

    public static String COT_IMAGE_USER = "_ImageUser";
    public static final String COT_NAME = "_Name";
    public static final String COT_PASS = "_Pass";
    public static final String COT_GENDER = "_Gender";
    public static final String COT_EMAIL = "_Email";
    public static final String COT_PHONE_NUM = "_PhoneNum";
    public static final String COT_BIRTH = "_Birth";



    private static final String TAO_BANG_USER = ""
            + "create table " + TEN_BANG_USER + " ( "
            + COT_ID_CUS + " integer primary key autoincrement ,"
            + COT_IMAGE_USER + " integer not null, "
            + COT_NAME + " text not null, "
            + COT_PASS + " text not null, "
            + COT_GENDER + " text not null, "
            + COT_EMAIL + " text not null, "
            + COT_PHONE_NUM + " text not null, "
            + COT_BIRTH + " text not null); ";

    //Tạo bảng Bank Account
    public static final String TEN_BANG_BANKACCOUNT = "BankAccount";

    public static final String COT_IDBANK = "_IDBank";

    public static final String COT_IDCUSBANK = "_IDCusBank";
    public static final String COT_BANKNAME = "_BankName";
    public static final String COT_BANKNUM = "_BankNum";

    public static final String COT_DATEBANK = "_BankDate";

    public static final String COT_NAMEUSERBANK = "_BankAccName";



    private static final String TAO_BANG_BANKACCOUNT = ""
            + "create table " + TEN_BANG_BANKACCOUNT + " ( "
            + COT_IDBANK + " integer primary key autoincrement ,"
            + COT_IDCUSBANK + " integer not null, "
            + COT_BANKNAME + " text not null, "
            + COT_BANKNUM + " text not null, "
            + COT_DATEBANK + " text not null, "
            + COT_NAMEUSERBANK + " text not null, "
            + "FOREIGN KEY " + "(" + COT_IDCUSBANK + ") REFERENCES "
            + TEN_BANG_USER + "(" + COT_ID_CUS + "));";




    //Tạo bảng Tickets
    public static final String TEN_BANG_TICKETS = "Tickets";

    public static final String COT_ID_TICKETS = "_IDTicket";

    public static final String COT_PHONE_CUS = "_PhoneCus";

    public static final String COT_NAME_CUS_TICKETS = "_NameCusTickets";

    public static final String COT_EMAIL_CUS_TICKETS = "_EmailCusTickets";

    public static final String COT_NAME_MOVIES_TICKETS = "_NameMoviesTickets";

    public static final String COT_FORMAT_MOVIES_TICKETS = "_FormatMoviesTickets";
    public static final String COT_ID_TICKETS_MOVIE = "_IDMovie";
    public static final String COT_IDCUS = "_CusID";
    public static final String COT_SEATS = "_Seat";
    public static final String COT_QUANTITY = "_Quantity";

    public static final String COT_TIME = "_Time";

    public static final String COT_LOCATION = "_Location";
    public static final String COT_PHONGCHIEU = "_PhongChieu";
    public static final String COT_BOOKING_DATE = "_BookingDate";
    public static final String COT_TOTAL = "_Total";

    private static final String TAO_BANG_TICKETS = ""
            + "create table " + TEN_BANG_TICKETS + " ( "
            + COT_ID_TICKETS + " integer primary key autoincrement ,"
            + COT_ID_TICKETS_MOVIE + " integer not null, "
            + COT_NAME_CUS_TICKETS + " text not null, "
            + COT_EMAIL_CUS_TICKETS + " text not null, "
            + COT_NAME_MOVIES_TICKETS + " text not null, "
            + COT_FORMAT_MOVIES_TICKETS + " text not null, "
            + COT_PHONE_CUS + " text not null, "
            + COT_IDCUS + " integer not null,  "
            + COT_SEATS + " text not null, "
            + COT_TIME + " text not null, "
            + COT_QUANTITY + " integer, "
            + COT_LOCATION + " text not null, "
            + COT_PHONGCHIEU + " integer, "
            + COT_BOOKING_DATE + " text not null, "
            + COT_TOTAL + " integer not null, "
            + "FOREIGN KEY " + "(" + COT_IDCUS + ") REFERENCES "
            + TEN_BANG_USER + "(" + COT_ID_CUS + "),"
            + "FOREIGN KEY " + "(" + COT_ID_TICKETS_MOVIE + ") REFERENCES "
            + TEN_BANG_MOVIES + "(" + COT_ID + "));";





    //Tạo bảng News
    public static final String TEN_BANG_NEWS = "News";

    public static final String COT_NEWS_ID = "_NewsID";
    public static final String COT_NEWS_NAME = "_NewsName";
    public static final String COT_NEWS_BANNER = "_NewsBanner";
    public static final String COT_NEWS_DATE = "_NewsUploadDate";
    public static final String COT_NEWS_DESCRIPTION = "_NewsDescription";
    public static final String COT_NEWS_RAP = "_NewsRapID";

    private static final String TAO_BANG_NEWS = ""
            + "create table " + TEN_BANG_NEWS + " ( "
            + COT_NEWS_ID + " integer primary key autoincrement ,"
            + COT_NEWS_NAME + " text not null, "
            + COT_NEWS_BANNER + " text not null, "
            + COT_NEWS_DATE + " text not null, "
            + COT_NEWS_DESCRIPTION + " text not null, "
            + COT_NEWS_RAP + " text );";

    public DBHelper(Context context) {
        super(context, TEN_DATABASE, null, 3);
    }


    

    @Override
    public void onCreate(SQLiteDatabase dbPhim) {
        dbPhim.execSQL(TAO_BANG_MOVIES);
        dbPhim.execSQL(TAO_BANG_USER);
        dbPhim.execSQL(TAO_BANG_TICKETS);
        dbPhim.execSQL(TAO_BANG_BANKACCOUNT);
        dbPhim.execSQL(TAO_BANG_NEWS);

    }

    @Override
    public void onUpgrade(SQLiteDatabase dbPhim, int i, int i1) {

    }
}
