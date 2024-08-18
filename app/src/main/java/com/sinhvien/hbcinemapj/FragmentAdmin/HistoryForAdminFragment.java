package com.sinhvien.hbcinemapj.FragmentAdmin;

import android.annotation.SuppressLint;
import android.app.SearchManager;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.appcompat.widget.SearchView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sinhvien.hbcinemapj.Adapter.HistoryTicketsAdminAdapter;
import com.sinhvien.hbcinemapj.Class.Tickets;
import com.sinhvien.hbcinemapj.Class.TicketsSession;
import com.sinhvien.hbcinemapj.Database.DBHelper;
import com.sinhvien.hbcinemapj.Database.TicketsDatabase;
import com.sinhvien.hbcinemapj.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HistoryForAdminFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HistoryForAdminFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HistoryForAdminFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HistoryForAdminFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HistoryForAdminFragment newInstance(String param1, String param2) {
        HistoryForAdminFragment fragment = new HistoryForAdminFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<Tickets> ticketsArrayList;
    private HistoryTicketsAdminAdapter historyTicketsAdapter;

    private SearchView searchView;

    TextView mtvTotalRevenue;


    TicketsDatabase ticketsDatabase;

    View rootView;

    @SuppressLint("Range")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_history_for_admin, container, false);
        ticketsDatabase = new TicketsDatabase(getContext());
        mRecyclerView = rootView.findViewById(R.id.HistoryTicketsRV);
        mtvTotalRevenue = (TextView) rootView.findViewById(R.id.tvTotalRevenue);
        searchView = rootView.findViewById(R.id.search_view_Tickets);
        searchView.clearFocus();
        capNhatDuLieu();
        SumTotal();

        return  rootView;
    }

    @SuppressLint("Range")
    public void capNhatDuLieu(){

        if (ticketsArrayList == null){
            ticketsArrayList = new ArrayList<>();
        } else {
            ticketsArrayList.removeAll(ticketsArrayList);
        }

        Cursor cursor = ticketsDatabase.layTatCaDuLieu();
        if (cursor != null){
            while (cursor.moveToNext()){
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
                ticketsArrayList.add(tickets);
            }
        }
        SetRV();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {

        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                historyTicketsAdapter.getFilter().filter(s);
                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }



    @SuppressLint("Range")
    public void SumTotal(){
        Cursor cursor = ticketsDatabase.SumAllTotal();
        if (cursor != null && cursor.moveToFirst()){
            DecimalFormat formatter = new DecimalFormat("###,###.##");
            double total = cursor.getDouble(0);
                mtvTotalRevenue.setText(formatter.format(total));

            cursor.close();
        }
    }

    public void SetRV(){
        if (ticketsArrayList != null){
            mLayoutManager = new GridLayoutManager(rootView.getContext(),1);
            mRecyclerView.setLayoutManager(mLayoutManager);

            historyTicketsAdapter = new HistoryTicketsAdminAdapter(rootView.getContext(), R.layout.history_admin_tickets_list, ticketsArrayList);

            mRecyclerView.setAdapter(historyTicketsAdapter);
        }
    }
}