package com.sinhvien.hbcinemapj.Activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.sinhvien.hbcinemapj.Class.User;
import com.sinhvien.hbcinemapj.Class.UserSession;
import com.sinhvien.hbcinemapj.Database.DBHelper;
import com.sinhvien.hbcinemapj.Database.UserDatabse;
import com.sinhvien.hbcinemapj.R;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ProfileDetails extends AppCompatActivity {

    DatePickerDialog datePickerDialog;
    TextView mtvDate,mtvGender;
    Spinner mGender;
    EditText mHoTenEdit, mSodtEdit, mEmailEdit;
    Button mEditSubmit, mDeleteAcc;
    ActionBar actionBar;
    String  Name, Pass, Gender, Email, PhoneNum, birthdate,gioiTinh;
    String bankId;


    ImageButton mimgbtnDate;

    UserDatabse userDatabse;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_details);
        initDatePicker();

        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Thông tin tài khoản");
        mGender = (Spinner) findViewById(R.id.Gender);
        mtvGender = (TextView) findViewById(R.id.tvGender);
        mHoTenEdit = (EditText) findViewById(R.id.edtHoTenEdit);
        mSodtEdit = (EditText) findViewById(R.id.edtSdtEdit);
        mEmailEdit = (EditText) findViewById(R.id.edtEmailEdit);
        mEditSubmit = (Button) findViewById(R.id.EditProfileDetails);
        mDeleteAcc = (Button) findViewById(R.id.DeleteAccount);
        mimgbtnDate = (ImageButton) findViewById(R.id.imgbtnDate);
        mtvDate = (TextView) findViewById(R.id.tvDate);
        userDatabse = new UserDatabse(this);

        mDeleteAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ProfileDetails.this);
                builder.setTitle("Thông báo");
                builder.setMessage("Bạn chắc chắn muốn xóa tài khoản ?");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        User user = layDuLieuNguoiDung();
                        userDatabse.delAcc(user);
                        Toast.makeText(ProfileDetails.this,"Bạn đã xóa tài khoản thành công", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ProfileDetails.this,MainActivity.class);
                        startActivity(intent);
                    }
                });

                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                builder.create().show();
            }
        });
        setUserInfo();


        //Giới tính
        ArrayList<String> gioitinh = new ArrayList<String>();
        gioitinh.add("Nam");
        gioitinh.add("Nữ");
        ArrayAdapter adapterGender = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, gioitinh);
        mGender.setAdapter(adapterGender);
        int position = adapterGender.getPosition(UserSession.Gender);
        mGender.setSelection(position);
        mGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                switch (position){
                    case 0:
                        Gender = "Nam";
                        break;
                    case 1:
                        Gender = "Nữ";
                        break;
                }
                mtvGender.setText(Gender);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        //Ngày Sinh


    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month =  month+1;
                String date = makeDateString(day, month, year);
                mtvDate.setText(date);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
    }

    private String makeDateString(int day, int month, int year) {
        return day + "/" + getMonthFormat(month) + "/" +year;
    }


    private String getMonthFormat(int month) {
        if(month == 1){
            return "1";
        }
        if(month == 2){
            return "2";
        }
        if(month == 3){
            return "3";
        }
        if(month == 3){
            return "4";
        }
        if(month == 5){
            return "5";
        }
        if(month == 6){
            return "6";
        }
        if(month == 7){
            return "7";
        }
        if(month == 8){
            return "8";
        }
        if(month == 9){
            return "9";
        }
        if(month == 10){
            return "10";
        }
        if(month == 11){
            return "11";
        }
        if(month == 12){
            return "12";
        }

        return "01";
    }

    @SuppressLint("Range")
    public void setUserInfo(){
        Cursor cursor = userDatabse.GetUserFromEmail(UserSession.userEmail);
        if(cursor != null){
            while (cursor.moveToNext()){
                User user = new User();
                user.setId(cursor.getInt(cursor.getColumnIndex(DBHelper.COT_ID_CUS)));
                user.set_Name(cursor.getString(cursor.getColumnIndex(DBHelper.COT_NAME)));
                user.set_PhoneNum(cursor.getString(cursor.getColumnIndex(DBHelper.COT_PHONE_NUM)));
                user.set_Email(cursor.getString(cursor.getColumnIndex(DBHelper.COT_EMAIL)));
                user.set_Birth(cursor.getString(cursor.getColumnIndex(DBHelper.COT_BIRTH)));
                user.set_Gender(cursor.getString(cursor.getColumnIndex(DBHelper.COT_GENDER)));

                UserSession.id = user.getId();
                UserSession.userName = user.get_Name();
                UserSession.Gender = user.get_Gender();
                mHoTenEdit.setText(user.get_Name());
                mSodtEdit.setText(user.get_PhoneNum());
                mEmailEdit.setText(user.get_Email());
                mEmailEdit.setEnabled(false);
                mtvDate.setText(user.get_Birth());
                mtvGender.setText(user.get_Gender());
            }
        }
    }

    public User layDuLieuNguoiDung(){
        Name = mHoTenEdit.getText().toString();
        PhoneNum = mSodtEdit.getText().toString();
        Email = mEmailEdit.getText().toString();
        birthdate = mtvDate.getText().toString();
        gioiTinh = mtvGender.getText().toString();
        Pass = UserSession.userPass;
        bankId = null;


        User user = new User();
        user.setId(UserSession.id);
        user.set_Name(Name);
        user.set_PhoneNum(PhoneNum);
        user.set_Pass(Pass);
        user.set_Email(Email);
        user.set_Birth(birthdate);
        user.set_Gender(gioiTinh);
        return user;
    }


    public void openDatePicker(View view) {
        datePickerDialog.show();
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

    public boolean KiemTraThayDoi(){
        boolean isValid = true;

        if(Name.length() < 6){
            Toast.makeText(ProfileDetails.this,"Họ tên không đươc dưới 6 ký tự",Toast.LENGTH_SHORT).show();
            isValid = false;
        }

        if(!Email.contains("@gmail")) {
            Toast.makeText(ProfileDetails.this,"Không đúng định dạng email",Toast.LENGTH_SHORT).show();
            isValid = false;
        }

        if(PhoneNum.length() < 10){
            Toast.makeText(ProfileDetails.this,"Số điện thoại không đúng",Toast.LENGTH_SHORT).show();
            isValid = false;
        }


        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date birthDate = null;
        try {
            birthDate = sdf.parse(birthdate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar dob = Calendar.getInstance();
        dob.setTime(birthDate);
        Calendar today = Calendar.getInstance();
        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
        if (dob.get(Calendar.MONTH) > today.get(Calendar.MONTH) ||
                (dob.get(Calendar.MONTH) == today.get(Calendar.MONTH) && dob.get(Calendar.DAY_OF_MONTH) > today.get(Calendar.DAY_OF_MONTH))) {
            age--;
        }

        if (age < 10) {
            Toast.makeText(ProfileDetails.this,"Số tuổi thay đổi không được nhỏ hơn 10",Toast.LENGTH_SHORT).show();
            isValid = false;
        }

        return isValid;
    }

    public void btnEditPf_onclick(View view) {
        User user = layDuLieuNguoiDung();
        String phone = mSodtEdit.getText().toString();
        boolean KiemTraSDT = userDatabse.KiemTraDKPhone(phone);
        if (KiemTraSDT){
            Toast.makeText(ProfileDetails.this,"Số điện thoại đã được đăng ký", Toast.LENGTH_SHORT).show();
        } else {
            if (user != null){
//            if (KiemTraThayDoi()){
                if(userDatabse.sua(user) != -1){
                    Toast.makeText(ProfileDetails.this,"Thông tin của bạn đã được thay đổi thành công",Toast.LENGTH_SHORT).show();
                    finish();
                }
//            }
            }
        }
    }
}