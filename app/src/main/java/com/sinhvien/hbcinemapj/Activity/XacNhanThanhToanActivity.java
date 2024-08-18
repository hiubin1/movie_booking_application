package com.sinhvien.hbcinemapj.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.sinhvien.hbcinemapj.R;

public class XacNhanThanhToanActivity extends AppCompatActivity {

    TextView mHoTen,mSDT,mEmail,mTenPhim,mRapPhim,mPhong,mDingDang,mGio,mGhe,mSoLuongGhe,mTongTien,mNgayChieu;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xac_nhan_thanh_toan);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Hoá Đơn");

        mHoTen = (TextView) findViewById(R.id.tvNameTickets);
        mSDT = (TextView) findViewById(R.id.tvPhoneTickets);
        mEmail = (TextView) findViewById(R.id.tvEmailTickets);
        mTenPhim = (TextView) findViewById(R.id.tvNameMovie);
        mRapPhim = (TextView) findViewById(R.id.tvRap);
        mPhong = (TextView) findViewById(R.id.tvPhong);
        mDingDang = (TextView) findViewById(R.id.tvDinhDang);
        mGio = (TextView) findViewById(R.id.tvTime);
        mNgayChieu = (TextView) findViewById(R.id.tvDateBooking);
        mGhe = (TextView) findViewById(R.id.tvSeatNum);
        mSoLuongGhe = (TextView) findViewById(R.id.quantity);
        mTongTien = (TextView) findViewById(R.id.tvTongTien);

        Bundle bundle = getIntent().getExtras();
        mHoTen.setText(bundle.getString("hovaten"));
        mSDT.setText(bundle.getString("sdt"));
        mEmail.setText(bundle.getString("email"));
        mTenPhim.setText(bundle.getString("MovieName"));
        mRapPhim.setText(bundle.getString("tenRap"));
        mPhong.setText(bundle.getString("phong"));
        mDingDang.setText(bundle.getString("dinhDang"));
        mGio.setText(bundle.getString("gioChieu"));
        mNgayChieu.setText(bundle.getString("ngayChieu"));
        mGhe.setText(bundle.getString("ghe"));
        mSoLuongGhe.setText("" + bundle.getInt("soLuongGhe"));
        mTongTien.setText(bundle.getString("tongTien"));



    }

    public void btnHuy_onclick(View view) {
        finish();
    }

    public void btnXacNhan_onclick(View view) {
        Intent i = new Intent(XacNhanThanhToanActivity.this, ChonNganHangActivity.class);
        Bundle b = new Bundle();
        b.putString("NameCus", mHoTen.getText().toString());
        b.putString("EmailCus",mEmail.getText().toString());
        b.putString("sdt",mSDT.getText().toString());
        b.putString("NameMovie", mTenPhim.getText().toString());
        b.putString("NameLocation",mRapPhim.getText().toString());
        b.putString("RoomName",mPhong.getText().toString());
        b.putString("DinhDang",mDingDang.getText().toString());
        b.putString("GioChieu",mGio.getText().toString());
        b.putString("NgayChieu",mNgayChieu.getText().toString());
        b.putString("GheNgoi",mGhe.getText().toString());
        b.putString("SoLuongGhe",mSoLuongGhe.getText().toString());
        b.putString("TongTien",mTongTien.getText().toString());
        i.putExtras(b);
        startActivity(i);
    }
}