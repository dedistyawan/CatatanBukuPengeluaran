package com.fpmobile.catatanbukupengeluaran.statik;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;


public class Preferences {
    //Shared Eeferences
    //Tampilan
    public static String BULAN_INI = "bulan ini";
    public static String BULAN_KEMARIN = "bulan kemarin";
    public static String TAHUN_KEMARIN = "tahun kemarin";
    public static String TAHUN_INI = "tahun ini";
    public static String SEMUA_DATA = "semua data";

    //pengingat
    public static String SATU_HARI = "satu hari";
    public static String DUA_HARI = "dua hari";
    public static String TIGA_HARI = "tiga hari";
    public static String SATU_MINGGU = "satu minggu";
    public static String DUA_MINGGU = "dua minggu";
    public static String SATU_BULAN = "satu bulan";
    public static String JANGAN_PERNAH = "jangan pernah";

    //tulis preferences
    public void tulisPreferences(Context context, String pengaturanTampilan, String pengaturanPengingat){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("pengingat", pengaturanPengingat);
        editor.putString("tampilan", pengaturanTampilan);
        editor.apply();
    }

    //baca preferences
    public String pengingat;
    public String tampilan;
    public void bacaPreferences(Context context){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        pengingat = preferences.getString("pengingat", SATU_HARI);
        tampilan = preferences.getString("tampilan", BULAN_INI);
    }
}
