package com.sinhvien.hbcinemapj.Activity;

import android.annotation.SuppressLint;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sinhvien.hbcinemapj.Adapter.MoviesAdapter;
import com.sinhvien.hbcinemapj.Database.UserDatabse;
import com.sinhvien.hbcinemapj.FragmentCus.HistoryFragment;
import com.sinhvien.hbcinemapj.R;
import com.sinhvien.hbcinemapj.FragmentCus.HomeFragment;
import com.sinhvien.hbcinemapj.FragmentCus.NewsFragment;
import com.sinhvien.hbcinemapj.FragmentCus.ProfileFragment;
import com.sinhvien.hbcinemapj.FragmentCus.TicketsFragment;

public class CustomerActivity extends AppCompatActivity {
    private ActionBar actionBar;

    UserDatabse databse;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);


        //ActionBar with only Logo
        actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.gradient_background));
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        actionBar.setDisplayShowCustomEnabled(true);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view =inflater.inflate(R.layout.custom_actionbar, null);
        actionBar.setCustomView(view);


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        actionBar.setLogo(R.drawable.blackwhitelogo);
        actionBar.setDisplayUseLogoEnabled(false);
        loadFragment(new HomeFragment());
        databse = new UserDatabse(this);

    }

    // bottom navigation
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()){
                case R.id.nav_home:
                    fragment = new HomeFragment();
                    loadFragment(fragment);
                    return true;

                case R.id.nav_news:
                    fragment = new NewsFragment();
                    loadFragment(fragment);
                    return true;

                case R.id.nav_booking:
                    fragment = new TicketsFragment();
                    loadFragment(fragment);
                    return true;

                case R.id.nav_history:
                    fragment = new HistoryFragment();
                    loadFragment(fragment);
                    return true;

                case R.id.nav_profile:
                    fragment = new ProfileFragment();
                    loadFragment(fragment);
                    return true;
            }
            return false;
        }
    };

    private void loadFragment(Fragment fragment) {
        // load fragment
        fragment.getActivity();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void imgMoviesDCHome_onclick(View view) {
        Intent i = new Intent(getApplicationContext(), ThongTinChiTietPhimActivity.class);
        startActivity(i);
    }

    public void imgNewsHome_onclick(View view) {
        Intent i = new Intent(getApplicationContext(), ThongTinChiTietTinTucActivity.class);
        startActivity(i);
    }

    public void btnDetailsProfile(View view) {
        Intent DP = new Intent(getApplicationContext(), ProfileDetails.class);
        startActivity(DP);
    }

    public void btnLogOut_onclick(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(CustomerActivity.this);
        builder.setTitle("Thông Báo");
        builder.setMessage("Bạn có muốn đăng xuất ?");
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
        builder.create().show();
    }

    public void btnPofileCinema_onclick(View view) {
        Intent i = new Intent(getApplicationContext(), ThongTinRapActivty.class);
        startActivity(i);
    }

    public void btnResetPass_onclick(View view) {
        Intent i = new Intent(getApplicationContext(), DoiMatKhauActivity.class);
        startActivity(i);
    }
}