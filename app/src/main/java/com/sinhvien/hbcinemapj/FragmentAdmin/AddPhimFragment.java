package com.sinhvien.hbcinemapj.FragmentAdmin;

import static java.security.AccessController.getContext;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sinhvien.hbcinemapj.Activity.AddDBMoviesPageActivity;
import com.sinhvien.hbcinemapj.Adapter.NewAddMoviesAdapter;
import com.sinhvien.hbcinemapj.Class.AddMovies;
import com.sinhvien.hbcinemapj.Database.AddPhimDatabase;
import com.sinhvien.hbcinemapj.Database.DBHelper;
import com.sinhvien.hbcinemapj.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddPhimFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddPhimFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddPhimFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddPhimFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddPhimFragment newInstance(String param1, String param2) {
        AddPhimFragment fragment = new AddPhimFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    public static RecyclerView mRecyclerView;
    public static RecyclerView.LayoutManager mLayoutManager;
    public static ArrayList<AddMovies> addMovieList;
    AddPhimDatabase database;
    View rootView;
    private NewAddMoviesAdapter adapter;
    FloatingActionButton mFBtnAdd;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_add_phim, container, false);

        database = new AddPhimDatabase(rootView.getContext());
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.AddPhimRV);



        mFBtnAdd = (FloatingActionButton) rootView.findViewById(R.id.FBAdd);

        mFBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Add = new Intent(getContext(), AddDBMoviesPageActivity.class);
                startActivity(Add);
            }
        });

        capNhatDuLieu();

        return  rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        capNhatDuLieu();
    }

    @SuppressLint("Range")
    public void capNhatDuLieu() {
        if(addMovieList == null) {
            addMovieList = new ArrayList<AddMovies>();
        } else {
            addMovieList.removeAll(addMovieList);
        }

        Cursor cursor = database.layTatCaDuLieu();
        if(cursor != null) {
            while (cursor.moveToNext()) {
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

                addMovieList.add(addMovies);

            }
        }
        setRecyclerView();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setRecyclerView() {
        if(addMovieList != null) {
            mLayoutManager = new LinearLayoutManager(rootView.getContext(), LinearLayoutManager.VERTICAL, false);
            mRecyclerView.setLayoutManager(mLayoutManager);
            adapter = new NewAddMoviesAdapter(rootView.getContext(),R.layout.list_add_phim,addMovieList);
            mRecyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
    }
}