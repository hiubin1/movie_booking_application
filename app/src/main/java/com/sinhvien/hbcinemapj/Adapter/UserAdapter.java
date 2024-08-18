package com.sinhvien.hbcinemapj.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sinhvien.hbcinemapj.Class.User;
import com.sinhvien.hbcinemapj.R;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserAdapterViewholder> {

    Context context;

    int layout;

    ArrayList<User> userArrayList;

    public UserAdapter(Context context, int layout, ArrayList<User> userArrayList) {
        this.context = context;
        this.layout = layout;
        this.userArrayList = userArrayList;
    }

    @NonNull
    @Override
    public UserAdapterViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(layout,parent,false);
        return new UserAdapterViewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapterViewholder holder, int position) {
        User user = userArrayList.get(position);
        holder.mimgCusInfo.setImageResource(user.getPic());
        holder.mtvNameCusInfo.setText(String.valueOf(user.get_Name()));
        holder.mtvEmailCusInfo.setText(String.valueOf(user.get_Email()));
        holder.mtvPhoneCusInfo.setText(String.valueOf(user.get_PhoneNum()));
        holder.mtvDateCusInfo.setText(String.valueOf(user.get_Birth()));
        holder.mtvGenderCusInfo.setText(String.valueOf(user.get_Gender()));
        holder.mIdCusInfo.setText(String.valueOf(user.getId()));
    }

    @Override
    public int getItemCount() {
        return userArrayList.size();
    }

    public class UserAdapterViewholder extends RecyclerView.ViewHolder{
        public ImageView mimgCusInfo;

        public TextView mIdCusInfo,mtvNameCusInfo, mtvEmailCusInfo, mtvPhoneCusInfo, mtvGenderCusInfo, mtvDateCusInfo;

        public UserAdapterViewholder(@NonNull View v) {
            super(v);
            mIdCusInfo = v.findViewById(R.id.tvIdCusInfo);
            mimgCusInfo = v.findViewById(R.id.imgCusInfo);
            mtvNameCusInfo = v.findViewById(R.id.tvNameCusInfo);
            mtvEmailCusInfo = v.findViewById(R.id.tvEmailCusInfo);
            mtvPhoneCusInfo = v.findViewById(R.id.tvPhoneCusInfo);
            mtvGenderCusInfo = v.findViewById(R.id.tvGenderCusInfo);
            mtvDateCusInfo = v.findViewById(R.id.tvDateCusInfo);
        }
    }
}
