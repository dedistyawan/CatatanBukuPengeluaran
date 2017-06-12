package com.fpmobile.catatanbukupengeluaran.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.fpmobile.catatanbukupengeluaran.R;
import com.fpmobile.catatanbukupengeluaran.statik.Preferences;

import info.hoang8f.widget.FButton;

public class SettingActivity extends AppCompatActivity {
    FButton simpan;
    RadioGroup pengaturanPengingat;
    RadioGroup pengaturanTampilan;
    Preferences sharedPref = new Preferences();

    //deklarasi radio button tampilan
    RadioButton bulanIni;
    RadioButton bulanKemarin;
    RadioButton tahunIni;
    RadioButton tahunKemarin;
    RadioButton semuaData;

    //deklarasi radio button pengingat
    RadioButton satuHari;
    RadioButton duaHari;
    RadioButton tigaHari;
    RadioButton satuMinggu;
    RadioButton duaMinggu;
    RadioButton satuBulan;
    RadioButton janganPernah;

    //sembunyikan untuk sementara
    LinearLayout sembunyikan;

    @Override
    protected void onCreate(Bundle savedIncycle){
        super.onCreate(savedIncycle);
        setContentView(R.layout.activity_setting);

        sembunyikan = (LinearLayout) findViewById(R.id.sembunyikan);
        sembunyikan.setVisibility(View.GONE);

        inisialisasiUserInterface();
        bacaPreferences();
        aturUserInterface();
        Toast.makeText(getApplicationContext(), "Diatur untuk menampilkan "+ sharedPref.tampilan, Toast.LENGTH_LONG).show();

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPref.tulisPreferences(getApplicationContext(), getTampilanTerpilih(), getPengingatTerpilih());
                sharedPref.bacaPreferences(getApplicationContext());
                Toast.makeText(getApplicationContext(), "Diatur untuk menampilkan "+ sharedPref.tampilan, Toast.LENGTH_LONG).show();
            }
        });
    }

    public void inisialisasiUserInterface(){
        simpan = (FButton) findViewById(R.id.simpan_pengaturan);
        pengaturanPengingat = (RadioGroup) findViewById(R.id.pengaturan_pengingat);
        pengaturanTampilan = (RadioGroup) findViewById(R.id.pengaturan_tampilan);
        //Pengingat
        satuHari = (RadioButton) findViewById(R.id.radioButtonSatuHari);
        duaHari = (RadioButton) findViewById(R.id.radioButtonDuaHari);
        tigaHari = (RadioButton) findViewById(R.id.radioButtonTigaHari);
        satuMinggu = (RadioButton) findViewById(R.id.radioButtonsSatuMinggu);
        duaMinggu = (RadioButton) findViewById(R.id.radioButtonDuaMinggu);
        satuBulan = (RadioButton) findViewById(R.id.radioButtonSatuBulan);
        janganPernah = (RadioButton) findViewById(R.id.radioButtonJanganPernah);
        //Tampilan
        bulanIni = (RadioButton) findViewById(R.id.radioButtonBulanIni);
        bulanKemarin = (RadioButton) findViewById(R.id.radioButtonBulanKemarin);
        tahunIni = (RadioButton) findViewById(R.id.radioButtonTahunIni);
        tahunKemarin = (RadioButton) findViewById(R.id.radioButtonTahunKemarin);
        semuaData = (RadioButton) findViewById(R.id.radioButtonSemuaData);
    }

    @Override
    public void onBackPressed(){
        Intent main = new Intent(getBaseContext(), MainActivity.class);
        startActivity(main);
        finish();
    }

    public void bacaPreferences(){
        sharedPref.bacaPreferences(getApplicationContext());
    }

    public void aturUserInterface(){
        aturPengingat();
        aturTampilan();
    }

    public void aturPengingat(){
        if(sharedPref.pengingat.equals(sharedPref.SATU_HARI)){
            satuHari.setChecked(true);
        }else if(sharedPref.pengingat.equals(sharedPref.DUA_HARI)){
            duaHari.setChecked(true);
        }else if(sharedPref.pengingat.equals(sharedPref.TIGA_HARI)){
            tigaHari.setChecked(true);
        }else if(sharedPref.pengingat.equals(sharedPref.SATU_MINGGU)){
            satuMinggu.setChecked(true);
        }else if(sharedPref.pengingat.equals(sharedPref.DUA_MINGGU)){
            duaMinggu.setChecked(true);
        }else if(sharedPref.pengingat.equals(sharedPref.SATU_BULAN)){
            satuBulan.setChecked(true);
        }else if(sharedPref.pengingat.equals(sharedPref.JANGAN_PERNAH)){
            janganPernah.setChecked(true);
        }else{
            satuHari.setChecked(true);
        }
    }

    public void aturTampilan(){
        if(sharedPref.tampilan.equals(sharedPref.BULAN_INI)){
            bulanIni.setChecked(true);
        }else if(sharedPref.tampilan.equals(sharedPref.BULAN_KEMARIN)){
            bulanKemarin.setChecked(true);
        }else if(sharedPref.tampilan.equals(sharedPref.TAHUN_INI)){
            tahunIni.setChecked(true);
        }else if(sharedPref.tampilan.equals(sharedPref.TAHUN_KEMARIN)){
            tahunKemarin.setChecked(true);
        }else if(sharedPref.tampilan.equals(sharedPref.SEMUA_DATA)){
            semuaData.setChecked(true);
        }else{
            bulanIni.setChecked(true);
        }
    }


    public String getTampilanTerpilih(){
        int id = pengaturanTampilan.getCheckedRadioButtonId();
        String terpilih = "";
        if(id == R.id.radioButtonBulanIni){
            terpilih = sharedPref.BULAN_INI;
        }else if(id == R.id.radioButtonBulanKemarin){
            terpilih = sharedPref.BULAN_KEMARIN;
        }else if(id == R.id.radioButtonTahunIni){
            terpilih = sharedPref.TAHUN_INI;
        }else if(id == R.id.radioButtonTahunKemarin){
            terpilih = sharedPref.TAHUN_KEMARIN;
        }else if(id == R.id.radioButtonSemuaData){
            terpilih = sharedPref.SEMUA_DATA;
        }else{
            terpilih = sharedPref.BULAN_INI;
        }
        return terpilih;
    }

    public String getPengingatTerpilih(){
        int id = pengaturanPengingat.getCheckedRadioButtonId();
        String terpilih = "";
        if(id == R.id.radioButtonSatuHari){
            terpilih = sharedPref.SATU_HARI;
        }else if(id == R.id.radioButtonDuaHari){
            terpilih = sharedPref.DUA_HARI;
        }else if(id == R.id.radioButtonTigaHari){
            terpilih = sharedPref.TIGA_HARI;
        }else if(id == R.id.radioButtonsSatuMinggu){
            terpilih = sharedPref.SATU_MINGGU;
        }else if(id == R.id.radioButtonDuaMinggu){
            terpilih = sharedPref.DUA_MINGGU;
        }else if(id == R.id.radioButtonSatuBulan){
            terpilih = sharedPref.SATU_BULAN;
        }else if(id == R.id.radioButtonJanganPernah){
            terpilih = sharedPref.JANGAN_PERNAH;
        }else{
            terpilih = sharedPref.SATU_HARI;
        }
        return terpilih;
    }
}