package com.sinhvien.hbcinemapj.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.sinhvien.hbcinemapj.Activity.ThongTinChiTietPhimActivity;
import com.sinhvien.hbcinemapj.Class.Movies;
import com.sinhvien.hbcinemapj.R;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Locale;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> implements Filterable{
    Context context;
    int layout;
    ArrayList<Movies> arrayList;
    ArrayList<Movies> arrayListOld;


    public MoviesAdapter(Context context, int layout, ArrayList<Movies> arrayList) {
        this.context = context;
        this.layout = layout;
        this.arrayList = arrayList;
        this.arrayListOld = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new ViewHolder(v);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Movies movies = arrayList.get(position);
        if(movies == null){
            return;
        }
        holder.tvListName.setText(String.valueOf(movies.getMovieName()));
        holder.tvListType.setText(String.valueOf(movies.getAge()));
        holder.tvListTime.setText(String.valueOf(movies.getTime()));
        holder.tvListDate.setText(String.valueOf(movies.getDate()));
        holder.imgListImage.setImageResource(movies.getPic());

        switch (movies.getAge()){
            case "P":
                holder.tvListType.setBackgroundDrawable(ContextCompat.getDrawable(context,R.drawable.custom_p));
                holder.tvListType.setTextColor(ContextCompat.getColor(context,R.color.green));
                break;
            case "C13":
                holder.tvListType.setBackgroundDrawable(ContextCompat.getDrawable(context,R.drawable.custom_c13));
                holder.tvListType.setTextColor(ContextCompat.getColor(context,R.color.yellow));
                break;
            case "C16":
                holder.tvListType.setBackgroundDrawable(ContextCompat.getDrawable(context,R.drawable.custom_c16));
                holder.tvListType.setTextColor(ContextCompat.getColor(context,R.color.orange));
                break;
            case "C18":
                holder.tvListType.setBackgroundDrawable(ContextCompat.getDrawable(context,R.drawable.custom_c18));
                holder.tvListType.setTextColor(ContextCompat.getColor(context,R.color.red));
                break;
            default:
                holder.tvListType.setTextColor(R.color.black);
        }

        holder.layoutitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickGoToMVDetail(movies);
            }
        });

        holder.imgListImage.setOnClickListener(new View.OnClickListener() {
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
        return arrayList.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String strSearch = charSequence.toString();
                // iết 1 hàm getFilter() trong hàm đó tạo 1 biến String strSearch, dùng if(strSearch.isEmpty())
                // để kiểm tra nếu thanh search không nhập gì hết thì nó sẽ hiện thị toàn bộ arrayList ra.
                if(strSearch.isEmpty()){
                    arrayList = arrayListOld;
                } else {
                    // nếu ngược lại thì dùng vòng for để duyệt qua các phim trong danh sách các phim được lưu trữ trong arrayListOld. Sau đó tạo 3 biến kiểu String gồm normalizedSearchString,normalizedMovieName và normalizedMovieDate
                    // dùng để chuẩn hóa tên và ngày phát hành sau đó dùng hàm if(normalizedMovieName.contains(normalizedSearchString) || normalizedMovieDate.contains(normalizedSearchString))
                    // để kiểm tra nếu dữ liệu được nhập trên thanh search có nằm trong phần tên hoặc ngày phát hành của các bộ phim trong arrayList thì nó sẽ hiện thị ra theo tương ứng
                    ArrayList<Movies> ArrayList = new ArrayList<>();
                    String normalizedSearchString = Normalizer.normalize(strSearch, Normalizer.Form.NFD)
                            .replaceAll("\\p{InCombiningDiacriticalMarks}+", "")
                            .toLowerCase();
                    for(Movies movies : arrayListOld){
                        String normalizedMovieName = Normalizer.normalize(movies.getMovieName(), Normalizer.Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "").toLowerCase();
                        String normalizedMovieDate = Normalizer.normalize(movies.getDate(), Normalizer.Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "").toLowerCase();
                        if(normalizedMovieName.contains(normalizedSearchString) || normalizedMovieDate.contains(normalizedSearchString)){
                            ArrayList.add(movies);
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
                arrayList = (ArrayList<Movies>) results.values;
                notifyDataSetChanged();
            }
        };
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvListName, tvListType, tvListTime, tvListDate;

        private RelativeLayout layoutitem;
        public ImageView imgListImage;
        public ViewHolder(View v) {
            super(v);
            layoutitem = v.findViewById(R.id.layout_item);
            tvListName = v.findViewById(R.id.tvListName);
            tvListType = v.findViewById(R.id.tvListType);
            tvListTime = v.findViewById(R.id.tvListTime);
            tvListDate = v.findViewById(R.id.tvListDate);
            imgListImage = v.findViewById(R.id.imgListMovie);
        }
    }
}
