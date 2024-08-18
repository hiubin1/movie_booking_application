package com.sinhvien.hbcinemapj.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.sinhvien.hbcinemapj.Class.Tickets;
import com.sinhvien.hbcinemapj.Class.TicketsSession;
import com.sinhvien.hbcinemapj.Database.TicketsDatabase;
import com.sinhvien.hbcinemapj.R;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicInteger;

public class ChonGheActivity extends AppCompatActivity {

    TextView tvhoten,tvsdt,tvemail,tvtenphim,tvrapphim,tvphongphim,tvdinhdangphim,tvgiochieu,tvngaydat;
    TextView tvSetPriceNor, tvSetPriceVip;

    TableLayout tableSeat;

    CheckBox seat;
    int soLuongGhe = 0;
    float tongTien = 0;
    float price = 0;

    TicketsDatabase ticketsDatabase;

    Button btnThanhToan;

    String formattongTien;

    // xài StringBuilder
    StringBuilder ghe = new StringBuilder();


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chon_ghe);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Chọn Ghế Ngồi");

        ticketsDatabase = new TicketsDatabase(this);

        tvhoten = (TextView) findViewById(R.id.tvhoten);
        tvsdt = (TextView) findViewById(R.id.tvsdt);
        tvemail = (TextView) findViewById(R.id.tvemail);
        tvtenphim = (TextView) findViewById(R.id.tvtenphim);
        tvrapphim = (TextView) findViewById(R.id.tvrapphim);
        tvphongphim = (TextView) findViewById(R.id.tvphongphim);
        tvdinhdangphim = (TextView) findViewById(R.id.tvdinhdangphim);
        tvgiochieu= (TextView) findViewById(R.id.tvgiochiue);
        tvngaydat = (TextView) findViewById(R.id.tvngaydat);

        btnThanhToan = (Button) findViewById(R.id.btnThanhToan);
        tvSetPriceNor = (TextView) findViewById(R.id.tvSetPriceNor);
        tvSetPriceVip = (TextView) findViewById(R.id.tvSetPriceVip);

        // GHẾ

        tableSeat = (TableLayout) findViewById(R.id.tableSeat);


        // Dòng này tạo một mảng ID chỗ ngồi, được biểu thị dưới dạng chuỗi.
        String[] seatIds = {"A1", "A2", "A3", "A4", "A5", "A6", "A7", "A8", "A9", "A10",
                "B1", "B2", "B3", "B4", "B5", "B6", "B7", "B8", "B9", "B10",
                "C1", "C2", "C3", "C4", "C5", "C6", "C7", "C8", "C9", "C10",
                "D1", "D2", "D3", "D4", "D5", "D6", "D7", "D8", "D9", "D10"};


        for (String seatId : seatIds) { // Vòng lặp for duyệt qua từng phần tử seatId trong mảng seatIds.
            seat = findViewById(getResources().getIdentifier("seat" + seatId, "id", getPackageName())); // sử dụng hàm getIdentifier để tìm và trả về id của CheckBox tương ứng với mã định danh của ghế ngồi.
            seat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                    DecimalFormat decimalFormat = new DecimalFormat("###.###đ"); // hàm dùng để đổi format tiền
                    String value; // tạo biến value
                    if (seatId.charAt(0) < 'C'){ // kiểm tra nếu check hàng ghế A hoặc B
                        value = tvSetPriceNor.getText().toString().replaceAll("[^\\d.]+", ""); // set giá tiền là 120000 cho hàng ghế A và B
                    } else { // kiểm tra nếu check hàng C và D
                        value = tvSetPriceVip.getText().toString().replaceAll("[^\\d.]+", ""); // set giá tiền là 200000 cho hàng ghế C và D
                    }
                     // gán giá trị value vô biến price và ép kiểu float
                    price = Float.parseFloat(value); // cập nhật biến price khi chọn vào hàng ghế nào
                    if (isChecked) { // kiểm tra nếu check vào ghế
                        if (ghe.toString().equals("")) { // kiểm tra xem chuỗi ghế có dữ liệu nào chưa = cách kiểm tra chuỗi ghe = rỗng
                            ghe = new StringBuilder(seatId); // nếu rỗng thì ghế check dầu tiên là giá trị đầu tiên
                        } else { // Còn kiểm tra nếu đây không phải là lần chọn ghế đầu tiên
                            ghe.append(" " + seatId); // ta thêm khoảng cách trước ghế được check sau
                        }
                        tongTien += price; // Cập nhật tổng tiền tăng dần nếu check vào ghế
                    } else { // kiểm tra ngược lại nếu bỏ check
                        if (ghe.indexOf(seatId) != -1) { // Nếu chuỗi "seatId" xuất hiện ở vị trí khác "-1" (tức là xuất hiện trong chuỗi `ghe`)
                            ghe = new StringBuilder(ghe.toString().trim().replace(seatId, "").trim().replaceAll("\\s+"," ")); // dùng để xóa ghế nếu là dữ liệu đầu tiên
                        }
                        String gheDaChon = ghe.toString().trim(); // dùng để xóa ghế nếu là dữ liệu ở bất kì đầu trong chuỗi
                        ghe = new StringBuilder(gheDaChon); // gán giá trị gheDaChon vô biến ghe

                        tongTien -= price; // cập nhật tổng tiền giảm dần
                        price = Float.parseFloat(value);
                    }
                    formattongTien = decimalFormat.format(tongTien); // set format tiền vô giá trị tongTien
                    isCheckedOrNot(isChecked); // hàm dùng để tính tổng số lần check ghế
                    sortSeats(); // hàm sắp xếp thứ tự ghế theo A B C D và 1 2 3 4 5 6 7 8 9 10
                }
            });
        }


        Bundle c = getIntent().getExtras();
        tvhoten.setText(c.getString("hovaten"));
        tvsdt.setText(c.getString("sdt"));
        tvemail.setText(c.getString("email"));
        tvtenphim.setText(c.getString("tenMovie"));
        tvrapphim.setText(c.getString("tenRap"));
        tvphongphim.setText(c.getString("phong"));
        tvdinhdangphim.setText(c.getString("dinhDang"));
        tvgiochieu.setText(c.getString("gioChieu"));
        tvngaydat.setText(c.getString("ngayChieu"));


        btnThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (soLuongGhe == 0){
                    Toast.makeText(ChonGheActivity.this,"Bạn chưa chọn ghế nào !", Toast.LENGTH_SHORT).show();
                } else if(soLuongGhe > 8){
                    Toast.makeText(ChonGheActivity.this,"Không được chọn quá 8 ghế !",Toast.LENGTH_SHORT).show();
                } else {
                    long IdTKmovie = TicketsSession.TicketsId;
                    String Phong = tvphongphim.getText().toString();
                    String rapPhim = tvrapphim.getText().toString();
                    String time = tvgiochieu.getText().toString();
                    String date = tvngaydat.getText().toString();
                    StringBuilder Ghe = ghe;
                        boolean KiemTraGhe = ticketsDatabase.KiemTraGhe(IdTKmovie,rapPhim,Phong,time,date, new StringBuilder(Ghe));
                        if (KiemTraGhe){
                            Toast.makeText(ChonGheActivity.this,"Ghế " + ghe + " đã được chọn xin vui lòng chọn ghế khác",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        Intent i = new Intent(getApplicationContext(),XacNhanThanhToanActivity.class);
                        Bundle bundle = new Bundle();

                        bundle.putString("hovaten",tvhoten.getText().toString());
                        bundle.putString("sdt",tvsdt.getText().toString());
                        bundle.putString("email",tvemail.getText().toString());
                        bundle.putString("MovieName",tvtenphim.getText().toString());
                        bundle.putString("tenRap",tvrapphim.getText().toString());
                        bundle.putString("phong",tvphongphim.getText().toString());
                        bundle.putString("dinhDang",tvdinhdangphim.getText().toString());
                        bundle.putString("gioChieu",tvgiochieu.getText().toString());
                        bundle.putString("ngayChieu",tvngaydat.getText().toString());
                        bundle.putString("ghe", ghe.toString());
                        bundle.putInt("soLuongGhe", soLuongGhe);
                        bundle.putString("tongTien", formattongTien);
                        i.putExtras(bundle);
                        startActivity(i);
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

    private void isCheckedOrNot(boolean isChecked) {
        if (isChecked){
            soLuongGhe++; // nếu check bao nhiêu ghế sẽ cộng dồn lên
        } else {
            soLuongGhe--; // bỏ check sẽ trừ ghế đó ra
        }
    }

//    Hàm sắp ghế theo thứ tự A B C D và 1 2 3 4 5 6 7 8 9 10
    private void sortSeats() {
        String[] seats = ghe.toString().split(" ");
        Arrays.sort(seats, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                String prefix1 = s1.substring(0, 1); // lấy ký tự đầu tiên của s1
                String prefix2 = s2.substring(0, 1); // lấy ký tự đầu tiên của s2
                if (prefix1.equals(prefix2)) {
                    int seatNum1 = Integer.parseInt(s1.substring(1)); // lấy số ghế của s1
                    int seatNum2 = Integer.parseInt(s2.substring(1)); // lấy số ghế của s2
                    return seatNum1 - seatNum2; // so sánh theo số ghế
                } else {
                    return prefix1.compareTo(prefix2); // so sánh theo ký tự đầu tiên
                }
            }
        });
        ghe = new StringBuilder(TextUtils.join(" ", seats));
    }
}


