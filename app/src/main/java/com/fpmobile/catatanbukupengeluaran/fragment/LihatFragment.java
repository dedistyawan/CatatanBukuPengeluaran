package com.fpmobile.catatanbukupengeluaran.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fpmobile.catatanbukupengeluaran.R;
import com.fpmobile.catatanbukupengeluaran.database.CreateDatabaseHandler;
import com.fpmobile.catatanbukupengeluaran.statik.Statik;


public class LihatFragment extends android.app.Fragment {

    private Context context = null;
    private CreateDatabaseHandler db;
    private String judul;
    private String nominal;
    private String tanggal;
    private String deskripsi;

    //UI deklaration
    private TextView judul_text;
    private TextView nominal_text;
    private TextView tanggal_text;
    private TextView deskripsi_text;
    TextView mToolBarTextView;
    private String diBukaDari;

    Statik styleRupiah = new Statik();

    public LihatFragment(Context context, CreateDatabaseHandler db, String judul, String nominal, String tanggal, String deskripsi, TextView mToolBarTextView, String diBukaDari){
        this.context = context;
        this.db = db;
        this.judul = judul;
        this.nominal = nominal;
        this.tanggal = tanggal;
        this.deskripsi = deskripsi;
        this.mToolBarTextView = mToolBarTextView;
        this.diBukaDari = diBukaDari;
    }

    Statik getJudul = new Statik();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_lihat, container, false);

        if(diBukaDari.contentEquals("pemasukan")) {
            mToolBarTextView.setText(getJudul.JUDUL_LIHAT_PEMASUKAN);
        }else{
            mToolBarTextView.setText(getJudul.JUDUL_LIHAT_PENGELUARAN);
        }

        //inisialisasi UI
        judul_text = (TextView) rootView.findViewById(R.id.judul_lihat);
        nominal_text = (TextView) rootView.findViewById(R.id.nominal_lihat);
        tanggal_text = (TextView) rootView.findViewById(R.id.date_lihat);
        deskripsi_text = (TextView) rootView.findViewById(R.id.deskripsi_lihat);

        //Atur nilai UI
        judul_text.setText(judul_text.getText()+ judul);
        nominal_text.setText(nominal_text.getText()+ styleRupiah.getStyleRupiah(Long.valueOf(nominal)));
        tanggal_text.setText(tanggal_text.getText()+ tanggal);
        deskripsi_text.setText(deskripsi_text.getText()+ deskripsi);

        return rootView;
    }

    public LihatFragment(){

    }
}
