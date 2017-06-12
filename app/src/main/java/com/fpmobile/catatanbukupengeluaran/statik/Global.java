package com.fpmobile.catatanbukupengeluaran.statik;


public class Global {
    private static Global instance = new Global();

    public static Global getInstance() {
        return instance;
    }

    public static void setInstance(Global instance) {
        Global.instance = instance;
    }


    private Global() {

    }

    //Set fragment aktif
    public static String statistikLaporanFragment = "1";
    public static String editPemasukanFragment = "2";
    public static String editPengeluaranFragment = "3";
    public static String lihatPemasukanFragment = "4";
    public static String listPemasukanFragment = "5";
    public static String pengaturanFragment = "6";
    public static String listPengeluaranFragment = "7";
    public static String tambahPemasukanFragment = "8";
    public static String tambahPengeluaranFragment ="9";
    public static String lihatPengeluaranFragment = "10";


    public String lokasiFragmentAktif;
    public String getLokasiFragmentAktif() {
        return lokasiFragmentAktif;
    }

    public void setLokasiFragmentAktif(String lokasiFragmentAktif) {
        this.lokasiFragmentAktif = lokasiFragmentAktif;
    }

}
