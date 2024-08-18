package com.sinhvien.hbcinemapj.FragmentCus;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sinhvien.hbcinemapj.Adapter.HistoryTicketsCusAdapter;
import com.sinhvien.hbcinemapj.Class.BankSession;
import com.sinhvien.hbcinemapj.Class.Tickets;
import com.sinhvien.hbcinemapj.Class.UserSession;
import com.sinhvien.hbcinemapj.Database.DBHelper;
import com.sinhvien.hbcinemapj.Database.TicketsDatabase;
import com.sinhvien.hbcinemapj.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HistoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HistoryFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HistoryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HistoryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HistoryFragment newInstance(String param1, String param2) {
        HistoryFragment fragment = new HistoryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<Tickets> ticketsArrayList;
    private HistoryTicketsCusAdapter historyTicketsCusAdapter;

    TextView mTotalToSpend;
    View rootView;
    TicketsDatabase ticketsDatabase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView =  inflater.inflate(R.layout.fragment_history, container, false);

        ticketsDatabase = new TicketsDatabase(getContext());
        mTotalToSpend = (TextView) rootView.findViewById(R.id.tvTotalToSpend);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.HistoryTicketsRV);
        capNhatDuLieu();
        SumTotalToSpend();
        return rootView;
    }

    @SuppressLint("Range")
    public void capNhatDuLieu(){
        if (ticketsArrayList == null){
            ticketsArrayList = new ArrayList<>();
        } else {
            ticketsArrayList.removeAll(ticketsArrayList);
        }

        long idCus = UserSession.id;
        Cursor cursor = ticketsDatabase.GetTicketsFromId(idCus);
        ticketsArrayList.clear();
        if (cursor != null && cursor.getCount() > 0 && cursor.moveToFirst()){
            do {
                Tickets tickets = new Tickets();
                tickets.setId(cursor.getInt(cursor.getColumnIndex(DBHelper.COT_ID_TICKETS)));
                tickets.setIdCus(cursor.getInt(cursor.getColumnIndex(DBHelper.COT_IDCUS)));
                tickets.setIdTicketsMovie(cursor.getInt(cursor.getColumnIndex(DBHelper.COT_ID_TICKETS_MOVIE)));
                tickets.setNameCus(cursor.getString(cursor.getColumnIndex(DBHelper.COT_NAME_CUS_TICKETS)));
                tickets.setPhoneCus(cursor.getString(cursor.getColumnIndex(DBHelper.COT_PHONE_CUS)));
                tickets.setEmailCus(cursor.getString(cursor.getColumnIndex(DBHelper.COT_EMAIL_CUS_TICKETS)));
                tickets.setNameMovies(cursor.getString(cursor.getColumnIndex(DBHelper.COT_NAME_MOVIES_TICKETS)));
                tickets.setLocation(cursor.getString(cursor.getColumnIndex(DBHelper.COT_LOCATION)));
                tickets.setPhongChieu(cursor.getString(cursor.getColumnIndex(DBHelper.COT_PHONGCHIEU)));
                tickets.setFormatMovies(cursor.getString(cursor.getColumnIndex(DBHelper.COT_FORMAT_MOVIES_TICKETS)));
                tickets.setTime(cursor.getString(cursor.getColumnIndex(DBHelper.COT_TIME)));
                tickets.setBookingDate(cursor.getString(cursor.getColumnIndex(DBHelper.COT_BOOKING_DATE)));
                tickets.setSeats(cursor.getString(cursor.getColumnIndex(DBHelper.COT_SEATS)));
                tickets.setQuantity(cursor.getString(cursor.getColumnIndex(DBHelper.COT_QUANTITY)));
                tickets.setTotal(cursor.getString(cursor.getColumnIndex(DBHelper.COT_TOTAL)));
                ticketsArrayList.add(tickets);
                BankSession.idBankCus = tickets.getIdCus();
            } while (cursor.moveToNext());
        }
        SetRV();
    }

    public void SumTotalToSpend(){
        long idCus = UserSession.id;
        Cursor cursor = ticketsDatabase.SumCusTotal(idCus);
        if (cursor != null && cursor.moveToFirst()){
            DecimalFormat formatter = new DecimalFormat("###,###.##");
            double total = cursor.getDouble(0);
            mTotalToSpend.setText(formatter.format(total) + "đ");

            cursor.close();
        }
    }

    public void SetRV(){
        if (ticketsArrayList != null){
            mLayoutManager = new GridLayoutManager(rootView.getContext(),1);
            mRecyclerView.setLayoutManager(mLayoutManager);
            historyTicketsCusAdapter = new HistoryTicketsCusAdapter(rootView.getContext(), R.layout.history_tickets_list,ticketsArrayList);
            mRecyclerView.setAdapter(historyTicketsCusAdapter);
        }
    }
}