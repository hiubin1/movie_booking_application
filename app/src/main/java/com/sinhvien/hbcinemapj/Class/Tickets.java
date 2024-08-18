package com.sinhvien.hbcinemapj.Class;

public class Tickets {

    private long id, idTicketsMovie, idCus;

    private String Seats, Time, Quantity, Location, PhongChieu, BookingDate, Total, PhoneCus,NameCus,NameMovies,FormatMovies,EmailCus;

    public Tickets(long id, long idTicketsMovie, long idCus, String seats, String time, String quantity, String location, String phongChieu, String bookingDate, String total, String phoneCus, String nameCus, String nameMovies, String formatMovies, String emailCus) {
        this.id = id;
        this.idTicketsMovie = idTicketsMovie;
        this.idCus = idCus;
        Seats = seats;
        Time = time;
        Quantity = quantity;
        Location = location;
        PhongChieu = phongChieu;
        BookingDate = bookingDate;
        Total = total;
        PhoneCus = phoneCus;
        NameCus = nameCus;
        NameMovies = nameMovies;
        FormatMovies = formatMovies;
        EmailCus = emailCus;
    }

    public Tickets() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdTicketsMovie() {
        return idTicketsMovie;
    }

    public void setIdTicketsMovie(long idTicketsMovie) {
        this.idTicketsMovie = idTicketsMovie;
    }

    public long getIdCus() {
        return idCus;
    }

    public void setIdCus(long idCus) {
        this.idCus = idCus;
    }

    public String getSeats() {
        return Seats;
    }

    public void setSeats(String seats) {
        Seats = seats;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getPhongChieu() {
        return PhongChieu;
    }

    public void setPhongChieu(String phongChieu) {
        PhongChieu = phongChieu;
    }

    public String getBookingDate() {
        return BookingDate;
    }

    public void setBookingDate(String bookingDate) {
        BookingDate = bookingDate;
    }

    public String getTotal() {
        return Total;
    }

    public void setTotal(String total) {
        Total = total;
    }

    public String getPhoneCus() {
        return PhoneCus;
    }

    public void setPhoneCus(String phoneCus) {
        PhoneCus = phoneCus;
    }

    public String getNameCus() {
        return NameCus;
    }

    public void setNameCus(String nameCus) {
        NameCus = nameCus;
    }

    public String getNameMovies() {
        return NameMovies;
    }

    public void setNameMovies(String nameMovies) {
        NameMovies = nameMovies;
    }

    public String getFormatMovies() {
        return FormatMovies;
    }

    public void setFormatMovies(String formatMovies) {
        FormatMovies = formatMovies;
    }

    public String getEmailCus() {
        return EmailCus;
    }

    public void setEmailCus(String emailCus) {
        EmailCus = emailCus;
    }
}
