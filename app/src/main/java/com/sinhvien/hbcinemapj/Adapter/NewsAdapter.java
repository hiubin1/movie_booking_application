package com.sinhvien.hbcinemapj.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.sinhvien.hbcinemapj.Activity.ThongTinChiTietTinTucActivity;
import com.sinhvien.hbcinemapj.Class.Movies;
import com.sinhvien.hbcinemapj.Class.News;
import com.sinhvien.hbcinemapj.R;

import java.text.Normalizer;
import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> implements Filterable {

    Context context;
    int layoutNews;
    ArrayList<News> arrayList;

    ArrayList<News> arrayListOldNews;

    public NewsAdapter(Context context, int layoutNews, ArrayList<News> arrayList) {
        this.context = context;
        this.layoutNews = layoutNews;
        this.arrayList = arrayList;
        this.arrayListOldNews = arrayList;
    }

    @NonNull
    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(layoutNews, parent, false);
        return new NewsAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int posotion) {
        News news = arrayList.get(posotion);
        if (news == null){
            return;
        }
        viewHolder.mtvNewsName.setText(String.valueOf(news.getNewsName()));
        viewHolder.mtvNewsDate.setText(String.valueOf(news.getNewsDate()));
        viewHolder.mimgNewsImage.setImageResource(news.getNewsImage());

        viewHolder.mimgNewsImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickToNewsDetail(news);
            }
        });
        viewHolder.mlayoutNewsItem.setOnClickListener(new View.OnClickListener() {
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
        return arrayList.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String strSearch = charSequence.toString();

                if(strSearch.isEmpty()){
                    arrayList = arrayListOldNews;
                } else {
                    ArrayList<News> ArrayList = new ArrayList<>();
                    String normalizedSearchString = Normalizer.normalize(strSearch, Normalizer.Form.NFD)
                            .replaceAll("\\p{InCombiningDiacriticalMarks}+", "")
                            .toLowerCase();
                    for(News news : arrayListOldNews){
                        String normalizedNewsName = Normalizer.normalize(news.getNewsName(), Normalizer.Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "").toLowerCase();
                        String normalizedNewsDate = Normalizer.normalize(news.getNewsName(), Normalizer.Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "").toLowerCase();
                        if(normalizedNewsName.contains(normalizedSearchString) || normalizedNewsDate.contains(normalizedSearchString)){
                            ArrayList.add(news);
                        }
                    }
                    arrayList = ArrayList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = arrayList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults results) {
                arrayList = (ArrayList<News>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public  static class ViewHolder extends RecyclerView.ViewHolder{

        public FrameLayout mlayoutNewsItem;
        public TextView mtvNewsName, mtvNewsDate;
        public ImageView mimgNewsImage;

        public ViewHolder(@NonNull View v) {
            super(v);
            mlayoutNewsItem = v.findViewById(R.id.layout_newsItem);
            mtvNewsName = v.findViewById(R.id.tvNewsName);
            mtvNewsDate = v.findViewById(R.id.tvNewsDate);
            mimgNewsImage = v.findViewById(R.id.imgNews);
        }
    }
}
