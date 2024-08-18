package com.sinhvien.hbcinemapj.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.sinhvien.hbcinemapj.Activity.AdminAddDetailsNewsActivity;
import com.sinhvien.hbcinemapj.Class.AddNews;
import com.sinhvien.hbcinemapj.Database.AddNewsDatabase;
import com.sinhvien.hbcinemapj.R;

import java.util.ArrayList;

public class AddNewsAdapter extends RecyclerView.Adapter<AddNewsAdapter.ViewHolder>{

    Context context;
    ArrayList<AddNews> arrayList;
    int layout;

    AddNewsDatabase addNewsDatabase;

    public AddNewsAdapter(Context context, int layout, ArrayList<AddNews> arrayList) {
        this.context = context;
        this.layout = layout;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public AddNewsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(context).inflate(layout, parent, false);

        addNewsDatabase = new AddNewsDatabase(v.getContext());
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AddNews addNews = arrayList.get(position);
        holder.tvNewsName.setText(addNews.getAddNewsName());
        holder.tvNewsDate.setText(addNews.getAddNewsDate());
        holder.tvNewsContent.setText(addNews.getAddNewsContent());
        holder.tvRapApDung.setText(addNews.getAddNewsRapApDung());
        holder.AddNewsImage.setImageResource(addNews.getAddNewsPic());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, AdminAddDetailsNewsActivity.class);
                Bundle b = new Bundle();
                b.putLong("ID",addNews.getAddNewsID());
                i.putExtras(b);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
            private ImageView AddNewsImage;
            private TextView tvNewsName,tvNewsDate,tvNewsContent,tvRapApDung;
            private CardView AddLayoutNewsItem;
        public ViewHolder(@NonNull View v) {
            super(v);
            AddNewsImage =  v.findViewById(R.id.AddNewsImage);
            tvNewsName =  v.findViewById(R.id.tvNewsName);
            tvNewsDate =  v.findViewById(R.id.tvNewsDate);
            tvNewsContent =  v.findViewById(R.id.tvNewsContent);
            tvRapApDung =  v.findViewById(R.id.tvRapApDung);
            AddLayoutNewsItem = v.findViewById(R.id.AddlayoutNews_item);
        }
    }
}
