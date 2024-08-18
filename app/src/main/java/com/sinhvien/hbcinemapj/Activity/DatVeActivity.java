package com.sinhvien.hbcinemapj.Activity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.sinhvien.hbcinemapj.Class.AddMovies;
import com.sinhvien.hbcinemapj.Class.BankSession;
import com.sinhvien.hbcinemapj.Class.User;
import com.sinhvien.hbcinemapj.Class.UserSession;
import com.sinhvien.hbcinemapj.Database.DBHelper;
import com.sinhvien.hbcinemapj.Database.UserDatabse;
import com.sinhvien.hbcinemapj.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class DatVeActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    Spinner spinnerRap, spinnerPhong, spinnerDinhDang;

    EditText edtNameTickets, edtPhoneTickets, edtEmailTickets;

    TextView tvDate, tvMovieNameTickets;
    Button btnTiepTuc;

    ImageButton imgbtnDate;

    RadioGroup mRadio;

    UserDatabse userDatabse;

    String tenRap, phong, dinhDang, gio, ngay;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dat_ve);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Đặt Vé");


        tvDate = (TextView) findViewById(R.id.tvDate);
        imgbtnDate = (ImageButton) findViewById(R.id.imgbtnDate);
        userDatabse = new UserDatabse(this);



        //Họ và tên
        edtNameTickets = (EditText) findViewById(R.id.edtNameTickets);

        //SDT
        edtPhoneTickets = (EditText) findViewById(R.id.edtPhoneTickets);

        //Email
        edtEmailTickets = (EditText) findViewById(R.id.edtEmailTickets);

        // Tên Phim
        tvMovieNameTickets = (TextView) findViewById(R.id.tvMovieNameTickets);

        // RẠP
        spinnerRap = (Spinner) findViewById(R.id.SpinnerRap);
        ArrayList<String> dsRap = new ArrayList<String>();
        dsRap.add("Vui lòng chọn rạp phim");
        dsRap.add("HB cinema quận 10");
        dsRap.add("HB cinema quận 5");
        dsRap.add("HB cinema quận Bình Thạnh");
        ArrayAdapter adapRap = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,dsRap);
        spinnerRap.setAdapter(adapRap);

        Bundle b = getIntent().getExtras();
        tvMovieNameTickets.setText(b.getString("MovieName"));


        SetUserInfo();


        spinnerRap.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                switch (position){
                    case 0:
                        tenRap = "Vui lòng chọn rạp phim";
                        break;
                    case 1:
                        tenRap = "HB cinema quận 10";
                        break;
                    case 2:
                        tenRap = "HB cinema quận 5";
                        break;
                    case 3:
                        tenRap = "HB cinema quận Bình Thạnh";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        // PHÒNG
        spinnerPhong = (Spinner) findViewById(R.id.SpinnerPhong);
        ArrayList<String> dsPhong = new ArrayList<String>();
        dsPhong.add("Vui lòng chọn phòng");
        dsPhong.add("  P1  ");
        dsPhong.add("  P2  ");
        dsPhong.add("  P3  ");
        dsPhong.add("  P4  ");
        ArrayAdapter adapPhong = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,dsPhong);
        spinnerPhong.setAdapter(adapPhong);


        spinnerPhong.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                switch (position){
                    case 0:
                        phong = "Vui lòng chọn phòng";
                        break;
                    case 1:
                        phong = "P1";
                        break;
                    case 2:
                        phong = "P2";
                        break;
                    case 3:
                        phong = "P3";
                        break;
                    case 4:
                        phong = "P4";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        // ĐỊNH DẠNG
        spinnerDinhDang = (Spinner) findViewById(R.id.SpinnerDinhDang);
        ArrayList<String> dsDinhDang = new ArrayList<String>();
        dsDinhDang.add("Vui lòng chọn định dạng phim");
        dsDinhDang.add("Thường");
        dsDinhDang.add("2D");
        ArrayAdapter adapDinhDang = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,dsDinhDang);
        spinnerDinhDang.setAdapter(adapDinhDang);


        spinnerDinhDang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                switch(position){
                    case 0:
                        dinhDang = "Vui lòng chọn định dạng phim";
                        break;
                    case 1:
                        dinhDang = "Thường";
                        break;
                    case 2:
                        dinhDang = "2D";
                        break;
                }
            }


            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }

        });


        // GIỜ
        mRadio = (RadioGroup) findViewById(R.id.rgSetTime);
        mRadio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId){
                    case R.id.rb830:
                        gio = "8:30";
                        break;
                    case R.id.rb1010:
                        gio = "10:10";
                        break;
                    case R.id.rb1050:
                        gio = "10:50";
                        break;
                    case R.id.rb1230:
                        gio = "12:30";
                        break;
                    case R.id.rb1310:
                        gio = "13:10";
                        break;
                    case R.id.rb1530:
                        gio = "15:30";
                        break;
                }
            }
        });



        // tiếp tục
        btnTiepTuc = (Button) findViewById(R.id.btnTiepTuc);
        btnTiepTuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(tenRap.equals("Vui lòng chọn rạp phim") || phong.equals("Vui lòng chọn phòng") || dinhDang.equals("Vui lòng chọn định dạng phim")|| ngay.equals("") || gio.equals("")){
                    Toast.makeText(DatVeActivity.this,"Vui lòng kiểm tra lại thông tin đặt vé",Toast.LENGTH_SHORT).show();
                } else {
                    Intent i = new Intent(getApplicationContext(),ChonGheActivity.class);
                    Bundle b = new Bundle();
                    b.putString("hovaten", edtNameTickets.getText().toString());
                    b.putString("sdt",edtPhoneTickets.getText().toString());
                    b.putString("email",edtEmailTickets.getText().toString());
                    b.putString("tenMovie",tvMovieNameTickets.getText().toString());
                    b.putString("tenRap",tenRap);
                    b.putString("phong", phong);
                    b.putString("dinhDang", dinhDang);
                    b.putString("ngayChieu", ngay);
                    b.putString("gioChieu", gio);
                    i.putExtras(b);
                    startActivity(i);
                }
            }
        });
    }

    @SuppressLint("Range")
    public void SetUserInfo(){
        Cursor cursor = userDatabse.GetUserFromEmail(UserSession.userEmail);
        if(cursor != null){
            while (cursor.moveToNext()){
                User user = new User();
                user.setId(cursor.getInt(cursor.getColumnIndex(DBHelper.COT_ID_CUS)));
                user.set_Name(cursor.getString(cursor.getColumnIndex(DBHelper.COT_NAME)));
                user.set_Email(cursor.getString(cursor.getColumnIndex(DBHelper.COT_EMAIL)));
                user.set_PhoneNum(cursor.getString(cursor.getColumnIndex(DBHelper.COT_PHONE_NUM)));

                UserSession.id = user.getId();
                edtNameTickets.setText(user.get_Name());
                edtNameTickets.setEnabled(false);
                edtEmailTickets.setText(user.get_Email());
                edtEmailTickets.setEnabled(false);
                edtPhoneTickets.setText(user.get_PhoneNum());

            }
        }
    }



    // Trở về trang trước đó
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        Calendar mCalendar = Calendar.getInstance();
        datePicker.setMaxDate(System.currentTimeMillis());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        mCalendar.set(Calendar.YEAR, year);
        mCalendar.set(Calendar.MONTH, month);
        mCalendar.set(Calendar.DAY_OF_MONTH, day);
        String dateTime = simpleDateFormat.format(mCalendar.getTime());
        tvDate.setText(dateTime);
        ngay = tvDate.getText().toString();
    }

    public void openDate_onclick(View view) {
        com.sinhvien.hbcinemapj.Fragment.DatePicker mDatePickerDialogFragment;
        mDatePickerDialogFragment = new com.sinhvien.hbcinemapj.Fragment.DatePicker();
        mDatePickerDialogFragment.show(getSupportFragmentManager(), "DATE PICK");
    }
}