package com.sinhvien.hbcinemapj.Adapter;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.sinhvien.hbcinemapj.Activity.ThongTinChiTietPhimActivity;
import com.sinhvien.hbcinemapj.Class.Movies;
import com.sinhvien.hbcinemapj.Database.AddPhimDatabase;
import com.sinhvien.hbcinemapj.R;

import java.util.ArrayList;

public class MoviesHomeAdapter extends RecyclerView.Adapter<MoviesHomeAdapter.MoviesHomeViewHolder> {

    private ArrayList<Movies> mMovies;

    private Context context;

    int layout;



    public MoviesHomeAdapter(Context context, int layout, ArrayList<Movies> mMovies) {
        this.context = context;
        this.layout = layout;
        this.mMovies = mMovies;
    }

    @NonNull
    @Override
    public MoviesHomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(layout,parent,false);
        return new MoviesHomeViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesHomeViewHolder holder, int position) {
        Movies movies = mMovies.get(position);
        if(movies == null){
            return;
        }
        holder.mImgMovieHome.setImageResource(movies.getPic());
        holder.mtvNameMovieHome.setText(String.valueOf(movies.getMovieName()));
        holder.mtvFormatMovieHome.setText(String.valueOf(movies.getAge()));
        holder.mtvTimeMovieHome.setText(String.valueOf(movies.getTime()));
        holder.mtvDateMovieHome.setText(String.valueOf(movies.getDate()));

        switch (movies.getAge()){
            case "P":
                holder.mtvFormatMovieHome.setBackgroundDrawable(ContextCompat.getDrawable(context,R.drawable.custom_p));
                holder.mtvFormatMovieHome.setTextColor(ContextCompat.getColor(context,R.color.green));
                break;
            case "C13":
                holder.mtvFormatMovieHome.setBackgroundDrawable(ContextCompat.getDrawable(context,R.drawable.custom_c13));
                holder.mtvFormatMovieHome.setTextColor(ContextCompat.getColor(context,R.color.yellow));
                break;
            case "C16":
                holder.mtvFormatMovieHome.setBackgroundDrawable(ContextCompat.getDrawable(context,R.drawable.custom_c16));
                holder.mtvFormatMovieHome.setTextColor(ContextCompat.getColor(context,R.color.orange));
                break;
            case "C18":
                holder.mtvFormatMovieHome.setBackgroundDrawable(ContextCompat.getDrawable(context,R.drawable.custom_c18));
                holder.mtvFormatMovieHome.setTextColor(ContextCompat.getColor(context,R.color.red));
                break;
            default:
                holder.mtvFormatMovieHome.setTextColor(ContextCompat.getColor(context,R.color.black));
        }

        holder.layoutitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickGoToMVDetail(movies);
            }
        });
        holder.mImgMovieHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickGoToMVDetail(movies);
            }
        });
    }

    private void onClickGoToMVDetail(Movies movies){
        Intent i = new Intent(context, ThongTinChiTietPhimActivity.class);
        Bundle b = new Bundle();
        b.putSerializable("object_movies",movies);
        i.putExtras(b);
        context.startActivity(i);
    }

    @Override
    public int getItemCount() {
        if (mMovies != null){
            return  mMovies.size();
        }
        return 0;
    }

    public class MoviesHomeViewHolder extends RecyclerView.ViewHolder{

        private ImageView mImgMovieHome;
        private TextView mtvNameMovieHome,mtvFormatMovieHome,mtvTimeMovieHome,mtvDateMovieHome;

        private LinearLayout layoutitem;

        public MoviesHomeViewHolder(@NonNull View v) {
            super(v);
            layoutitem = v.findViewById(R.id.layout_MoviesHome);
            mImgMovieHome = (ImageView) v.findViewById(R.id.ImgMovieHome);
            mtvNameMovieHome = (TextView) v.findViewById(R.id.tvNameMovieHome);
            mtvFormatMovieHome = (TextView) v.findViewById(R.id.tvFormatMovieHome);
            mtvTimeMovieHome = (TextView) v.findViewById(R.id.tvTimeMovieHome);
            mtvDateMovieHome = (TextView) v.findViewById(R.id.tvDateMovieHome);
        }
    }
}
