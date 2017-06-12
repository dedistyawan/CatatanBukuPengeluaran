package com.fpmobile.catatanbukupengeluaran.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fpmobile.catatanbukupengeluaran.R;
import com.fpmobile.catatanbukupengeluaran.database.CreateDatabaseHandler;
import com.fpmobile.catatanbukupengeluaran.model.Pemasukan;
import com.fpmobile.catatanbukupengeluaran.model.Pengeluaran;
import com.fpmobile.catatanbukupengeluaran.statik.Global;
import com.fpmobile.catatanbukupengeluaran.statik.Preferences;
import com.fpmobile.catatanbukupengeluaran.statik.Statik;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.util.List;


public class StatistikLaporanFragment extends android.app.Fragment {

    private CreateDatabaseHandler db;
    List<Pemasukan> Pemasukan;
    List<Pengeluaran> Pengeluaran;
    private long jumlahMasukan = 0;
    private long jumlahKeluaran = 0;
    private long selisih = 0;

    private long jumlahMasukanPerHari = 0;
    private long jumlahPengeluaranPerHari = 0;
    private long jumlahTabunganPerHari = 0;

    //UI deklaration
    TextView angkaPengeluaran;
    TextView angkaPemasukan;
    TextView angkaSelisih;
    TextView judulSelisih;
    TextView infoLaporan;
    LinearLayout pembungkusSelisih;

    TextView penghasilanPerHari;
    TextView pengeluaranPerHari;
    TextView tabunganPerHari;

    Statik styleRupiah = new Statik();
    Preferences preferences = new Preferences();
    private Context context;

    public StatistikLaporanFragment(){

    }

    public StatistikLaporanFragment(CreateDatabaseHandler db, Context context){
        this.db = db;
        this.context = context;
    }

    String pesanAwal = "Berikut ini adalah data Laporan berdasarkan rentang waktu: <b>";
    String pesanAhir = "</b>. Anda dapat mengubah rentang waktu laporan pada bagian pengaturan";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.statistik, container, false);

        //UI inisialisasi
        angkaPengeluaran = (TextView) rootView.findViewById(R.id.angka_pengeluaran);
        angkaPemasukan = (TextView) rootView.findViewById(R.id.angka_pemasukan);
        angkaSelisih = (TextView) rootView.findViewById(R.id.angka_selisih);
        judulSelisih = (TextView) rootView.findViewById(R.id.judul_selisih);
        pembungkusSelisih = (LinearLayout) rootView.findViewById(R.id.pembungkus_selisih);
        infoLaporan = (TextView) rootView.findViewById(R.id.textviewInfoLaporan);

        penghasilanPerHari = (TextView) rootView.findViewById(R.id.penghasilanPerHari);
        pengeluaranPerHari = (TextView) rootView.findViewById(R.id.pengeluaranPerHari);
        tabunganPerHari = (TextView) rootView.findViewById(R.id.tabunganPerhari);

        //Membaca preferences
        preferences.bacaPreferences(context);
        infoLaporan.setText(Html.fromHtml(pesanAwal + preferences.tampilan + pesanAhir));

        //set fragment aktif
        Global lokasiFragment =  Global.getInstance();
        lokasiFragment.setLokasiFragmentAktif(lokasiFragment.statistikLaporanFragment);

        PieChart mPieChart = (PieChart) rootView.findViewById(R.id.piechart);

        bacaDatabase();
        //Detail laporan
        penghasilanPerHari.setText(penghasilanPerHari.getText() + styleRupiah.getStyleRupiah(jumlahMasukanPerHari));
        pengeluaranPerHari.setText(pengeluaranPerHari.getText() + styleRupiah.getStyleRupiah(jumlahPengeluaranPerHari));
        tabunganPerHari.setText(tabunganPerHari.getText() + styleRupiah.getStyleRupiah(jumlahTabunganPerHari));



        angkaPemasukan.setText("Rp. "+styleRupiah.getStyleRupiah(jumlahMasukan));
        angkaPengeluaran.setText("Rp. "+styleRupiah.getStyleRupiah(jumlahKeluaran));

        if(selisih<0) {
            angkaSelisih.setText("Rp. " + styleRupiah.getStyleRupiah(selisih));
            judulSelisih.setText("Saldo Terutang");
            pembungkusSelisih.setBackgroundColor(Color.parseColor("#E98B39"));
        }else{
            angkaSelisih.setText("Rp. " + styleRupiah.getStyleRupiah(selisih));
            judulSelisih.setText("Saldo Tabungan");
            pembungkusSelisih.setBackgroundColor(Color.parseColor("#F2CA27"));
        }


        //Meminimalisir
        jumlahMasukan /= 1000;
        jumlahKeluaran /= 1000;
        selisih /= 1000;

        mPieChart.addPieSlice(new PieModel("Total Penghasilan (K)", Float.parseFloat(String.valueOf(jumlahMasukan)), Color.parseColor("#40D47E")));
        mPieChart.addPieSlice(new PieModel("Total Pengeluaran (K)", Float.parseFloat(String.valueOf(jumlahKeluaran)), Color.parseColor("#EA6153")));
        if(selisih<0){
            selisih = selisih * -1;
            mPieChart.addPieSlice(new PieModel("Saldo (Minus / Hutang) (K)", Float.parseFloat(String.valueOf(selisih)), Color.parseColor("#E98B39")));
        }else{
           mPieChart.addPieSlice(new PieModel("Saldo (Surplus / Tabungan) (K)", Float.parseFloat(String.valueOf(selisih)), Color.parseColor("#F2CA27")));
        }

        mPieChart.startAnimation();



        return rootView;
    }


    public void kalkulasiNilai(){
        for (Pemasukan masuk : Pemasukan) {
            jumlahMasukan += Long.valueOf(String.valueOf(masuk.getJumlahPemasukan()));
        }

        for (Pengeluaran keluar : Pengeluaran) {
            jumlahKeluaran += Long.valueOf(String.valueOf(keluar.getJumlahPengeluaran()));
        }

        selisih = jumlahMasukan - jumlahKeluaran;
    }

    public void bacaDatabase(){
        //membaca database masukan
        if(preferences.tampilan.equals(preferences.BULAN_INI)) {
            Pemasukan = db.getBulanIniPemasukans();
            Pengeluaran = db.getBulanIniPengeluaran();
            kalkulasiNilai();
            //Detail laporan
            jumlahMasukanPerHari = jumlahMasukan/30;
            jumlahPengeluaranPerHari = jumlahKeluaran/30;
            jumlahTabunganPerHari = selisih/30;
        }else if(preferences.tampilan.equals(preferences.BULAN_KEMARIN)){
            Pemasukan = db.getBulanKemarinPemasukans();
            Pengeluaran = db.getBulanKemarinPengeluaran();
            kalkulasiNilai();
            //Detail laporan
            jumlahMasukanPerHari = jumlahMasukan/30;
            jumlahPengeluaranPerHari = jumlahKeluaran/30;
            jumlahTabunganPerHari = selisih/30;
        }else if(preferences.tampilan.equals(preferences.TAHUN_INI)){
            Pemasukan = db.getTahunIniPemasukans();
            Pengeluaran = db.getTahunIniPengeluaran();
            kalkulasiNilai();
            //Detail laporan
            jumlahMasukanPerHari = jumlahMasukan/360;
            jumlahPengeluaranPerHari = jumlahKeluaran/360;
            jumlahTabunganPerHari = selisih/360;
        }else if(preferences.tampilan.equals(preferences.TAHUN_KEMARIN)){
            Pemasukan = db.getTahunKemarinPemasukans();
            Pengeluaran = db.getTahunKemarinPengeluaran();
            kalkulasiNilai();
            //Detail laporan
            jumlahMasukanPerHari = jumlahMasukan/360;
            jumlahPengeluaranPerHari = jumlahKeluaran/360;
            jumlahTabunganPerHari = selisih/360;
        }else if(preferences.tampilan.equals(preferences.SEMUA_DATA)){
            Pemasukan = db.getAllPemasukans();
            Pengeluaran = db.getAllPengeluaran();
            kalkulasiNilai();
            //Detail laporan
            jumlahMasukanPerHari = jumlahMasukan/Pemasukan.size();
            jumlahPengeluaranPerHari = jumlahKeluaran/Pemasukan.size();
            jumlahTabunganPerHari = selisih/Pemasukan.size();
        }

    }

}

