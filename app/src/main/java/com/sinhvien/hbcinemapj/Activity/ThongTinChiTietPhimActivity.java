package com.sinhvien.hbcinemapj.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.widget.AppCompatButton;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.sinhvien.hbcinemapj.Class.Movies;
import com.sinhvien.hbcinemapj.Class.MoviesSession;
import com.sinhvien.hbcinemapj.Class.TicketsSession;
import com.sinhvien.hbcinemapj.Database.AddPhimDatabase;
import com.sinhvien.hbcinemapj.Database.DBHelper;
import com.sinhvien.hbcinemapj.R;

import java.io.Console;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ThongTinChiTietPhimActivity extends AppCompatActivity {

    String SaveNameMovie;

    ImageView mImgPosterDetail, mBannerMoviesDetails;

    long idTickets;

    Button mbtnDatVe;

    AddPhimDatabase addPhimDatabase;

    TextView mtvNameMovieDetails,mtvDateMovieDetails,mtvTimeMovieDetails,mtvDescMovieDetails,tvAgeMovieDetails,
    mtvTypeMovieDetails,mtvDirectorMovieDetails,mtvActorsMovieDetails,mtvLangugeMovieDetails;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thongtinchitietphim);
        mImgPosterDetail = (ImageView) findViewById(R.id.tvPosterMovieDetails);
        mBannerMoviesDetails = (ImageView) findViewById(R.id.bannerMoviesDetails);
        mtvNameMovieDetails = (TextView) findViewById(R.id.tvNameMovieDetails);
        mtvDateMovieDetails = (TextView) findViewById(R.id.tvDateMovieDetails);
        mtvTimeMovieDetails = (TextView) findViewById(R.id.tvTimeMovieDetails);
        mtvDescMovieDetails = (TextView) findViewById(R.id.tvDescMovieDetails);
        tvAgeMovieDetails = (TextView) findViewById(R.id.tvAgeMovieDetails);
        mtvTypeMovieDetails = (TextView) findViewById(R.id.tvTypeMovieDetails);
        mtvDirectorMovieDetails = (TextView) findViewById(R.id.tvDirectorMovieDetails);
        mtvActorsMovieDetails = (TextView) findViewById(R.id.tvActorsMovieDetails);
        mtvLangugeMovieDetails = (TextView) findViewById(R.id.tvLangugeMovieDetails);
        mbtnDatVe = (Button) findViewById(R.id.btnDatVe);
        addPhimDatabase = new AddPhimDatabase(this);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Phim");


        Bundle b = getIntent().getExtras();
        if(b == null) {
            return;
        }

        Movies movies = (Movies) b.get("object_movies");
        SaveNameMovie = movies.getDate();
        idTickets = movies.getId();

        TicketsSession.TicketsId = idTickets;
        SaveNameMovie = movies.getMovieName();
        mImgPosterDetail.setImageResource(movies.getPic());
        mBannerMoviesDetails.setImageResource(movies.getBanner());
        mtvNameMovieDetails.setText(movies.getMovieName());
        mtvDateMovieDetails.setText(movies.getDate());
        mtvTimeMovieDetails.setText(movies.getTime());
        mtvDescMovieDetails.setText(movies.getDesc());
        tvAgeMovieDetails.setText(movies.getAge());
        mtvTypeMovieDetails.setText(movies.getType());
        mtvDirectorMovieDetails.setText(movies.getDirector());
        mtvActorsMovieDetails.setText(movies.getActors());
        mtvLangugeMovieDetails.setText(movies.getLanguge());

        CheckMovieDate();

    }

    public  void CheckMovieDate() {

        String ngayKhoiChieu = mtvDateMovieDetails.getText().toString();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String DateNow = sdf.format(new Date());

        try {
            Date dateKhoiChieu = sdf.parse(ngayKhoiChieu);
            Date dateNow = sdf.parse(DateNow);

            if (dateNow.compareTo(dateKhoiChieu) >= 0){
                mbtnDatVe.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Bundle b = new Bundle();
                        Intent i = new Intent(ThongTinChiTietPhimActivity.this,DatVeActivity.class);
                        b.putString("MovieName", SaveNameMovie);
                        i.putExtras(b);
                        startActivity(i);
                    }
                });
            } else {
                mbtnDatVe.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(ThongTinChiTietPhimActivity.this,"Chưa có xuất chiếu của bộ phim này",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        } catch (ParseException e){
            e.printStackTrace();
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }



}

