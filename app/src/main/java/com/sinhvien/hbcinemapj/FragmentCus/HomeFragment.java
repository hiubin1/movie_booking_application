package com.sinhvien.hbcinemapj.FragmentCus;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.sinhvien.hbcinemapj.Adapter.DCSCAdapter;
import com.sinhvien.hbcinemapj.Adapter.NewsHomeAdapter;
import com.sinhvien.hbcinemapj.Class.News;
import com.sinhvien.hbcinemapj.Database.AddNewsDatabase;
import com.sinhvien.hbcinemapj.Database.DBHelper;
import com.sinhvien.hbcinemapj.R;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private RecyclerView mRecyclerView;

    private RecyclerView.LayoutManager layoutManager;

    ArrayList<News> mNewsList;

    NewsHomeAdapter newsHomeAdapter;

    TabLayout mTabLayout;
    ViewPager mViewPager;

    AddNewsDatabase addNewsDatabase;

    View rootView;

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_home, container, false);
        mRecyclerView = rootView.findViewById(R.id.RVNewsHome);
        addNewsDatabase = new AddNewsDatabase(getContext());


        // Đang chiếu vs sắp chiếu
        mTabLayout = (TabLayout) rootView.findViewById(R.id.tab_layout_fim);
        mViewPager = (ViewPager) rootView.findViewById(R.id.view_pager_fim);
        mTabLayout.addTab(mTabLayout.newTab().setText("Đang Chiếu"));
        mTabLayout.addTab(mTabLayout.newTab().setText("Sắp Chiếu"));
        mTabLayout.setTabGravity(mTabLayout.GRAVITY_FILL);


        final DCSCAdapter adapter = new DCSCAdapter(rootView.getContext(), getFragmentManager(), mTabLayout.getTabCount());
        mViewPager.setAdapter(adapter);
        setTabDividers();
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        capNhatDuLieu();
        return rootView;
    }

    public void SetRV(){
        if(mNewsList != null){
            layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
            mRecyclerView.setLayoutManager(layoutManager);
            newsHomeAdapter = new NewsHomeAdapter(rootView.getContext(),R.layout.list_news_home,mNewsList);
            mRecyclerView.setAdapter(newsHomeAdapter);
        }
    }

    @SuppressLint("Range")
    public void capNhatDuLieu(){
        if (mNewsList == null) {
            mNewsList = new ArrayList<>();
        } else {
            mNewsList.removeAll(mNewsList);
        }
        Cursor cursor = addNewsDatabase.layTatCaDuLieu();
        if(cursor != null){
            while (cursor.moveToNext()){
                News news = new News();

                news.setId(cursor.getInt(cursor.getColumnIndex(DBHelper.COT_NEWS_ID)));
                news.setNewsName(cursor.getString(cursor.getColumnIndex(DBHelper.COT_NEWS_NAME)));
                news.setNewsDate(cursor.getString(cursor.getColumnIndex(DBHelper.COT_NEWS_DATE)));
                news.setNewsContent(cursor.getString(cursor.getColumnIndex(DBHelper.COT_NEWS_DESCRIPTION)));
                news.setNewsImage(cursor.getInt(cursor.getColumnIndex(DBHelper.COT_NEWS_BANNER)));
                news.setNewsRapApDung(cursor.getString(cursor.getColumnIndex(DBHelper.COT_NEWS_RAP)));

                mNewsList.add(news);
            }
        }
        SetRV();
    }

    private void setTabDividers(){
        View root = mTabLayout.getChildAt(0);
        if(root instanceof LinearLayout){
            ((LinearLayout) root).setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
            GradientDrawable drawable = new GradientDrawable();
            drawable.setColor(Color.RED);
            drawable.setSize(2,1);
            ((LinearLayout) root).setDividerPadding(10);
            ((LinearLayout) root).setDividerDrawable(drawable);
        }
    }
}
