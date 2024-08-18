package com.sinhvien.hbcinemapj.Activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.sinhvien.hbcinemapj.Adapter.BankAdapter;
import com.sinhvien.hbcinemapj.Class.BankSession;
import com.sinhvien.hbcinemapj.Class.Tickets;
import com.sinhvien.hbcinemapj.Class.TicketsSession;
import com.sinhvien.hbcinemapj.Class.User;
import com.sinhvien.hbcinemapj.Class.UserSession;
import com.sinhvien.hbcinemapj.Database.BankDatabase;
import com.sinhvien.hbcinemapj.Database.DBHelper;
import com.sinhvien.hbcinemapj.Database.TicketsDatabase;
import com.sinhvien.hbcinemapj.R;
import com.sinhvien.hbcinemapj.Class.Banks;

import java.util.ArrayList;

public class ChonNganHangActivity extends AppCompatActivity {

    private int selectedItemPosition = -1;

    String NameCus,EmailCus,phoneCus, tenPhim, tenRap, phong, dinhDang,
    gioChieu, ngayChieu, gheNgoi, soLuongGhe, tongTien;

    ArrayList<Banks> banksArrayList;

    String time,room;
    BankAdapter adapter;

    BankDatabase bankDatabase;

    private long id = -1;


    TicketsDatabase ticketsDatabase;
    ListView mLvBank;
    ActionBar actionBar;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chon_ngan_hang);
        ticketsDatabase = new TicketsDatabase(this);
        bankDatabase = new BankDatabase(this);

        actionBar = getSupportActionBar();
        actionBar.setTitle("Thanh Toán");
        actionBar.setDisplayHomeAsUpEnabled(true);


        Bundle b = getIntent().getExtras();
        NameCus = b.getString("NameCus");
        EmailCus = b.getString("EmailCus");
        phoneCus = b.getString("sdt");
        tenPhim = b.getString("NameMovie");
        tenRap = b.getString("NameLocation");
        phong = b.getString("RoomName");
        dinhDang = b.getString("DinhDang");
        gioChieu = b.getString("GioChieu");
        ngayChieu = b.getString("NgayChieu");
        gheNgoi = b.getString("GheNgoi");
        soLuongGhe = b.getString("SoLuongGhe");
        tongTien = b.getString("TongTien");

        capNhatDuLieu();
    }

    public void FAbtnThemNganHang_onclick(View view) {
        Intent i = new Intent(getApplicationContext(),LienKetNganHangActivity.class);
        startActivity(i);
    }

    @SuppressLint("Range")
    public void capNhatDuLieu(){
        if (banksArrayList == null){
            banksArrayList = new ArrayList<Banks>();
        } else {
            banksArrayList.removeAll(banksArrayList);
        }
        long IdCusBank = UserSession.id;
        Cursor cursor = bankDatabase.GetAllFromIdCus(IdCusBank);
        if (cursor != null) {
            while (cursor.moveToNext()){
                Banks banks = new Banks();

                banks.setBankid(cursor.getInt(cursor.getColumnIndex(DBHelper.COT_IDBANK)));
                banks.setIdCusBank(cursor.getInt(cursor.getColumnIndex(DBHelper.COT_IDCUSBANK)));
                banks.setBankName(cursor.getString(cursor.getColumnIndex(DBHelper.COT_BANKNAME)));
                banks.setBankNum(cursor.getString(cursor.getColumnIndex(DBHelper.COT_BANKNUM)));
                banks.setNgayPhatHanh(cursor.getString(cursor.getColumnIndex(DBHelper.COT_DATEBANK)));
                banks.setBankAccName(cursor.getString(cursor.getColumnIndex(DBHelper.COT_NAMEUSERBANK)));

                banksArrayList.add(banks);
            }
        }
        SetLV();
    }
    public void SetLV(){
        if (banksArrayList != null ){
            mLvBank = (ListView) findViewById(R.id.lvTkNganHang);
            adapter = new BankAdapter(ChonNganHangActivity.this, R.layout.tk_ngan_hang, banksArrayList);
            mLvBank.setAdapter(adapter);
        }
    }

    public Tickets layDuLieuNguoiDung(){
        Tickets tickets = new Tickets();

        tickets.setId(id);

        tickets.setIdTicketsMovie(TicketsSession.TicketsId);
        tickets.setIdCus(UserSession.id);
        tickets.setNameCus(NameCus);
        tickets.setEmailCus(UserSession.userEmail);
        tickets.setPhoneCus(phoneCus);
        tickets.setFormatMovies(dinhDang);
        tickets.setNameMovies(tenPhim);
        tickets.setSeats(gheNgoi);
        tickets.setTime(gioChieu);
        tickets.setQuantity(soLuongGhe);
        tickets.setLocation(tenRap);
        tickets.setPhongChieu(phong);
        tickets.setBookingDate(ngayChieu);
        tickets.setTotal(tongTien);
        room = tickets.getPhongChieu();
        time = tickets.getTime();
        TicketsSession.time = time;
        TicketsSession.Room = room;

        return tickets;

    }



    @SuppressLint("Range")
    public void btnThanhToan_onclick(View view) {
        Tickets tickets = layDuLieuNguoiDung();
        int checkedCount = 0;
        int checkedIndex = -1;
        for (int i = 0; i < mLvBank.getChildCount(); i++){
            View childView = mLvBank.getChildAt(i);
            CheckBox checkBox = (CheckBox) childView.findViewById(R.id.cbChoose);
            if (checkBox.isChecked()){
                checkedCount++;
                checkedIndex = i;
            }
        }
        if (checkedCount == 0){
            Toast.makeText(ChonNganHangActivity.this, "Bạn chưa chọn tài khoản ngân hàng để thanh toán", Toast.LENGTH_SHORT).show();
            return;
        }
        if (checkedCount > 1){
            Toast.makeText(ChonNganHangActivity.this, "Bạn chỉ được chọn một tài khoản ngân hàng để thanh toán", Toast.LENGTH_SHORT).show();
            return;
        }
        // Lấy thông tin ngân hàng tại vị trí checkedIndex và hiển thị toast
        Cursor cursor = bankDatabase.GetAllFromIdBank(checkedIndex);
        cursor.close();
        if (tickets != null){
            if(ticketsDatabase.them(tickets) != -1){
                AlertDialog.Builder b = new AlertDialog.Builder(ChonNganHangActivity.this);
                b.setTitle("Thông báo");
                b.setMessage("Đặt vé thành công \u2764"
                        + "\n" + "-----------------------------------------"
                        + "\n" + "Chúc các homies xem phim vui vẻ !");
                b.setPositiveButton("Đóng", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent h = new Intent(getApplicationContext(),CustomerActivity.class);
                        startActivity(h);
                        dialogInterface.cancel();
                    }
                });
                b.create().show();
            }
        }
        adapter.notifyDataSetChanged();
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
