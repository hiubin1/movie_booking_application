package com.sinhvien.hbcinemapj.Class;

public class Banks {

    long bankid, IdCusBank;
    private String bankNum, bankName,NgayPhatHanh,BankAccName;

    public Banks(){
    }

    public Banks(long bankid, long idCusBank, String bankNum, String bankName, String ngayPhatHanh, String bankAccName) {
        this.bankid = bankid;
        IdCusBank = idCusBank;
        this.bankNum = bankNum;
        this.bankName = bankName;
        NgayPhatHanh = ngayPhatHanh;
        BankAccName = bankAccName;
    }

    public long getBankid() {
        return bankid;
    }

    public void setBankid(long bankid) {
        this.bankid = bankid;
    }

    public long getIdCusBank() {
        return IdCusBank;
    }

    public void setIdCusBank(long idCusBank) {
        IdCusBank = idCusBank;
    }

    public String getBankNum() {
        return bankNum;
    }

    public void setBankNum(String bankNum) {
        this.bankNum = bankNum;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getNgayPhatHanh() {
        return NgayPhatHanh;
    }

    public void setNgayPhatHanh(String ngayPhatHanh) {
        NgayPhatHanh = ngayPhatHanh;
    }

    public String getBankAccName() {
        return BankAccName;
    }

    public void setBankAccName(String bankAccName) {
        BankAccName = bankAccName;
    }
}
