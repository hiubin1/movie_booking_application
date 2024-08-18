package com.sinhvien.hbcinemapj.Adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.sinhvien.hbcinemapj.Class.BankSession;
import com.sinhvien.hbcinemapj.Database.BankDatabase;
import com.sinhvien.hbcinemapj.R;
import com.sinhvien.hbcinemapj.Class.Banks;

import java.util.ArrayList;

public class BankAdapter extends BaseAdapter {

    Context context;
    int layout;
    ArrayList<Banks> banks;

    TextView mTvNumBank;
    TextView mTvNameBank;

    CheckBox mcbChoose;



    public BankAdapter(Context context, int layout, ArrayList<Banks> banks) {
        this.context = context;
        this.layout = layout;
        this.banks = banks;
    }

    @Override
    public int getCount() {
        return banks.size();
    }

    @Override
    public Object getItem(int position) {
        return banks.get(position);
    }

    @Override
    public long getItemId(int position) {
        return banks.get(position).getBankid();
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View viewBank;

        if(view == null) {
            viewBank = View.inflate(viewGroup.getContext(), R.layout.tk_ngan_hang, null);
        } else {
            viewBank = view;
        }

        mTvNameBank = (TextView) viewBank.findViewById(R.id.tvNameBank);
        mTvNumBank = (TextView) viewBank.findViewById(R.id.tvNumBank);
        mcbChoose = (CheckBox) viewBank.findViewById(R.id.cbChoose);

        Banks banks = (Banks) getItem(position);

        mTvNumBank.setText(String.valueOf(banks.getBankNum()));
        mTvNameBank.setText(String.valueOf(banks.getBankName()));


        return viewBank;
    }
}
