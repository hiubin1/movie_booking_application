package com.sinhvien.hbcinemapj.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sinhvien.hbcinemapj.Activity.ThongTinChiTietTinTucActivity;
import com.sinhvien.hbcinemapj.Class.News;
import com.sinhvien.hbcinemapj.R;

import java.util.ArrayList;

public class NewsHomeAdapter extends RecyclerView.Adapter<NewsHomeAdapter.NewsHomeViewHolder> {

    private ArrayList<News> mNews;

    private Context context;

    int layout;

    public NewsHomeAdapter(Context context, int layout, ArrayList<News> mNews) {
        this.context = context;
        this.layout = layout;
        this.mNews = mNews;
    }


    @NonNull
    @Override
    public NewsHomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(layout,parent,false);
        return new NewsHomeViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsHomeViewHolder holder, int position) {
        News news = mNews.get(position);
        if(mNews == null){
            return;
        }
        holder.mImgNewsHome.setImageResource(news.getNewsImage());
        holder.mNewsNameHome.setText(String.valueOf(news.getNewsName()));

        holder.layoutitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickToNewsDetail(news);
            }
        });
        holder.mImgNewsHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickToNewsDetail(news);
            }
        });
    }



    private void clickToNewsDetail(News news) {
        Intent intentNews = new Intent(context, ThongTinChiTietTinTucActivity.class);
        Bundle bundleNews = new Bundle();
        bundleNews.putSerializable("object_news", news);
        intentNews.putExtras(bundleNews);
        context.startActivity(intentNews);
    }

    @Override
    public int getItemCount() {
        if (mNews != null){
            return mNews.size();
        }
        return 0;
    }

    public class NewsHomeViewHolder extends RecyclerView.ViewHolder{
        private ImageView mImgNewsHome;
        private TextView mNewsNameHome;

        private LinearLayout layoutitem;

        public NewsHomeViewHolder(@NonNull View v) {
            super(v);
            mImgNewsHome = (ImageView) v.findViewById(R.id.imgNewsHome);
            mNewsNameHome = (TextView) v.findViewById(R.id.tvNewsNameHome);
            layoutitem = (LinearLayout) v.findViewById(R.id.layout_itemNewsHome);
        }
    }
}
