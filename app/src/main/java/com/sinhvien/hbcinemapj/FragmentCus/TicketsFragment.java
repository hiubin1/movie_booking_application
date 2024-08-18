package com.sinhvien.hbcinemapj.FragmentCus;

import android.annotation.SuppressLint;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;

import com.sinhvien.hbcinemapj.Activity.CustomerActivity;
import com.sinhvien.hbcinemapj.Adapter.MoviesAdapter;
import com.sinhvien.hbcinemapj.Class.Movies;
import com.sinhvien.hbcinemapj.Class.MoviesSession;
import com.sinhvien.hbcinemapj.Database.AddPhimDatabase;
import com.sinhvien.hbcinemapj.Database.DBHelper;
import com.sinhvien.hbcinemapj.R;

import java.io.Serializable;
import java.util.ArrayList;

public class TicketsFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;

    private SearchView searchView;
    private ArrayList<Movies> moviesList;
    private MoviesAdapter moviesAdapter;

    AddPhimDatabase addPhimDatabase;

    View rootView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_tickets,container,false);
        addPhimDatabase = new AddPhimDatabase(getContext());

        searchView = rootView.findViewById(R.id.search_view);
        searchView.clearFocus();
        mRecyclerView = rootView.findViewById(R.id.recyclerView);

        capNhatDuLieu();


        return rootView;
    }

    @SuppressLint("Range")
    public void capNhatDuLieu(){
        if (moviesList == null){
            moviesList = new ArrayList<>();
        } else {
            moviesList.removeAll(moviesList);
        }
        Cursor cursor = addPhimDatabase.layTatCaDuLieu();
        if (cursor != null){
            while (cursor.moveToNext()){
                // sửa lại thứ tự
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
                MoviesSession.movieName = movies.getMovieName();
                MoviesSession.pic = movies.getPic();
                moviesList.add(movies);
            }
        }
        SetRV();
    }

    public void SetRV(){
        if (moviesList != null){
            mLayoutManager = new GridLayoutManager(rootView.getContext(), 2);
            mRecyclerView.setLayoutManager(mLayoutManager);
            moviesAdapter = new MoviesAdapter(rootView.getContext(), R.layout.movies_list, moviesList);
            mRecyclerView.setAdapter(moviesAdapter);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {



        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                moviesAdapter.getFilter().filter(s);
                return true;
            }

        });
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }
}
