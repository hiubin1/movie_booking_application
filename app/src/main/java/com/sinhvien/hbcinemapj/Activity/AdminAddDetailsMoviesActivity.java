package com.sinhvien.hbcinemapj.Activity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.sinhvien.hbcinemapj.Class.AddMovies;
import com.sinhvien.hbcinemapj.Class.MoviesSession;
import com.sinhvien.hbcinemapj.Database.AddPhimDatabase;
import com.sinhvien.hbcinemapj.Database.DBHelper;
import com.sinhvien.hbcinemapj.R;

import java.io.File;
import java.util.ArrayList;

public class AdminAddDetailsMoviesActivity extends AppCompatActivity {

    EditText mtxtName, mtxtAge, mtxtLength, mtxtNgayChieu, mtxtDienVien, mtxtDaoDien, mtxtNoiDung, mtxtNgonNgu;
    TextView mtvTypeMovieEditDetails;

    Spinner mspnAddDetailsType;
    Button mbtnXoa, mbtnSua;

    String Name,Age,Length,NgayChieu,DienVien,DaoDien,NoiDung,NgonNgu,TheLoai;

    ImageView mimgPoster, mimgBanner;

    ArrayList<AddMovies> addMovieList;

    AddPhimDatabase addPhimDatabase;

    long id = - 1;


    AddMovies xoa = new AddMovies();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_details_movies);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Chỉnh sửa phim");


        mtxtName = findViewById(R.id.txtAddDetailsName);
        mtvTypeMovieEditDetails = findViewById(R.id.tvTypeMovieEditDetails);
        mtxtAge = findViewById(R.id.txtAddDetailsAge);
        mtxtLength = findViewById(R.id.txtAddDetailsLength);
        mtxtNgayChieu = findViewById(R.id.txtAddDetailsReleasedDate);
        mtxtDienVien = findViewById(R.id.txtAddDetailsActors);
        mtxtDaoDien = findViewById(R.id.txtAddDetailsDirector);
        mtxtNoiDung = findViewById(R.id.txtAddDetailsDesc);
        mtxtNgonNgu = findViewById(R.id.txtAddDetailsLanguage);

        mimgPoster = findViewById(R.id.imgAddDetailsImage);
        mimgBanner = findViewById(R.id.imgAddDetailsBanner);

        mbtnSua = findViewById(R.id.btnDBEdit);
        mbtnXoa = findViewById(R.id.btnDBDelete);
        mbtnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AdminAddDetailsMoviesActivity.this);
                builder.setTitle("Thông báo");
                builder.setMessage("Bạn có muốn xóa bộ phim này ?");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        addPhimDatabase.delPhim(xoa);
                        Toast.makeText(AdminAddDetailsMoviesActivity.this, "Xoá thành công", Toast.LENGTH_SHORT).show();
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

        mspnAddDetailsType = findViewById(R.id.spnAddDetailsType);
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
        mspnAddDetailsType.setAdapter(adap);

        //Làm Spinnerrrrrr
        mspnAddDetailsType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
                mtvTypeMovieEditDetails.setText(TheLoai);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        

        addPhimDatabase = new AddPhimDatabase(this);

        Intent i = getIntent();
        Bundle b = i.getExtras();
        id = b.getLong("ID");

        Log.i("DEBUG", String.valueOf(id));

        capNhatDuLieu();

    }

        public AddMovies layDuLieuNguoiDung(){
        Name = mtxtName.getText().toString();
        Age = mtxtAge.getText().toString();
        Length = mtxtLength.getText().toString();
        NgayChieu = mtxtNgayChieu.getText().toString();
        DienVien = mtxtDienVien.getText().toString();
        DaoDien = mtxtDaoDien.getText().toString();
        NoiDung = mtxtNoiDung.getText().toString();
        NgonNgu = mtxtNgonNgu.getText().toString();
        TheLoai = mtvTypeMovieEditDetails.getText().toString();


        AddMovies addMovies = new AddMovies();

        addMovies.setAddMovieID(id);
        addMovies.setAddMovieType(TheLoai);
        addMovies.setAddMovieName(Name);
        addMovies.setAddMovieAge(Age);
        addMovies.setAddMovieLength(Length);
        addMovies.setAddMovieReleasedDate(NgayChieu);
        addMovies.setAddMoiveActors(DienVien);
        addMovies.setAddMovieDirector(DaoDien);
        addMovies.setAddMovieDesc(NoiDung);
        addMovies.setAddMovieLanguage(NgonNgu);


        return addMovies;
    }

    @SuppressLint("Range")
    public void capNhatDuLieu() {
        if(addMovieList == null) {
            addMovieList = new ArrayList<AddMovies>();
        } else {
            addMovieList.removeAll(addMovieList);
        }

        Cursor cursor = addPhimDatabase.layDataTheoID(id);
        if(cursor != null && cursor.getCount() > 0 && cursor.moveToFirst()) {
            do {
                int idMovies = cursor.getInt(cursor.getColumnIndex(DBHelper.COT_ID));
                if(idMovies == id) {
                    AddMovies addMovies = new AddMovies();
                    addMovies.setAddMovieID(Integer.parseInt
                            (cursor.getString(cursor.getColumnIndex(DBHelper.COT_ID))));

                    addMovies.setAddMovieName(cursor.getString
                            (cursor.getColumnIndex(DBHelper.COT_TEN_PHIM)));

                    addMovies.setAddMoviePic(Integer.parseInt
                            (cursor.getString(cursor.getColumnIndex(DBHelper.COT_MOVIE_IMAGE))));

                    addMovies.setAddMovieType(cursor.getString
                            (cursor.getColumnIndex(DBHelper.COT_TYPE_MOVIE)));

                    addMovies.setAddMovieAge(cursor.getString
                            (cursor.getColumnIndex(DBHelper.COT_AGE)));

                    addMovies.setAddMovieLength(cursor.getString
                            (cursor.getColumnIndex(DBHelper.COT_MOVIELENGTH)));

                    addMovies.setAddMovieReleasedDate(cursor.getString
                            (cursor.getColumnIndex(DBHelper.COT_RELEASED_DATE)));

                    addMovies.setAddMovieDirector(cursor.getString
                            (cursor.getColumnIndex(DBHelper.COT_DIRECTOR)));

                    addMovies.setAddMoiveActors(cursor.getString
                            (cursor.getColumnIndex(DBHelper.COT_ACTORS)));

                    addMovies.setAddMovieLanguage(cursor.getString
                            (cursor.getColumnIndex(DBHelper.COT_LANGUAGE)));

                    addMovies.setAddMovieDesc(cursor.getString
                            (cursor.getColumnIndex(DBHelper.COT_DESCRIPTION)));

                    addMovies.setAddMoiveBanner(cursor.getInt(cursor.getColumnIndex(DBHelper.COT_BANNER)));


                    mtxtName.setText(addMovies.getAddMovieName());

                    mtvTypeMovieEditDetails.setText(addMovies.getAddMovieType());

                    mtxtAge.setText(addMovies.getAddMovieAge());

                    mtxtLength.setText(addMovies.getAddMovieLength());

                    mtxtNgayChieu.setText(addMovies.getAddMovieReleasedDate());

                    mtxtDaoDien.setText(addMovies.getAddMovieDirector());

                    mtxtDienVien.setText(addMovies.getAddMoiveActors());

                    mtxtNoiDung.setText(addMovies.getAddMovieDesc());

                    mtxtNgonNgu.setText(addMovies.getAddMovieLanguage());

                    mimgPoster.setImageResource(addMovies.getAddMoviePic());

                    mimgBanner.setImageResource(addMovies.getAddMoiveBanner());

                    xoa.setAddMovieID(addMovies.getAddMovieID());
                    
                    break;
                }
            } while (cursor.moveToNext());
        }

        cursor.close();
    }

        public void btnEditMvDetails_onClick(View view) {
        AddMovies addMovies = layDuLieuNguoiDung();
        String nameMovie = mtxtName.getText().toString();
        boolean KiemTraEditPhim = addPhimDatabase.KiemTraEditPhim(nameMovie);
        if (KiemTraEditPhim){
            Toast.makeText(AdminAddDetailsMoviesActivity.this,"Bộ phim này đã tồn tại",Toast.LENGTH_SHORT).show();
        } else {
            if(addMovies != null){
                if(addPhimDatabase.EditPhim(addMovies) != -1){
                    Toast.makeText(AdminAddDetailsMoviesActivity.this,"Chỉnh sửa thông tin thành công",Toast.LENGTH_SHORT).show();
                    finish();
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