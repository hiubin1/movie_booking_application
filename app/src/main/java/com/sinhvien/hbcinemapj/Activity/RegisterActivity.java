package com.sinhvien.hbcinemapj.Activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.sinhvien.hbcinemapj.Class.User;
import com.sinhvien.hbcinemapj.Class.UserSession;
import com.sinhvien.hbcinemapj.Database.DBHelper;
import com.sinhvien.hbcinemapj.Database.UserDatabse;
import com.sinhvien.hbcinemapj.FragmentCus.ProfileFragment;
import com.sinhvien.hbcinemapj.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class RegisterActivity extends AppCompatActivity {

    EditText edtDate, edtFName,edtEmail,edtPhoneNum,edtPass,edtRPPW;
    RadioGroup rgGender;




    private String Name, Pass, Gender, Email, PhoneNum, Birth, RPPass;

    private int Pic;


    long id = -1;

    long idUser;

    int PicUser;

    public static UserDatabse databse;


    String email,phone;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Đăng ký");

        edtDate = (EditText) findViewById(R.id.edtDate);
        edtFName = (EditText) findViewById(R.id.edtFName);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtPhoneNum = (EditText) findViewById(R.id.edtPhone);
        edtPass = (EditText) findViewById(R.id.edtPW);
        edtRPPW = (EditText) findViewById(R.id.edtRPPW);
        rgGender = (RadioGroup) findViewById(R.id.rgGender);
        databse = new UserDatabse(this);
        edtFName.requestFocus();


    }


    public void btnPageLogin_onclick(View view) {
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
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

    public User layDuLieuNguoiDung(){
        int id = rgGender.getCheckedRadioButtonId();
        RadioButton radGD = (RadioButton) findViewById(id);
        String gender_choose = radGD.getText().toString();
        if(gender_choose.equals("Nam")){
            Gender = "Nam";
            Pic = R.drawable.boruto256;
        } else if (gender_choose.equals("Nữ")){
            Gender = "Nữ";
            Pic = R.drawable.sadara256;
        }

        Name = edtFName.getText().toString();
        Email = edtEmail.getText().toString();
        PhoneNum = edtPhoneNum.getText().toString();
        Birth = edtDate.getText().toString();
        Pass = edtPass.getText().toString();
        RPPass = edtRPPW.getText().toString();


        User user = new User();


        idUser = user.getId();
        UserSession.id = idUser;
        UserSession.Pic = PicUser;
        user.setId(id);
        user.set_Name(Name);
        user.set_Email(Email);
        user.set_PhoneNum(PhoneNum);
        user.set_Birth(Birth);
        user.set_Pass(Pass);
        user.set_Gender(Gender);
        user.setPic(Pic);

        return user;
    }

    public boolean KiemTraDK(){
        boolean isValid = true;

        if(Name.length() < 6){
            Toast.makeText(RegisterActivity.this,"Họ tên không đươc dưới 6 ký tự",Toast.LENGTH_SHORT).show();
            isValid = false;
        }

        if(!Email.contains("@gmail")) {
            Toast.makeText(RegisterActivity.this,"Không đúng định dạng email",Toast.LENGTH_SHORT).show();
            isValid = false;
        }

        if(PhoneNum.length() < 10){
            Toast.makeText(RegisterActivity.this,"Số điện thoại không đúng",Toast.LENGTH_SHORT).show();
            isValid = false;
        }


        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date birthDate = null;
        try {
            birthDate = sdf.parse(Birth);
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
            Toast.makeText(RegisterActivity.this,"Bạn không đủ tuổi để đăng ký",Toast.LENGTH_SHORT).show();
            isValid = false;
        }

        if(Pass.length() <= 9){
            Toast.makeText(RegisterActivity.this,"Mật khẩu không an toàn",Toast.LENGTH_SHORT).show();
            isValid = false;
        }

        if (!Pass.equals(RPPass)){
            Toast.makeText(RegisterActivity.this,"Mật khẩu không trùng nhau",Toast.LENGTH_SHORT).show();
            isValid = false;
        }

        return isValid;
    }

    public void btnComplete_onclick(View view) {
        User user = layDuLieuNguoiDung();
        email = edtEmail.getText().toString();
        phone = edtPhoneNum.getText().toString();
        boolean KiemTraDKEmail = databse.KiemTraDKEmail(email);
        boolean KiemTraDKPhone = databse.KiemTraDKPhone(phone);
        if (KiemTraDKEmail){
            Toast.makeText(RegisterActivity.this,"Email đã được đăng ký",Toast.LENGTH_SHORT).show();
        } else if (KiemTraDKPhone){
            Toast.makeText(RegisterActivity.this,"Số điện thoại đã được đăng ký",Toast.LENGTH_SHORT).show();

        } else {
            if(user != null){
            if (KiemTraDK()){
                if(databse.them(user) != -1){
                    Toast.makeText(RegisterActivity.this,"Đăng ký thành công",Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(i);
                    edtFName.setText(null);
                    edtEmail.setText(null);
                    edtPhoneNum.setText(null);
                    edtDate.setText(null);
                    edtPass.setText(null);
                    edtRPPW.setText(null);
                    id = -1;
                }
            } else {
                Toast.makeText(RegisterActivity.this,"Đăng ký không thành công",Toast.LENGTH_SHORT).show();
            }
            }
        }
    }
}
