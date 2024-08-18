package com.sinhvien.hbcinemapj.Activity;

import android.annotation.SuppressLint;

import android.content.Intent;
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

import com.sinhvien.hbcinemapj.Database.AddPhimDatabase;
import com.sinhvien.hbcinemapj.Class.AddMovies;
import com.sinhvien.hbcinemapj.R;

import java.util.ArrayList;

public class AddDBMoviesPageActivity extends AppCompatActivity {

    AddPhimDatabase addPhimDatabase;
    Spinner mspnType;
    EditText medtAddName, medtAddActors, medtAddDirector, medtAddDescription, medtAddReleasedDate, medtAddMovieLength,
            medtAddLanguage, medtAddAge;


    Spinner mspnLinkImage;
    Spinner mspnLinkBannerPhim;
    String TheLoai, TenPhim, TenActors, TenDirector, MoTa, NgayKhoiChieu, ThoiLuong, NgonNgu, Age;

    int linkAnh;
    int linkBannerPhim;
    private long id = -1;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dbmovies_page);



        //Database
        addPhimDatabase = new AddPhimDatabase(this);

        medtAddName = (EditText) findViewById(R.id.edtAddNameMovie);
        medtAddActors = (EditText) findViewById(R.id.edtAddNameActor);
        medtAddDirector = (EditText) findViewById(R.id.edtAddNameDirector);
        medtAddDescription = (EditText) findViewById(R.id.edtAddDesc);
        medtAddReleasedDate = (EditText) findViewById(R.id.edtAddDate);
        medtAddMovieLength = (EditText) findViewById(R.id.edtAddTime);
        medtAddLanguage = (EditText) findViewById(R.id.edtAddLanguage);
        medtAddAge = (EditText) findViewById(R.id.edtAddAge);

        //Set Spinner cho Thể loại
        mspnType = (Spinner) findViewById(R.id.spnType);
        ArrayList<String> dsTheLoai = new ArrayList<String>();
        dsTheLoai.add("Khoa học viễn tưởng");
        dsTheLoai.add("Gia đình");
        dsTheLoai.add("Phiêu lưu");
        dsTheLoai.add("Hồi hộp");
        dsTheLoai.add("Tâm lý");
        dsTheLoai.add("Tài liệu");
        dsTheLoai.add("Hoạt hình");
        dsTheLoai.add("Lãng mạn");
        dsTheLoai.add("Kinh dị");
        dsTheLoai.add("Hành động");
        dsTheLoai.add("Hài");
        ArrayAdapter adap = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, dsTheLoai);
        mspnType.setAdapter(adap);

        //Làm Spinnerrrrrr
        mspnType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                switch (position) {
                    case 0:
                        TheLoai = "Khoa học viễn tưởng";
                        break;
                    case 1:
                        TheLoai = "Gia đình";
                        break;
                    case 2:
                        TheLoai = "Phiêu lưu";
                        break;
                    case 3:
                        TheLoai = "Hồi hộp";
                        break;
                    case 4:
                        TheLoai = "Tâm lý";
                        break;
                    case 5:
                        TheLoai = "Tài liệu";
                        break;
                    case 6:
                        TheLoai = "Hoạt hình";
                        break;
                    case 7:
                        TheLoai = "Lãng mạn";
                        break;
                    case 8:
                        TheLoai = "Kinh dị";
                        break;
                    case 9:
                        TheLoai = "Hành động";
                        break;
                    case 10:
                        TheLoai = "Hài";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //Link Ảnh
        mspnLinkImage = (Spinner) findViewById(R.id.spnLinkImage);
        ArrayList<String> LinkAnh = new ArrayList<String>();

        LinkAnh.add("Everything Everyone All At Once");
        LinkAnh.add("Siêu Lừa Gặp Siêu Lầy");
        LinkAnh.add("Kamen Rider");
        LinkAnh.add("Khi Ta Hai Lăm");
        LinkAnh.add("Tìm Nhà Cho Boss");
        LinkAnh.add("Missing");
        LinkAnh.add("Ant Man");
        LinkAnh.add("Nhà Bà Nữ");
        LinkAnh.add("Shazam");
        LinkAnh.add("Tom And Jerry");
        LinkAnh.add("Nghi Thức Trử Tà");
        LinkAnh.add("Suzume: Khóa Chặt Cửa Nào");

        LinkAnh.add("Biệt Đội Rất Ổn");
        LinkAnh.add("Big George Foreman");
        LinkAnh.add("Tri Kỷ");
        LinkAnh.add("Tay Sai Của Quỷ");
        LinkAnh.add("Khắc Tinh Của Quỷ");
        ArrayAdapter adapter = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, LinkAnh);
        mspnLinkImage.setAdapter(adapter);

        mspnLinkImage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                switch (position) {

                    case 0:
                        linkAnh = R.drawable.everthingeverwhere;
                        break;
                    case 1:
                        linkAnh = R.drawable.sieuluagapsieulay;
                        break;
                    case 2:
                        linkAnh = R.drawable.kamenrider;
                        break;
                    case 3:
                        linkAnh = R.drawable.khita25;
                        break;
                    case 4:
                        linkAnh = R.drawable.tncb;
                        break;
                    case 5:
                        linkAnh = R.drawable.messing;
                        break;
                    case 6:
                        linkAnh = R.drawable.nguoikien3;
                        break;
                    case 7:
                        linkAnh = R.drawable.nhabanu;
                        break;
                    case 8:
                        linkAnh = R.drawable.shazam;
                        break;
                    case 9:
                        linkAnh = R.drawable.tomandjerry;
                        break;
                    case 10:
                        linkAnh = R.drawable.nttt;
                        break;
                    case 11:
                        linkAnh = R.drawable.suzume;
                        break;
                    case 12:
                        linkAnh = R.drawable.bdro;
                        break;
                    case 13:
                        linkAnh = R.drawable.bgf;
                        break;
                    case 14:
                        linkAnh = R.drawable.tk;
                        break;
                    case 15:
                        linkAnh = R.drawable.tscq;
                        break;
                    case 16:
                        linkAnh = R.drawable.ktcq;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        mspnLinkBannerPhim = (Spinner) findViewById(R.id.spnLinkBannerPhim);
        ArrayList<String> LinkBanner = new ArrayList<String>();
        LinkBanner.add("Everything Everyone All At Once");
        LinkBanner.add("Siêu Lừa Gặp Siêu Lầy");
        LinkBanner.add("Kamen Rider");
        LinkBanner.add("Khi Ta Hai Lăm");
        LinkBanner.add("Tìm Nhà Cho Boss");
        LinkBanner.add("Missing");
        LinkBanner.add("Ant Man");
        LinkBanner.add("Nhà Bà Nữ");
        LinkBanner.add("Shazam");
        LinkBanner.add("Tom And Jerry");
        LinkBanner.add("Nghi Thức Trừ Tà");
        LinkBanner.add("Suzume: Khóa Chặt Cửa Nào");
        LinkBanner.add("Biệt Đội Rất Ổn");
        LinkBanner.add("Big George Foreman");
        LinkBanner.add("Tri Kỷ");
        LinkBanner.add("Tay Sai Của Quỷ");
        LinkBanner.add("Khắc Tinh Của Quỷ");
        ArrayAdapter adapter1 = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, LinkBanner);
        mspnLinkBannerPhim.setAdapter(adapter1);

        mspnLinkBannerPhim.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                switch (position) {
                    case 0:
                        linkBannerPhim = R.drawable.bannereverythingeverywhereallatone;
                        break;
                    case 1:
                        linkBannerPhim = R.drawable.bannerslgsl;
                        break;
                    case 2:
                        linkBannerPhim = R.drawable.bannerkamenrider;
                        break;
                    case 3:
                        linkBannerPhim = R.drawable.bannerkt25;
                        break;
                    case 4:
                        linkBannerPhim = R.drawable.bannertncb;
                        break;
                    case 5:
                        linkBannerPhim = R.drawable.bannermt;
                        break;
                    case 6:
                        linkBannerPhim = R.drawable.bannerantman;
                        break;
                    case 7:
                        linkBannerPhim = R.drawable.bannernhabanu;
                        break;
                    case 8:
                        linkBannerPhim = R.drawable.bannershazam;
                        break;
                    case 9:
                        linkBannerPhim = R.drawable.bannertomandjerry;
                        break;
                    case 10:
                        linkBannerPhim = R.drawable.bannernttt;
                        break;
                    case 11:
                        linkBannerPhim = R.drawable.bannersuzume;
                        break;
                    case 12:
                        linkBannerPhim = R.drawable.bannerbdro;
                        break;
                    case 13:
                        linkBannerPhim = R.drawable.bannerbgf;
                        break;
                    case 14:
                        linkBannerPhim = R.drawable.bannertk;
                        break;
                    case 15:
                        linkBannerPhim = R.drawable.bannertscq;
                        break;
                    case 16:
                        linkBannerPhim = R.drawable.bannerktcq;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Thêm Phim");
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

    //Database
    public AddMovies layDuLieuNgDung() {
        TenPhim = medtAddName.getText().toString();
        TenActors = medtAddActors.getText().toString();
        TenDirector = medtAddDirector.getText().toString();
        MoTa = medtAddDescription.getText().toString();
        NgayKhoiChieu = medtAddReleasedDate.getText().toString();
        ThoiLuong = medtAddMovieLength.getText().toString();
        NgonNgu = medtAddLanguage.getText().toString();
        Age = medtAddAge.getText().toString();



        AddMovies addMovies = new AddMovies();
        addMovies.setAddMovieID(id);
        addMovies.setAddMovieType(TheLoai);
        addMovies.setAddMovieName(TenPhim);
        addMovies.setAddMoiveActors(TenActors);
        addMovies.setAddMovieDirector(TenDirector);
        addMovies.setAddMovieDesc(MoTa);
        addMovies.setAddMovieReleasedDate(NgayKhoiChieu);
        addMovies.setAddMovieLength(ThoiLuong);
        addMovies.setAddMovieLanguage(NgonNgu);
        addMovies.setAddMovieAge(Age);
        addMovies.setAddMoviePic(linkAnh);
        addMovies.setAddMoiveBanner(linkBannerPhim);

        return addMovies;
    }

    public void btnThemVoDatabase_onClick(View view) {
        AddMovies addMovies = layDuLieuNgDung();
        if (TenPhim.equals("") || TenActors.equals("") || TenDirector.equals("") || MoTa.equals("") || NgayKhoiChieu.equals("") || ThoiLuong.equals("") || NgonNgu.equals("") || Age.equals("")){
            Toast.makeText(AddDBMoviesPageActivity.this,"Vui lòng nhập đầy đủ thông tin  bộ phim", Toast.LENGTH_SHORT).show();
        } else {
            String tenPhim = medtAddName.getText().toString();
            int pic = linkAnh;
            int banner = linkBannerPhim;
            boolean KiemTraPhim = addPhimDatabase.KiemTraPhim(tenPhim,pic,banner);
            if (KiemTraPhim){
                Toast.makeText(AddDBMoviesPageActivity.this,"Bộ phim này đã tồn tại",Toast.LENGTH_SHORT).show();
            } else {
                if (addMovies != null) {
                    if (addPhimDatabase.addPhim(addMovies) != -1) {
                        id = -1;
                        Toast.makeText(this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),AdminActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            }
        }
    }
}