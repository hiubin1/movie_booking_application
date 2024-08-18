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
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.sinhvien.hbcinemapj.Activity.ThongTinChiTietPhimActivity;
import com.sinhvien.hbcinemapj.Class.Movies;
import com.sinhvien.hbcinemapj.R;

import java.util.ArrayList;

public class MovieHomeSCAdapter extends RecyclerView.Adapter<MovieHomeSCAdapter.MovieCSHomeViewHolder> {

    private ArrayList<Movies> mMovies;

    private Context context;

    int layout;

    public MovieHomeSCAdapter(Context context, int layout, ArrayList<Movies> mMovies) {
        this.mMovies = mMovies;
        this.context = context;
        this.layout = layout;
    }

    @NonNull
    @Override
    public MovieCSHomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v= LayoutInflater.from(parent.getContext()).inflate(layout,parent,false);
        return new MovieCSHomeViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieCSHomeViewHolder holder, int position) {
        Movies movies = mMovies.get(position);
        if(movies == null) {
            return;
        }

        holder.mImgSCMoive.setImageResource(movies.getPic());
        holder.mtxtNameSCMovie.setText(String.valueOf(movies.getMovieName()));
        holder.mtxtFormatSCMovie.setText(String.valueOf(movies.getAge()));
        holder.mtxtTimeSCMovie.setText(String.valueOf(movies.getTime()));
        holder.mtxtDateSCMovie.setText(String.valueOf(movies.getDate()));

        switch (movies.getAge()) {
            case "P":
                holder.mtxtFormatSCMovie.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.custom_p));
                holder.mtxtFormatSCMovie.setTextColor(ContextCompat.getColor(context, R.color.green));
                break;
            case "C13":
                holder.mtxtFormatSCMovie.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.custom_c13));
                holder.mtxtFormatSCMovie.setTextColor(ContextCompat.getColor(context, R.color.yellow));
                break;
            case "C16":
                holder.mtxtFormatSCMovie.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.custom_c16));
                holder.mtxtFormatSCMovie.setTextColor(ContextCompat.getColor(context, R.color.orange));
                break;
            case "C18":
                holder.mtxtFormatSCMovie.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.custom_c18));
                holder.mtxtFormatSCMovie.setTextColor(ContextCompat.getColor(context, R.color.red));
                break;
            default:
                holder.mtxtFormatSCMovie.setTextColor(ContextCompat.getColor(context, R.color.black));

        }
        holder.layoutitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickGoToMVDetail(movies);
            }
        });

        holder.mImgSCMoive.setOnClickListener(new View.OnClickListener() {
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
        if(mMovies != null) {
            return mMovies.size();
        }
        return 0;
    }

    public class MovieCSHomeViewHolder extends RecyclerView.ViewHolder {

        private ImageView mImgSCMoive;
        private TextView mtxtNameSCMovie, mtxtFormatSCMovie, mtxtTimeSCMovie, mtxtDateSCMovie;

        private LinearLayout layoutitem;
        public MovieCSHomeViewHolder(@NonNull View v) {
            super(v);
            layoutitem = (LinearLayout) v.findViewById(R.id.layout_ListSapChieu);
            mImgSCMoive = (ImageView) v.findViewById(R.id.imgMoviesSCHome);
            mtxtNameSCMovie = (TextView) v.findViewById(R.id.txtMovieNameSC);
            mtxtFormatSCMovie = (TextView) v.findViewById(R.id.txtMovieFormatSC);
            mtxtTimeSCMovie = (TextView) v.findViewById(R.id.txtMovieTimeSC);
            mtxtDateSCMovie = (TextView) v.findViewById(R.id.txtMovieDateSC);
        }
    }
}
