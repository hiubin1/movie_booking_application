package com.sinhvien.hbcinemapj.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sinhvien.hbcinemapj.Class.Tickets;
import com.sinhvien.hbcinemapj.R;

import java.util.ArrayList;

public class HistoryTicketsCusAdapter extends RecyclerView.Adapter<HistoryTicketsCusAdapter.HistoryTicketsHolder>{

    Context context;

    int layout;

    ArrayList<Tickets> ticketsArrayList;


    public HistoryTicketsCusAdapter(Context context, int layout, ArrayList<Tickets> ticketsArrayList){
        this.context = context;
        this.layout = layout;
        this.ticketsArrayList = ticketsArrayList;

    }

    @NonNull
    @Override
    public HistoryTicketsCusAdapter.HistoryTicketsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(layout, parent, false);
        return new HistoryTicketsCusAdapter.HistoryTicketsHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryTicketsHolder holder, int position) {
        Tickets tickets = ticketsArrayList.get(position);
        holder.IdTicketsUser.setText(String.valueOf(tickets.getId()));
        holder.NameTicketsList.setText(String.valueOf(tickets.getNameCus()));
        holder.PhoneTicketsList.setText(String.valueOf(tickets.getPhoneCus()));
        holder.EmailTicketsList.setText(String.valueOf(tickets.getEmailCus()));
        holder.NameMovieTicketsList.setText(String.valueOf(tickets.getNameMovies()));
        holder.CinemaTicketsList.setText(String.valueOf(tickets.getLocation()));
        holder.RoomTicketsList.setText(String.valueOf(tickets.getPhongChieu()));
        holder.FormatTicketsList.setText(String.valueOf(tickets.getFormatMovies()));
        holder.TimeTicketsList.setText(String.valueOf(tickets.getTime()));
        holder.DateBookingTicketsList.setText(String.valueOf(tickets.getBookingDate()));
        holder.SeatNumTicketsList.setText(String.valueOf(tickets.getSeats()));
        holder.QuantitySeatTicketsList.setText(String.valueOf(tickets.getQuantity()));
        holder.TotalMoneyTicketsList.setText(String.valueOf(tickets.getTotal()));
    }

    @Override
    public int getItemCount() {
        return ticketsArrayList.size();
    }

    public class HistoryTicketsHolder extends RecyclerView.ViewHolder {

        public TextView IdTicketsUser,NameTicketsList, PhoneTicketsList, EmailTicketsList, NameMovieTicketsList, CinemaTicketsList,
                RoomTicketsList, FormatTicketsList, TimeTicketsList, DateBookingTicketsList, SeatNumTicketsList, QuantitySeatTicketsList,
                TotalMoneyTicketsList;



        public HistoryTicketsHolder(@NonNull View v) {
            super(v);
            IdTicketsUser = (TextView) v.findViewById(R.id.IdTicketsUser);
            NameTicketsList = (TextView) v.findViewById(R.id.NameTicketsList);
            PhoneTicketsList = (TextView) v.findViewById(R.id.PhoneTicketsList);
            EmailTicketsList = (TextView) v.findViewById(R.id.EmailTicketsList);
            NameMovieTicketsList = (TextView) v.findViewById(R.id.NameMovieTicketsList);
            CinemaTicketsList = (TextView) v.findViewById(R.id.CinemaTicketsList);
            RoomTicketsList = (TextView) v.findViewById(R.id.RoomTicketsList);
            FormatTicketsList = (TextView) v.findViewById(R.id.FormatTicketsList);
            TimeTicketsList = (TextView) v.findViewById(R.id.TimeTicketsList);
            DateBookingTicketsList = (TextView) v.findViewById(R.id.DateBookingTicketsList);
            SeatNumTicketsList = (TextView) v.findViewById(R.id.SeatNumTicketsList);
            QuantitySeatTicketsList = (TextView) v.findViewById(R.id.QuantitySeatTicketsList);
            TotalMoneyTicketsList = (TextView) v.findViewById(R.id.TotalMoneyTicketsList);
        }
    }
}
