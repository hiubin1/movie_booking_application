package com.sinhvien.hbcinemapj.Activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.sinhvien.hbcinemapj.Class.News;
import com.sinhvien.hbcinemapj.R;

public class ThongTinChiTietTinTucActivity extends AppCompatActivity {

    TextView mtvNewsNameDetails, mtvNewsDateDetails, mtvNewsContentDetail, mtvRapApDungDetails;
    ImageView mimgNewsDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thongtinchitiettintuc);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Tin Tức");

        mtvNewsNameDetails = (TextView) findViewById(R.id.tvNewsNameDetails);
        mtvNewsDateDetails = (TextView) findViewById(R.id.tvDateNewsDetails);
        mtvNewsContentDetail = (TextView) findViewById(R.id.tvContentNewsDetails);
        mtvRapApDungDetails = (TextView) findViewById(R.id.tvRapApDungDetails);
        mimgNewsDetails = (ImageView) findViewById(R.id.imgNewsDetails);

        Bundle bundle = getIntent().getExtras();
        if(bundle == null){
            return;
        }
        News news = (News) bundle.get("object_news");

        mimgNewsDetails.setImageResource(news.getNewsImage());
        mtvNewsNameDetails.setText(news.getNewsName());
        mtvNewsDateDetails.setText(news.getNewsDate());
        mtvNewsContentDetail.setText(news.getNewsContent());
        mtvRapApDungDetails.setText(news.getNewsRapApDung());
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