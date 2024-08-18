package com.sinhvien.hbcinemapj.Activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.sinhvien.hbcinemapj.Class.Tickets;
import com.sinhvien.hbcinemapj.Class.User;
import com.sinhvien.hbcinemapj.Class.UserSession;
import com.sinhvien.hbcinemapj.Database.DBHelper;
import com.sinhvien.hbcinemapj.Database.UserDatabse;
import com.sinhvien.hbcinemapj.FragmentCus.ProfileFragment;
import com.sinhvien.hbcinemapj.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText medtEmail, medtPass;

    Button mbtnPageRegister;

    RadioGroup mrgRole;

    UserDatabse databse;

    CheckBox mchkGhiNho;



    String fileName = "mySharedPreferences";




    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        medtEmail = (EditText) findViewById(R.id.edtEmailLogin);
        medtPass = (EditText) findViewById(R.id.edtPWLogin);
        mbtnPageRegister = (Button) findViewById(R.id.btnPageRegister);
        mrgRole = (RadioGroup) findViewById(R.id.rgRole);
        mchkGhiNho = (CheckBox) findViewById(R.id.chkGhiNho);
        databse = new UserDatabse(this);
        medtEmail.requestFocus();
    }




    public void btnPageRegister_onclick(View view) {

        int id = mrgRole.getCheckedRadioButtonId();
        if( id == -1){
            Toast.makeText(MainActivity.this, "Vui lòng chọn vai trò",Toast.LENGTH_SHORT).show();
            return;
        }
        RadioButton rad = (RadioButton) findViewById(id);
        String role_choose = rad.getText() + "";

        if (role_choose.equals("Quản Lý")){
            Toast.makeText(MainActivity.this, "Không thể đăng ký dưới quyền quản lý",Toast.LENGTH_SHORT).show();
        } else if (role_choose.equals("Thành Viên")){
            Intent i = new Intent(getApplicationContext(),RegisterActivity.class);
            startActivity(i);
        }
    }



    @SuppressLint("Range")
    public void btnLogin_onClick(View view) {

        int id = mrgRole.getCheckedRadioButtonId();
        if( id == -1){
            Toast.makeText(MainActivity.this, "Vui lòng chọn vai trò",Toast.LENGTH_SHORT).show();
            return;
        }
        RadioButton rad = (RadioButton) findViewById(id);
        String role_choose = rad.getText() + "";

        if (role_choose.equals("Quản Lý")){
            String TenDangNhapDefault = "admin@gmail.com";
            String PassDefault = "123456";

            String TenDNDefault = medtEmail.getText().toString();
            String PassDNDefault = medtPass.getText().toString();

            if(TenDangNhapDefault.equals(TenDNDefault) && PassDefault.equals(PassDNDefault)){
                Intent i = new Intent(MainActivity.this, AdminActivity.class);
                startActivity(i);
            } else {
                Toast.makeText(this,"Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
            }
        }


        if (role_choose.equals("Thành Viên")) {
            String TenDNDefault = medtEmail.getText().toString();
            String PassDNDefault = medtPass.getText().toString();
            boolean KiemTraDN = databse.KiemTraDN(TenDNDefault,PassDNDefault);
            if(KiemTraDN){
                UserSession.userPass = PassDNDefault;
                UserSession.userEmail = TenDNDefault;

                savingPreferences(); // lưu trạng thái
                    Intent i = new Intent(getApplicationContext(),CustomerActivity.class);
                    startActivity(i);
                    String email = UserSession.userEmail;
                    String Name = "";
                    Cursor cursor = databse.GetNameFromEmail(email);
                    if (cursor.moveToFirst()){
                        Name = cursor.getString(cursor.getColumnIndex("_Name"));
                        String message = "Xin chào " + Name;
                        Toast.makeText(MainActivity.this,message,Toast.LENGTH_SHORT).show();
                    }
            } else {
                Toast.makeText(this,"Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
            }
        }
    }


    private void savingPreferences(){
//        Tạo đối tượng getSharedPreferences
        SharedPreferences pre = this.getSharedPreferences(fileName,Context.MODE_PRIVATE);

        // Tạo đối tượng Editor để lưu thay đổi
        SharedPreferences.Editor editor = pre.edit();

//        lưu trữ dữ liệu dưới dạng key/value
        String user = medtEmail.getText().toString();
        String pw = medtPass.getText().toString();
        boolean bchk = mchkGhiNho.isChecked();

        if(!bchk){ // nếu người dùng không check
//            Xóa mọi thứ lưu trữ trước đó
            editor.clear();
        } else { // nếu người dùng check
//            lưu vào editor và hiển thị sẵn
            editor.putString("user",user);
            editor.putString("pw",pw);
            editor.putBoolean("checked",bchk);
        }
//        Chấp nhận lưu xuống file
        editor.commit();
    }

    public void restoringPreferences(){
        SharedPreferences pre = this.getSharedPreferences(fileName,Context.MODE_PRIVATE);
        if(pre != null){
            boolean bchk = pre.getBoolean("checked",false); // Lấy giá trị đã lưu trữ với key là "checked" nếu không có giá trị nào được lưu với key tương ứng, phương thức sẽ trả về false

            if(bchk){
                //  Lấy giá trị đã lưu trữ với key là "Email" và "Pw" nếu không có giá trị nào được lưu với key tương ứng, phương thức sẽ trả về chuỗi rỗng
                String user = pre.getString("user","");
                String pw = pre.getString("pw","");

                medtEmail.setText(user);
                medtPass.setText(pw);
            }
            mchkGhiNho.setChecked(bchk);
        }
    }

    @Override
    protected void onResume() {
        // gọi hàm đọc trạng thái
        super.onResume();
        restoringPreferences();
    }

    @Override
    protected void onPause() {
        // gọi hàm lưu trạng thái
        super.onPause();
        savingPreferences();
    }
}
