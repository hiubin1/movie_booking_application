package com.sinhvien.hbcinemapj.Activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.sinhvien.hbcinemapj.Class.NewsSession;
import com.sinhvien.hbcinemapj.Database.AddNewsDatabase;
import com.sinhvien.hbcinemapj.Class.AddNews;
import com.sinhvien.hbcinemapj.R;

import java.util.ArrayList;

public class AddDBNewsPageActivity extends AppCompatActivity {

    AddNewsDatabase addNewsDatabase;

    Spinner mspnLinkBaner;

    EditText medtAddNewsName,medtAddMewsContent,medtAddNewsDate,medtAddNewsRapApDung,medtAddNewsBanner;

    Button mbtnAddNews;

    String TenTinTuc, NoiDung, NgayDang, RapApDung;
    int Banner;

    private long id = -1;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dbnews_page);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Thêm Tin Tức");

        addNewsDatabase = new AddNewsDatabase(this);

        medtAddNewsName = (EditText) findViewById(R.id.edtAddNewsName);
        medtAddNewsDate =(EditText) findViewById(R.id.edtAddNewsUploadDate);
        medtAddMewsContent = (EditText) findViewById(R.id.edtAddNewsDescription);
        medtAddNewsRapApDung = (EditText) findViewById(R.id.edtAddNewsRapApDung);
        mbtnAddNews = (Button) findViewById(R.id.btnAddNews);


        mspnLinkBaner = (Spinner) findViewById(R.id.spnLinkBanner);
        ArrayList<String> LinkBanner = new ArrayList<String>();
        LinkBanner.add("Mỹ Quyền Không Cần Khuôn Mẫu");
        LinkBanner.add("KATE WNSLET Bị Body Shaming");
        LinkBanner.add("Bộ Sưu Tập Hot Food K-TASTE");
        LinkBanner.add("Ký Sự Điện Ảnh - Kỳ 21");
        LinkBanner.add("Review Siêu Lừa Gặp Siêu Lầy");
        ArrayAdapter adapter = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, LinkBanner);
        mspnLinkBaner.setAdapter(adapter);

        mspnLinkBaner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                switch (position){
                    case 0:
                        Banner = R.drawable.news1;
                        break;
                    case 1:
                        Banner = R.drawable.news2;
                        break;
                    case 2:
                        Banner = R.drawable.news3;
                        break;
                    case 3:
                        Banner = R.drawable.news4;
                        break;
                    case 4:
                        Banner = R.drawable.news5;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
   }



    public AddNews layDuLieuNgDung(){
        TenTinTuc = medtAddNewsName.getText().toString();
        NgayDang = medtAddNewsDate.getText().toString();
        NoiDung = medtAddMewsContent.getText().toString();
        RapApDung = medtAddNewsRapApDung.getText().toString();



        AddNews addNews = new AddNews();
        addNews.setAddNewsID(id);
        addNews.setAddNewsName(TenTinTuc);
        addNews.setAddNewsContent(NoiDung);
        addNews.setAddNewsDate(NgayDang);
        addNews.setAddNewsPic(Banner);
        addNews.setAddNewsRapApDung(RapApDung);

        return addNews;
    }

    public void btnThemTinTucVoDB_onclick(View view) {
        AddNews addNews = layDuLieuNgDung();
        if (TenTinTuc.equals("") || NoiDung.equals("") || NgayDang.equals("") || RapApDung.equals("")){
            Toast.makeText(AddDBNewsPageActivity.this,"Vui lòng nhập đầy đủ thông tin của tin tức",Toast.LENGTH_SHORT).show();
        } else {
            String tenTinTuc = medtAddNewsName.getText().toString();
            int pic = Banner;
            boolean KiemTraTinTuc = addNewsDatabase.KiemTraTinTuc(tenTinTuc,pic);
            if (KiemTraTinTuc){
                Toast.makeText(AddDBNewsPageActivity.this,"Tin tức này đã tồn tại",Toast.LENGTH_SHORT).show();
            } else {
                if(addNews != null){
                    if(addNewsDatabase.addTinTuc(addNews) != -1){
                        id = -1;
                        Toast.makeText(this,"Thêm thành công",Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            }
        }
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