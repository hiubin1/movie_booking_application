package com.sinhvien.hbcinemapj.FragmentAdmin;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sinhvien.hbcinemapj.Activity.AddDBNewsPageActivity;
import com.sinhvien.hbcinemapj.Adapter.AddNewsAdapter;
import com.sinhvien.hbcinemapj.Adapter.NewAddMoviesAdapter;
import com.sinhvien.hbcinemapj.Class.AddNews;
import com.sinhvien.hbcinemapj.Database.AddNewsDatabase;
import com.sinhvien.hbcinemapj.Database.DBHelper;
import com.sinhvien.hbcinemapj.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddNewsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddNewsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddNewsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddNewsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddNewsFragment newInstance(String param1, String param2) {
        AddNewsFragment fragment = new AddNewsFragment();
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

    public static RecyclerView mrvAddNews;

    public static RecyclerView.LayoutManager mLayoutManager;
    public static ArrayList<AddNews> addNewsList;

    AddNewsDatabase database;

    View view;

    private AddNewsAdapter adapter;

    FloatingActionButton mbtnAddNews;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_add_news, container, false);

        database = new AddNewsDatabase(view.getContext());
        mrvAddNews = (RecyclerView) view.findViewById(R.id.rvAddNews);

        mbtnAddNews = (FloatingActionButton) view.findViewById(R.id.FloatButtonAddNews);
        mbtnAddNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addNews = new Intent(view.getContext(), AddDBNewsPageActivity.class);
                startActivity(addNews);
            }
        });
        capNhatDuLieu();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        capNhatDuLieu();
    }

    @SuppressLint("Range")
    public void capNhatDuLieu(){
        if(addNewsList == null){
            addNewsList = new ArrayList<AddNews>();
        } else {
            addNewsList.removeAll(addNewsList);
        }

        Cursor cursor = database.layTatCaDuLieu();
        if(cursor != null){
            while (cursor.moveToNext()){
                AddNews addNews = new AddNews();
                addNews.setAddNewsID(Integer.parseInt
                        (cursor.getString(cursor.getColumnIndex(DBHelper.COT_NEWS_ID))));

                addNews.setAddNewsName(cursor.getString
                        (cursor.getColumnIndex(DBHelper.COT_NEWS_NAME)));

                addNews.setAddNewsPic(Integer.parseInt(cursor.getString
                        (cursor.getColumnIndex(DBHelper.COT_NEWS_BANNER))));

                addNews.setAddNewsDate(cursor.getString
                        (cursor.getColumnIndex(DBHelper.COT_NEWS_DATE)));

                addNews.setAddNewsContent(cursor.getString
                        (cursor.getColumnIndex(DBHelper.COT_NEWS_DESCRIPTION)));

                addNews.setAddNewsRapApDung(cursor.getString
                        (cursor.getColumnIndex(DBHelper.COT_NEWS_RAP)));

                addNewsList.add(addNews);
            }
        }
        SetRV();
    }
    public void SetRV(){
        if (addNewsList != null){
            mLayoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL,false);
            mrvAddNews.setLayoutManager(mLayoutManager);
            adapter = new AddNewsAdapter(view.getContext(),R.layout.list_add_news,addNewsList);
            mrvAddNews.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
    }

}