package com.sinhvien.hbcinemapj.Activity;

import static com.sinhvien.hbcinemapj.FragmentAdmin.AddPhimFragment.mRecyclerView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;

import com.sinhvien.hbcinemapj.Adapter.MoviesAdapter;
import com.sinhvien.hbcinemapj.Adapter.UserAdapter;
import com.sinhvien.hbcinemapj.Class.User;
import com.sinhvien.hbcinemapj.Database.DBHelper;
import com.sinhvien.hbcinemapj.Database.UserDatabse;
import com.sinhvien.hbcinemapj.R;

import java.util.ArrayList;

public class ThongTinKhachHangActivity extends AppCompatActivity {

    public static RecyclerView mRecycleView;

    public static RecyclerView.LayoutManager mlayoutManager;

    public static ArrayList<User> userArrayList;

    UserDatabse userDatabse;

    private UserAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_khach_hang);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Thông tin khách hàng");

        userDatabse = new UserDatabse(this);
        mRecycleView = (RecyclerView) findViewById(R.id.RVCusInfo);
        capNhatDuLieu();

    }

    @SuppressLint("Range")
    public void capNhatDuLieu(){
        if (userArrayList == null){
            userArrayList = new ArrayList<>();
        } else {
            userArrayList.removeAll(userArrayList);
        }
        Cursor cursor = userDatabse.layTatCaDuLieu();
        if (cursor != null){
            while (cursor.moveToNext()){
                User user = new User();
                user.setId(cursor.getInt(cursor.getColumnIndex(DBHelper.COT_ID_CUS)));
                user.set_Name(cursor.getString(cursor.getColumnIndex(DBHelper.COT_NAME)));
                user.set_Email(cursor.getString(cursor.getColumnIndex(DBHelper.COT_EMAIL)));
                user.set_PhoneNum(cursor.getString(cursor.getColumnIndex(DBHelper.COT_PHONE_NUM)));
                user.set_Gender(cursor.getString(cursor.getColumnIndex(DBHelper.COT_GENDER)));
                user.set_Birth(cursor.getString(cursor.getColumnIndex(DBHelper.COT_BIRTH)));
                user.setPic(cursor.getInt(cursor.getColumnIndex(DBHelper.COT_IMAGE_USER)));

                userArrayList.add(user);
            }
        }
        SetRV();
    }

    public void SetRV(){
        if (userArrayList != null){
            mlayoutManager = new GridLayoutManager(this, 1);
            mRecycleView.setLayoutManager(mlayoutManager);
            userAdapter = new UserAdapter(this, R.layout.layout_infocus, userArrayList);
            mRecycleView.setAdapter(userAdapter);
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