package com.sinhvien.hbcinemapj.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sinhvien.hbcinemapj.Database.UserDatabse;
import com.sinhvien.hbcinemapj.R;

public class DoiMatKhauActivity extends AppCompatActivity {

    EditText medtEmailRSPass, medtRSPass, medtConfirmRSPass;
    Button mbtnReset;

    UserDatabse userDatabse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doi_mat_khau);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Đổi mật khẩu");

        medtEmailRSPass = (EditText) findViewById(R.id.edtEmailRSPass);
        medtRSPass = (EditText) findViewById(R.id.edtRSPass);
        medtConfirmRSPass = (EditText) findViewById(R.id.edtConfirmRSPass);
        mbtnReset = (Button) findViewById(R.id.btnReset);
        userDatabse = new UserDatabse(this);

        mbtnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email,pass,CFPass;

                email = medtEmailRSPass.getText().toString();
                pass = medtRSPass.getText().toString();
                CFPass = medtConfirmRSPass.getText().toString();

                if (email.equals("") || pass.equals("") || CFPass.equals("")){
                    Toast.makeText(DoiMatKhauActivity.this,"bạn chưa nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    if (pass.equals(CFPass)) {
                        int UpdatePass = userDatabse.UpdatePass(email,pass);
                        if (UpdatePass == 1){
                            medtEmailRSPass.setText("");
                            medtConfirmRSPass.setText("");
                            Toast.makeText(DoiMatKhauActivity.this,"Thay đổi mật khẩu thành công",Toast.LENGTH_SHORT).show();
                            AlertDialog.Builder builder = new AlertDialog.Builder(DoiMatKhauActivity.this);
                            builder.setTitle("Thông báo");
                            builder.setMessage("Bạn có muốn đăng xuất và đăng nhập lại bằng mật khẩu mới ?");
                            builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            });
                            builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                }
                            });
                            builder.create().show();
                        } else {
                            Toast.makeText(DoiMatKhauActivity.this,"Thay đổi mật khẩu thất bại",Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(DoiMatKhauActivity.this,"2 mật khẩu không trùng khớp",Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });
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