package com.sinhvien.hbcinemapj.FragmentCus;

import static com.sinhvien.hbcinemapj.Class.UserSession.Pic;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.sinhvien.hbcinemapj.Class.User;
import com.sinhvien.hbcinemapj.Class.UserSession;
import com.sinhvien.hbcinemapj.Database.DBHelper;
import com.sinhvien.hbcinemapj.Database.UserDatabse;
import com.sinhvien.hbcinemapj.R;

public class ProfileFragment extends Fragment {
    TextView mtvNameUser;

    ImageView mImgUserProfile;
    UserDatabse database;


    @SuppressLint({"MissingInflatedId", "Range"})
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_profile,container,false);
        mtvNameUser = (TextView) view.findViewById(R.id.tvNameUser);
        mImgUserProfile = (ImageView) view.findViewById(R.id.ImgUserProfile);
        database = new UserDatabse(getContext());
        String gender = UserSession.Gender;
        String email = UserSession.userEmail;
        int Pic = 0;
        String username = "";

        Cursor cursor = database.GetNameFromEmail(email);
        if(cursor.moveToFirst()){
            username = cursor.getString(cursor.getColumnIndex("_Name"));
        }
        mtvNameUser.setText(username);


        cursor = database.GetPicFromGender(gender);
        if (cursor.moveToFirst()){
            if (gender.equals("Nam")){
                Pic = R.drawable.boruto256;
            } else if (gender.equals("Nữ")){
                Pic = R.drawable.sadara256;
            }
        }
        mImgUserProfile.setImageResource(Pic);
        return view;
    }
}
