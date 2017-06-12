package com.fpmobile.catatanbukupengeluaran.model;

public class Pemasukan {

    public int idPemasukan;
    public String judulPemasukan;
    public String deskripsiPemasukan;
    public String jumlahPemasukan;
    public int tanggalPemasukan;
    public int bulanPemasukan;

    public int getTahunPemasukan() {
        return tahunPemasukan;
    }

    public void setTahunPemasukan(int tahunPemasukan) {
        this.tahunPemasukan = tahunPemasukan;
    }

    public int getBulanPemasukan() {
        return bulanPemasukan;
    }

    public void setBulanPemasukan(int bulanPemasukan) {
        this.bulanPemasukan = bulanPemasukan;
    }

    public int getTanggalPemasukan() {
        return tanggalPemasukan;
    }

    public void setTanggalPemasukan(int tanggalPemasukan) {
        this.tanggalPemasukan = tanggalPemasukan;
    }

    public int tahunPemasukan;

    public Pemasukan(){

    }

    public Pemasukan(String judulPemasukan, String deskripsiPemasukan, String jumlahPemasukan, int tanggalPemasukan, int bulanPemasukan, int tahunPemasukan) {
        this.judulPemasukan = judulPemasukan;
        this.deskripsiPemasukan = deskripsiPemasukan;
        this.jumlahPemasukan = jumlahPemasukan;
        this.tanggalPemasukan = tanggalPemasukan;
        this.bulanPemasukan = bulanPemasukan;
        this.tahunPemasukan = tahunPemasukan;
    }

    public Pemasukan(int idPemasukan, String judulPemasukan, String deskripsiPemasukan, String jumlahPemasukan, int tanggalPemasukan, int bulanPemasukan, int tahunPemasukan) {
        this.idPemasukan = idPemasukan;
        this.judulPemasukan = judulPemasukan;
        this.deskripsiPemasukan = deskripsiPemasukan;
        this.jumlahPemasukan = jumlahPemasukan;
        this.tanggalPemasukan = tanggalPemasukan;
        this.bulanPemasukan = bulanPemasukan;
        this.tahunPemasukan = tahunPemasukan;
    }

    public String getDeskripsiPemasukan() {
        return deskripsiPemasukan;
    }

    public void setDeskripsiPemasukan(String deskripsiPemasukan) {
        this.deskripsiPemasukan = deskripsiPemasukan;
    }

    public String getJumlahPemasukan() {
        return jumlahPemasukan;
    }

    public void setJumlahPemasukan(String jumlahPemasukan) {
        this.jumlahPemasukan = jumlahPemasukan;
    }

    public String getJudulPemasukan() {
        return judulPemasukan;
    }

    public void setJudulPemasukan(String judulPemasukan) {
        this.judulPemasukan = judulPemasukan;
    }

    public int getIdPemasukan() {
        return idPemasukan;
    }

    public void setIdPemasukan(int idPemasukan) {
        this.idPemasukan = idPemasukan;
    }

}
