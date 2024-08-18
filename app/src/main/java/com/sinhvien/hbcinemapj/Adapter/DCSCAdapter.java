package com.sinhvien.hbcinemapj.Adapter;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.sinhvien.hbcinemapj.Fragment.FragmentDangChieu;
import com.sinhvien.hbcinemapj.Fragment.FragmentSapChieu;

public class DCSCAdapter extends FragmentStatePagerAdapter {
    Context context;
    int totalTabs;
    public DCSCAdapter(Context c, FragmentManager fm, int totalTabs) {
        super(fm);
        context = c;
        this.totalTabs = totalTabs;
    }

    @Override
    public Fragment getItem(int position) {
       switch (position){
           case 0:
               FragmentDangChieu dc = new FragmentDangChieu();
               return dc;

           case 1:
               FragmentSapChieu sc = new FragmentSapChieu();
               return sc;

           default:
               return null;
       }
    }

    @Override
    public int getCount() {
        return totalTabs;
    }
}
