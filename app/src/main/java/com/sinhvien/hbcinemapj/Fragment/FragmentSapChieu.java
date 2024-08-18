package com.sinhvien.hbcinemapj.Fragment;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sinhvien.hbcinemapj.Adapter.MovieHomeSCAdapter;
import com.sinhvien.hbcinemapj.Class.Movies;
import com.sinhvien.hbcinemapj.Database.AddPhimDatabase;
import com.sinhvien.hbcinemapj.Database.DBHelper;
import com.sinhvien.hbcinemapj.R;

import java.util.ArrayList;

public class FragmentSapChieu  extends Fragment {

    private RecyclerView mRecyclerView;

    private RecyclerView.LayoutManager mLayoutManager;

    ArrayList<Movies> moviesArrayList;

    MovieHomeSCAdapter movieHomeSCAdapter;

    AddPhimDatabase addPhimDatabase;

    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_sapchieu,container,false);
        mRecyclerView = view.findViewById(R.id.RVMovieSCHome);
        addPhimDatabase = new AddPhimDatabase(getContext());


        capNhatDuLieu();

        return view;
    }

    public void SetRV(){
        if (moviesArrayList != null){
            mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
            mRecyclerView.setLayoutManager(mLayoutManager);
            movieHomeSCAdapter = new MovieHomeSCAdapter(view.getContext(), R.layout.list_phim_sap_chieu,moviesArrayList);
            mRecyclerView.setAdapter(movieHomeSCAdapter);
        }
    }

    @SuppressLint("Range")
    public void capNhatDuLieu(){
        if (moviesArrayList == null){
            moviesArrayList = new ArrayList<>();
        } else {
            moviesArrayList.removeAll(moviesArrayList);
        }
        Cursor cursor = addPhimDatabase.GetDataFormDateAfter();
        if(cursor != null){
            while (cursor.moveToNext()){
                Movies movies = new Movies();
                movies.setId(cursor.getInt(cursor.getColumnIndex(DBHelper.COT_ID)));
                movies.setPic(cursor.getInt(cursor.getColumnIndex(DBHelper.COT_MOVIE_IMAGE)));
                movies.setMovieName(cursor.getString(cursor.getColumnIndex(DBHelper.COT_TEN_PHIM)));
                movies.setAge(cursor.getString(cursor.getColumnIndex(DBHelper.COT_AGE)));
                movies.setTime(cursor.getString(cursor.getColumnIndex(DBHelper.COT_MOVIELENGTH)));
                movies.setDate(cursor.getString(cursor.getColumnIndex(DBHelper.COT_RELEASED_DATE)));
                movies.setDesc(cursor.getString(cursor.getColumnIndex(DBHelper.COT_DESCRIPTION)));
                movies.setType(cursor.getString(cursor.getColumnIndex(DBHelper.COT_TYPE_MOVIE)));
                movies.setDirector(cursor.getString(cursor.getColumnIndex(DBHelper.COT_DIRECTOR)));
                movies.setActors(cursor.getString(cursor.getColumnIndex(DBHelper.COT_ACTORS)));
                movies.setLanguge(cursor.getString(cursor.getColumnIndex(DBHelper.COT_LANGUAGE)));
                movies.setBanner(cursor.getInt(cursor.getColumnIndex(DBHelper.COT_BANNER)));

                moviesArrayList.add(movies);
            }
        }
        SetRV();
    }
}
