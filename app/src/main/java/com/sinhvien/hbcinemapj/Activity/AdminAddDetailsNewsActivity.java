package com.sinhvien.hbcinemapj.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sinhvien.hbcinemapj.Class.AddNews;
import com.sinhvien.hbcinemapj.Class.News;
import com.sinhvien.hbcinemapj.Class.NewsSession;
import com.sinhvien.hbcinemapj.Database.AddNewsDatabase;
import com.sinhvien.hbcinemapj.Database.DBHelper;
import com.sinhvien.hbcinemapj.R;

import java.util.ArrayList;

public class AdminAddDetailsNewsActivity extends AppCompatActivity {
    EditText mtvEditNewsDetailsName,mtvEditNewsDetailsRapApDung,mtvEditNewsDetailsDate,mtvEditNewsDetailsDesc;

    String Name, Date, RapApDung, NoiDung;

    ImageView mimgNews;
    Button mbtnSua, mbtnXoa;
    AddNewsDatabase addNewsDatabase;

    ArrayList<AddNews> addNewsArrayList;

    AddNews xoa = new AddNews();

    long id = -1;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_details_news);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Chỉnh sửa tin tức");

        mtvEditNewsDetailsName = findViewById(R.id.tvEditNewsDetailsName);
        mtvEditNewsDetailsRapApDung = findViewById(R.id.tvEditNewsDetailsRapApDung);
        mtvEditNewsDetailsDate = findViewById(R.id.tvEditNewsDetailsDate);
        mtvEditNewsDetailsDesc = findViewById(R.id.tvEditNewsDetailsDesc);
        mimgNews = findViewById(R.id.imgAddNewsDetailsImage);

        addNewsDatabase = new AddNewsDatabase(this);
        mbtnXoa = findViewById(R.id.btnDBNewsDelete);
        mbtnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AdminAddDetailsNewsActivity.this);
                builder.setTitle("Thông báo");
                builder.setMessage("Bạn có muốn xóa tin tức này ?");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        addNewsDatabase.delTinTuc(xoa);
                        Toast.makeText(AdminAddDetailsNewsActivity.this,"Xóa thành công",Toast.LENGTH_SHORT).show();
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
            }
        });

        mbtnSua = findViewById(R.id.btnDBNewsEdit);
        mbtnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddNews addNews = layDuLieuNguoiDung();
                String nameNews = mtvEditNewsDetailsName.getText().toString();
                boolean KiemTraEditTinTuc = addNewsDatabase.KiemTraEditTinTuc(nameNews);
                if (KiemTraEditTinTuc){
                    Toast.makeText(AdminAddDetailsNewsActivity.this,"Tin tức này đã tồn tại",Toast.LENGTH_SHORT).show();
                } else {
                    if(addNews != null){
                        if(addNewsDatabase.EditTinTuc(addNews) != -1){
                            Toast.makeText(AdminAddDetailsNewsActivity.this,"Chỉnh sửa thành công",Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                }
            }
        });

        Intent i = getIntent();
        Bundle b = i.getExtras();
        id = b.getLong("ID");

        capNhatDuLieu();

    }

    public AddNews layDuLieuNguoiDung(){
        Name = mtvEditNewsDetailsName.getText().toString();
        Date = mtvEditNewsDetailsDate.getText().toString();
        RapApDung = mtvEditNewsDetailsRapApDung.getText().toString();
        NoiDung = mtvEditNewsDetailsDesc.getText().toString();

        AddNews addNews = new AddNews();

        addNews.setAddNewsID(id);
        addNews.setAddNewsName(Name);
        addNews.setAddNewsContent(NoiDung);
        addNews.setAddNewsDate(Date);
        addNews.setAddNewsRapApDung(RapApDung);

        return addNews;
    }

    @SuppressLint("Range")
    public void capNhatDuLieu(){
        if (addNewsArrayList == null){
            addNewsArrayList = new ArrayList<AddNews>();
        } else {
            addNewsArrayList.removeAll(addNewsArrayList);
        }

        Cursor cursor = addNewsDatabase.getDataFromID(id);
        if (cursor!= null && cursor.getCount() > 0 && cursor.moveToFirst()){
            do {
                int idNews = cursor.getInt(cursor.getColumnIndex(DBHelper.COT_NEWS_ID));
                if(idNews == id){
                    AddNews addNews = new AddNews();
                    addNews.setAddNewsID(cursor.getInt(cursor.getColumnIndex(DBHelper.COT_NEWS_ID)));

                    addNews.setAddNewsName(cursor.getString(cursor.getColumnIndex(DBHelper.COT_NEWS_NAME)));

                    addNews.setAddNewsDate(cursor.getString(cursor.getColumnIndex(DBHelper.COT_NEWS_DATE)));

                    addNews.setAddNewsContent(cursor.getString(cursor.getColumnIndex(DBHelper.COT_NEWS_DESCRIPTION)));

                    addNews.setAddNewsRapApDung(cursor.getString(cursor.getColumnIndex(DBHelper.COT_NEWS_RAP)));

                    addNews.setAddNewsPic(cursor.getInt(cursor.getColumnIndex(DBHelper.COT_NEWS_BANNER)));

                    mtvEditNewsDetailsName.setText(addNews.getAddNewsName());

                    mtvEditNewsDetailsDate.setText(addNews.getAddNewsDate());

                    mtvEditNewsDetailsDesc.setText(addNews.getAddNewsContent());

                    mtvEditNewsDetailsRapApDung.setText(addNews.getAddNewsRapApDung());

                    mimgNews.setImageResource(addNews.getAddNewsPic());

                    xoa.setAddNewsID(addNews.getAddNewsID());

                    break;
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}