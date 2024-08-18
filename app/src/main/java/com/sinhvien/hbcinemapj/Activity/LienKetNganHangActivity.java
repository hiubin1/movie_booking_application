package com.sinhvien.hbcinemapj.Activity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.sinhvien.hbcinemapj.Class.BankSession;
import com.sinhvien.hbcinemapj.Class.Banks;
import com.sinhvien.hbcinemapj.Class.UserSession;
import com.sinhvien.hbcinemapj.Database.BankDatabase;
import com.sinhvien.hbcinemapj.R;

import java.util.ArrayList;

public class LienKetNganHangActivity extends AppCompatActivity {

    ActionBar actionBar;

    EditText medtCardNum,medtDateOfIssue,medtBankNameAcc;

    BankDatabase bankDatabase;

    String CardNum,DateOfIS, NameAcc,NameBank;

    private static long id = -1;

    ArrayList<String> dsTaiKhoan;
    ArrayAdapter<String> adapter;
    Spinner mSpnChonNganHang;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lien_ket_ngan_hang);
        medtDateOfIssue = (EditText) findViewById(R.id.edtDateOfIssue);
        medtCardNum = (EditText) findViewById(R.id.edtCardNum);
        medtBankNameAcc = (EditText) findViewById(R.id.edtBankNameAcc);
        bankDatabase = new BankDatabase(this);

        actionBar = getSupportActionBar();
        actionBar.setTitle("Liên kết ngân hàng");
        actionBar.setDisplayHomeAsUpEnabled(true);

        mSpnChonNganHang = (Spinner) findViewById(R.id.spnChonNganHang);

        dsTaiKhoan = new ArrayList<>();

        dsTaiKhoan.add("MBBank");
        dsTaiKhoan.add("Sacombank");
        dsTaiKhoan.add("Vietcombank");
        dsTaiKhoan.add("VPBank");

        adapter = new ArrayAdapter<>(LienKetNganHangActivity.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, dsTaiKhoan);
        mSpnChonNganHang.setAdapter(adapter);

        mSpnChonNganHang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                switch (position){
                    case 0:
                        NameBank = "MBBank";
                        break;
                    case 1:
                        NameBank = "Sacombank";
                        break;
                    case 2:
                        NameBank = "Vietcombank";
                        break;
                    case 3:
                        NameBank = "VPBank";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public  Banks layduLieuNguoiDung(){
        NameAcc = medtBankNameAcc.getText().toString();
        CardNum = medtCardNum.getText().toString();
        DateOfIS = medtDateOfIssue.getText().toString();

        Banks banks = new Banks();
        banks.setBankid(id);
        banks.setIdCusBank(UserSession.id);
        banks.setBankName(NameBank);
        banks.setBankNum(CardNum);
        banks.setBankAccName(NameAcc);
        banks.setNgayPhatHanh(DateOfIS);
        return banks;
    }


    public void btnLienKet_onclick(View view) {
        Banks banks = layduLieuNguoiDung();
        if(NameAcc.equals("") || CardNum.equals("") || DateOfIS.equals("")){
            Toast.makeText(LienKetNganHangActivity.this,"vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
        } else {
            if(banks != null){
                if (bankDatabase.them(banks) != -1){
                    id = -1;
                    Toast.makeText(LienKetNganHangActivity.this,"Thêm thành công", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
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