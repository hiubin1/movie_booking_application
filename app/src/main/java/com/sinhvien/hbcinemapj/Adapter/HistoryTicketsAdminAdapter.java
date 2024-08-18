package com.sinhvien.hbcinemapj.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sinhvien.hbcinemapj.Class.Tickets;
import com.sinhvien.hbcinemapj.R;

import java.text.Normalizer;
import java.util.ArrayList;

public class HistoryTicketsAdminAdapter extends RecyclerView.Adapter<HistoryTicketsAdminAdapter.HistoryTicketsAdminHolder> implements Filterable {

    Context context;

    int layout;

    ArrayList<Tickets> ticketsArrayList;

    ArrayList<Tickets> ticketsArrayList1;

    public HistoryTicketsAdminAdapter(Context context, int layout, ArrayList<Tickets> ticketsArrayList) {
        this.context = context;
        this.layout = layout;
        this.ticketsArrayList = ticketsArrayList;
        this.ticketsArrayList1 = ticketsArrayList;
    }

    @NonNull
    @Override
    public HistoryTicketsAdminHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(layout,parent,false);
        return new HistoryTicketsAdminAdapter.HistoryTicketsAdminHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryTicketsAdminHolder holder, int position) {
        Tickets tickets = ticketsArrayList.get(position);
        holder.IdTicketsAdmin.setText(String.valueOf(tickets.getId()));
        holder.NameTicketsListAdmin.setText(String.valueOf(tickets.getNameCus()));
        holder.PhoneTicketsListAdmin.setText(String.valueOf(tickets.getPhoneCus()));
        holder.EmailTicketsListAdmin.setText(String.valueOf(tickets.getEmailCus()));
        holder.NameMovieTicketsListAdmin.setText(String.valueOf(tickets.getNameMovies()));
        holder.CinemaTicketsListAdmin.setText(String.valueOf(tickets.getLocation()));
        holder.RoomTicketsListAdmin.setText(String.valueOf(tickets.getPhongChieu()));
        holder.FormatTicketsListAdmin.setText(String.valueOf(tickets.getFormatMovies()));
        holder.TimeTicketsListAdmin.setText(String.valueOf(tickets.getTime()));
        holder.DateBookingTicketsListAdmin.setText(String.valueOf(tickets.getBookingDate()));
        holder.SeatNumTicketsListAdmin.setText(String.valueOf(tickets.getSeats()));
        holder.QuantitySeatTicketsListAdmin.setText(String.valueOf(tickets.getQuantity()));
    }

    @Override
    public int getItemCount() {
        return ticketsArrayList.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String strSearch = charSequence.toString();

                if (strSearch.isEmpty()){
                    ticketsArrayList = ticketsArrayList1;
                } else {
                    ArrayList<Tickets> arrayList = new ArrayList<>();
                    String normalizedSearchString = Normalizer.normalize(strSearch, Normalizer.Form.NFD)
                            .replaceAll("\\p{InCombiningDiacriticalMarks}+", "")
                            .toLowerCase();
                    for (Tickets tickets : ticketsArrayList1){
                        String normalizedTicketsRoom = Normalizer.normalize(tickets.getPhongChieu(),Normalizer.Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "").toLowerCase();
                        String normalizedTicketsTime = Normalizer.normalize(tickets.getTime(),Normalizer.Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "").toLowerCase();
                        String normalizedTicketsNameMovie = Normalizer.normalize(tickets.getNameMovies(),Normalizer.Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "").toLowerCase();
                        String normalizedTicketsLocation = Normalizer.normalize(tickets.getLocation(),Normalizer.Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "").toLowerCase();
                        if (normalizedTicketsRoom.contains(normalizedSearchString) || normalizedTicketsTime.contains(normalizedSearchString)
                                || normalizedTicketsLocation.contains(normalizedSearchString) || normalizedTicketsNameMovie.contains(normalizedSearchString)){
                            arrayList.add(tickets);
                        }
                    }
                    ticketsArrayList = arrayList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = ticketsArrayList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                ticketsArrayList = (ArrayList<Tickets>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }


    public class HistoryTicketsAdminHolder extends RecyclerView.ViewHolder{

        public TextView IdTicketsAdmin,NameTicketsListAdmin, PhoneTicketsListAdmin, EmailTicketsListAdmin, NameMovieTicketsListAdmin, CinemaTicketsListAdmin,
                RoomTicketsListAdmin, FormatTicketsListAdmin, TimeTicketsListAdmin, DateBookingTicketsListAdmin
                , SeatNumTicketsListAdmin, QuantitySeatTicketsListAdmin;


        public HistoryTicketsAdminHolder(@NonNull View v) {
            super(v);
            IdTicketsAdmin = (TextView) v.findViewById(R.id.IdTicketsAdmin);
            NameTicketsListAdmin = (TextView) v.findViewById(R.id.NameTicketsListAdmin);
            PhoneTicketsListAdmin = (TextView) v.findViewById(R.id.PhoneTicketsListAdmin);
            EmailTicketsListAdmin = (TextView) v.findViewById(R.id.EmailTicketsListAdmin);
            NameMovieTicketsListAdmin = (TextView) v.findViewById(R.id.NameMovieTicketsListAdmin);
            CinemaTicketsListAdmin = (TextView) v.findViewById(R.id.CinemaTicketsListAdmin);
            RoomTicketsListAdmin = (TextView) v.findViewById(R.id.RoomTicketsListAdmin);
            FormatTicketsListAdmin = (TextView) v.findViewById(R.id.FormatTicketsListAdmin);
            TimeTicketsListAdmin = (TextView) v.findViewById(R.id.TimeTicketsListAdmin);
            DateBookingTicketsListAdmin = (TextView) v.findViewById(R.id.DateBookingTicketsListAdmin);
            SeatNumTicketsListAdmin = (TextView) v.findViewById(R.id.SeatNumTicketsListAdmin);
            QuantitySeatTicketsListAdmin = (TextView) v.findViewById(R.id.QuantitySeatTicketsListAdmin);
        }
    }
}
