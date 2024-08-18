package com.sinhvien.hbcinemapj.FragmentCus;

import android.annotation.SuppressLint;
import android.app.SearchManager;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sinhvien.hbcinemapj.Adapter.MoviesAdapter;
import com.sinhvien.hbcinemapj.Adapter.NewsAdapter;
import com.sinhvien.hbcinemapj.Class.News;
import com.sinhvien.hbcinemapj.Class.NewsSession;
import com.sinhvien.hbcinemapj.Database.AddNewsDatabase;
import com.sinhvien.hbcinemapj.Database.DBHelper;
import com.sinhvien.hbcinemapj.R;

import java.util.ArrayList;

public class NewsFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;

    private SearchView searchView;

    AddNewsDatabase addNewsDatabase;

    private ArrayList<News> newsList;
    private NewsAdapter newsAdapter;

    View rootView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_news,container,false);
        addNewsDatabase = new AddNewsDatabase(getContext());

        searchView = rootView.findViewById(R.id.search_view_news);
        searchView.clearFocus();
        mRecyclerView = rootView.findViewById(R.id.recyclerViewNews);

        capNhatDuLieu();


        return rootView;
    }

    @SuppressLint("Range")
    public void capNhatDuLieu(){
        if (newsList == null){
            newsList = new ArrayList<>();
        } else {
            newsList.removeAll(newsList);
        }
        Cursor cursor = addNewsDatabase.layTatCaDuLieu();
        if(cursor != null){
            while (cursor.moveToNext()){
                News news = new News();

                news.setId(cursor.getInt(cursor.getColumnIndex(DBHelper.COT_NEWS_ID)));
                news.setNewsName(cursor.getString(cursor.getColumnIndex(DBHelper.COT_NEWS_NAME)));
                news.setNewsDate(cursor.getString(cursor.getColumnIndex(DBHelper.COT_NEWS_DATE)));
                news.setNewsContent(cursor.getString(cursor.getColumnIndex(DBHelper.COT_NEWS_DESCRIPTION)));
                news.setNewsRapApDung(cursor.getString(cursor.getColumnIndex(DBHelper.COT_NEWS_RAP)));
                news.setNewsImage(cursor.getInt(cursor.getColumnIndex(DBHelper.COT_NEWS_BANNER)));
                NewsSession.newsName = news.getNewsName();
                NewsSession.pic = news.getNewsImage();
                newsList.add(news);
            }
        }
        SetRV();
    }

    public void SetRV(){
        if (newsList != null){
            mLayoutManager = new GridLayoutManager(rootView.getContext(), 1);
            mRecyclerView.setLayoutManager(mLayoutManager);
            newsAdapter = new NewsAdapter(rootView.getContext(), R.layout.news_list, newsList);
            mRecyclerView.setAdapter(newsAdapter);
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
                newsAdapter.getFilter().filter(s);
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
