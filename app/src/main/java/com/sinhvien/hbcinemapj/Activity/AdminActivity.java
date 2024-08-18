package com.sinhvien.hbcinemapj.Activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sinhvien.hbcinemapj.FragmentAdmin.AddNewsFragment;
import com.sinhvien.hbcinemapj.R;
import com.sinhvien.hbcinemapj.FragmentAdmin.AddPhimFragment;
import com.sinhvien.hbcinemapj.FragmentAdmin.AdminProfileFragment;
import com.sinhvien.hbcinemapj.FragmentAdmin.HistoryForAdminFragment;

public class AdminActivity extends AppCompatActivity {

    private ActionBar toolBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        toolBar = getSupportActionBar();
        toolBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.gradient_background));
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        toolBar.setDisplayShowCustomEnabled(true);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view =inflater.inflate(R.layout.custom_actionbar, null);
        toolBar.setCustomView(view);

        BottomNavigationView navAdmin = (BottomNavigationView) findViewById(R.id.AdminNavigation);
        navAdmin.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        toolBar.setLogo(R.drawable.blackwhitelogo);
        toolBar.setDisplayUseLogoEnabled(true);
        toolBar.setTitle("Admin");
        loadFragment(new AddPhimFragment());

    }

    BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment frag;
            switch (menuItem.getItemId()) {
                case R.id.nav_AddPhim:
                    frag = new AddPhimFragment();
                    loadFragment(frag);
                    return true;

                case R.id.nav_AddNews:
                    frag = new AddNewsFragment();
                    loadFragment(frag);
                    return true;

                case R.id.nav_AdminHistory:
                    frag = new HistoryForAdminFragment();
                    loadFragment(frag);
                    return true;

                case R.id.nav_AdminProfile:
                    frag = new AdminProfileFragment();
                    loadFragment(frag);
                    return true;
            }

            return false;
        }
    };

    private void loadFragment(Fragment fragment) {
        //load Fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.FrameContainer, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void btnInfoCus_onclick(View view) {
        Intent i = new Intent(getApplicationContext(),ThongTinKhachHangActivity.class);
        startActivity(i);
    }

    public void btnLogOutAdmin_onclick(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(AdminActivity.this);
        builder.setTitle("Thông báo");
        builder.setMessage("Bạn có muốn đăng xuất ?");
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent logout = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(logout);
            }
        });
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        builder.create().show();
    }
}