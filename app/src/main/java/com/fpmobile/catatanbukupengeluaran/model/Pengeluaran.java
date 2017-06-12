package com.fpmobile.catatanbukupengeluaran.model;

public class Pengeluaran {
    public String judulPengeluaran;
    public int idPengeluaran;
    public String deskripsiPengeluaran;
    public String jumlahPengeluaran;
    public int tanggalPengeluaran;
    public int bulanPengeluaran;
    public int tahunPengeluaran;

    public Pengeluaran(){

    }

    public Pengeluaran(String judulPengeluaran, String deskripsiPengeluaran, String jumlahPengeluaran, int tanggalPengeluaran, int bulanPengeluaran, int tahunPengeluaran) {
        this.judulPengeluaran = judulPengeluaran;
        this.deskripsiPengeluaran = deskripsiPengeluaran;
        this.jumlahPengeluaran = jumlahPengeluaran;
        this.tanggalPengeluaran = tanggalPengeluaran;
        this.bulanPengeluaran = bulanPengeluaran;
        this.tahunPengeluaran = tahunPengeluaran;
    }

    public int getTahunPengeluaran() {
        return tahunPengeluaran;
    }

    public void setTahunPengeluaran(int tahunPengeluaran) {
        this.tahunPengeluaran = tahunPengeluaran;
    }

    public String getJudulPengeluaran() {
        return judulPengeluaran;
    }

    public void setJudulPengeluaran(String judulPengeluaran) {
        this.judulPengeluaran = judulPengeluaran;
    }

    public int getIdPengeluaran() {
        return idPengeluaran;
    }

    public void setIdPengeluaran(int idPengeluaran) {
        this.idPengeluaran = idPengeluaran;
    }

    public String getDeskripsiPengeluaran() {
        return deskripsiPengeluaran;
    }

    public void setDeskripsiPengeluaran(String deskripsiPengeluaran) {
        this.deskripsiPengeluaran = deskripsiPengeluaran;
    }

    public String getJumlahPengeluaran() {
        return jumlahPengeluaran;
    }

    public void setJumlahPengeluaran(String jumlahPengeluaran) {
        this.jumlahPengeluaran = jumlahPengeluaran;
    }

    public int getTanggalPengeluaran() {
        return tanggalPengeluaran;
    }

    public void setTanggalPengeluaran(int tanggalPengeluaran) {
        this.tanggalPengeluaran = tanggalPengeluaran;
    }

    public int getBulanPengeluaran() {
        return bulanPengeluaran;
    }

    public void setBulanPengeluaran(int bulanPengeluaran) {
        this.bulanPengeluaran = bulanPengeluaran;
    }

}
