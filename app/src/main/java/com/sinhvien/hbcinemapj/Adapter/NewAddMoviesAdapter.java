package com.sinhvien.hbcinemapj.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.sinhvien.hbcinemapj.Activity.AdminAddDetailsMoviesActivity;
import com.sinhvien.hbcinemapj.Class.AddMovies;
import com.sinhvien.hbcinemapj.Database.AddPhimDatabase;
import com.sinhvien.hbcinemapj.R;

import java.util.ArrayList;

public class NewAddMoviesAdapter extends RecyclerView.Adapter<NewAddMoviesAdapter.ViewHolder> {


    Context context;
    int layout;
    ArrayList<AddMovies> arrayList;

    AddPhimDatabase database;

    public NewAddMoviesAdapter(Context context, int layout, ArrayList<AddMovies> arrayList) {
        this.context = context;
        this.layout = layout;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public NewAddMoviesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(context).inflate(layout, parent, false);

        database = new AddPhimDatabase(v.getContext());

        return new ViewHolder(v);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AddMovies addMovies = arrayList.get(position);
        holder.txtAddName.setText(String.valueOf(addMovies.getAddMovieName()));
        holder.txtAddType.setText(String.valueOf(addMovies.getAddMovieType()));
        holder.txtAddFormat.setText(String.valueOf(addMovies.getAddMovieAge()));
        holder.txtAddLength.setText(String.valueOf(addMovies.getAddMovieLength()));
        holder.txtAddReleasedDate.setText(String.valueOf(addMovies.getAddMovieReleasedDate()));
        holder.txtDirector.setText(String.valueOf(addMovies.getAddMovieDirector()));
        holder.txtActors.setText(String.valueOf(addMovies.getAddMoiveActors()));
        holder.txtLanguage.setText(String.valueOf(addMovies.getAddMovieLanguage()));
        holder.txtDesc.setText(String.valueOf(addMovies.getAddMovieDesc()));
        holder.AddMovieImage.setImageResource(addMovies.getAddMoviePic());

            switch (addMovies.getAddMovieAge()) {
                case "P":
                    holder.txtAddFormat.setBackgroundDrawable(ContextCompat.getDrawable(context,R.drawable.custom_p));
                    holder.txtAddFormat.setTextColor(ContextCompat.getColor(context,R.color.green));
                    break;
                case "C13":
                    holder.txtAddFormat.setBackgroundDrawable(ContextCompat.getDrawable(context,R.drawable.custom_c13));
                    holder.txtAddFormat.setTextColor(ContextCompat.getColor(context,R.color.yellow));
                    break;
                case "C16":
                    holder.txtAddFormat.setBackgroundDrawable(ContextCompat.getDrawable(context,R.drawable.custom_c16));
                    holder.txtAddFormat.setTextColor(ContextCompat.getColor(context,R.color.orange));
                    break;
                case "C18":
                    holder.txtAddFormat.setBackgroundDrawable(ContextCompat.getDrawable(context,R.drawable.custom_c18));
                    holder.txtAddFormat.setTextColor(ContextCompat.getColor(context,R.color.red));
                    break;
                default:
                    holder.txtAddFormat.setTextColor(R.color.black);
            }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, AdminAddDetailsMoviesActivity.class);
                Bundle b = new Bundle();
                b.putLong("ID", addMovies.getAddMovieID());
                i.putExtras(b);
                context.startActivity(i);
            }
        });

    }


    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txtAddName, txtAddType, txtAddFormat, txtAddLength, txtAddReleasedDate, txtDirector, txtActors, txtLanguage, txtDesc;

        private CardView AddLayoutPhimItem;
        public ImageView AddMovieImage;

        public ViewHolder(@NonNull View v) {
            super(v);
            AddLayoutPhimItem = v.findViewById(R.id.AddlayoutPhim_item);
            txtAddName = v.findViewById(R.id.txtAddMovieName);
            txtAddType = v.findViewById(R.id.txtAddMovieType);
            txtAddFormat = v.findViewById(R.id.txtAddMovieFormat);
            txtAddLength = v.findViewById(R.id.txtAddMovieLength);
            txtAddReleasedDate = v.findViewById(R.id.txtAddReleaseDate);
            txtDirector = v.findViewById(R.id.txtAddDirector);
            txtActors = v.findViewById(R.id.txtAddActors);
            txtLanguage = v.findViewById(R.id.txtAddLanguage);
            txtDesc = v.findViewById(R.id.txtAddMovieDesrciption);
            AddMovieImage = v.findViewById(R.id.AddMovieImage);
        }
    }
}
