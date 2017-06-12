package com.fpmobile.catatanbukupengeluaran.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.fpmobile.catatanbukupengeluaran.R;
import com.fpmobile.catatanbukupengeluaran.database.CreateDatabaseHandler;
import com.fpmobile.catatanbukupengeluaran.model.Pemasukan;
import com.fpmobile.catatanbukupengeluaran.statik.Global;
import com.fpmobile.catatanbukupengeluaran.statik.Statik;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;

import info.hoang8f.widget.FButton;


public class TambahPemasukanFragment extends android.app.Fragment implements DatePickerDialog.OnDateSetListener {

    private Context context = null;
    private CreateDatabaseHandler db;
    int tanggal, bulan, tahun;

    //UI elemen
    private MaterialEditText judul;
    private MaterialEditText jumlah;
    private MaterialEditText deskripsi;
    private MaterialEditText date;
    private ImageButton show_date;
    private FButton submit;
    TextView mToolBarTextView;

    public TambahPemasukanFragment(){

    }

    Statik waktu = new Statik();

    public TambahPemasukanFragment(Context context, CreateDatabaseHandler db, TextView mToolBarTextView){
        this.context = context;
        this.db = db;
        this.mToolBarTextView = mToolBarTextView;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tambah_pemasukan, container, false);

        //set fragment aktif
        Global lokasiFragment =  Global.getInstance();
        lokasiFragment.setLokasiFragmentAktif(lokasiFragment.tambahPemasukanFragment);

        //init komponen
        judul = (MaterialEditText) rootView.findViewById(R.id.judul_pemasukan);
        jumlah = (MaterialEditText) rootView.findViewById(R.id.jumlah_pemasukan);
        deskripsi = (MaterialEditText) rootView.findViewById(R.id.deskripsi_pemasukan);
        date = (MaterialEditText) rootView.findViewById(R.id.tanggal_pemasukan);
        show_date = (ImageButton) rootView.findViewById(R.id.show_tanggal);
        submit = (FButton) rootView.findViewById(R.id.submit_pemasukan);

        //Set defaul
        date.setFocusable(false);
        date.setEnabled(false);
        date.setText(String.valueOf(waktu.getHari())+ " " + waktu.getNamaBulan(waktu.getBulan())+ " " + String.valueOf(waktu.getTahun()));
        tanggal = waktu.getHari();
        bulan = waktu.getBulan();
        tahun = waktu.getTahun();


        show_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar now = Calendar.getInstance();
                DatePickerDialog dpd = DatePickerDialog.newInstance(
                        TambahPemasukanFragment.this,
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH)

                );
                dpd.setOkText("Ok");
                dpd.setCancelText("Batal");
                dpd.setMaxDate(now);
                dpd.show(getFragmentManager(), "Atur date");
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(kalkulasiValue()){
                    db.tambahPemasukan(new Pemasukan(judul.getText().toString(), deskripsi.getText().toString(), jumlah.getText().toString(), tanggal, bulan, tahun));
                    Toast.makeText(context, "Catatan pemasukan disimpan", Toast.LENGTH_LONG).show();
                    mToolBarTextView.setText(new Statik().JUDUL_LIST_PEMASUKAN);
                    replaceFragment(new ListPemasukanFragment(context, db, mToolBarTextView));
                }
            }
        });

        return rootView;
    }

    protected void replaceFragment(android.app.Fragment fragment){
        // Create new fragment and transaction

        android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack if needed
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();
    }


    public boolean kalkulasiValue(){
        boolean status = true;
        //Judul
        if (judul.getText().toString().trim().equalsIgnoreCase("")) {
            judul.setError("Kolom tidak boleh kosong");
            status = false;
        }else if(judul.getText().length() > 30){
            judul.setError("Judul max 30 karakter");
            status = false;
        }
        //Jumlah
        if (jumlah.getText().toString().trim().equalsIgnoreCase("")) {
            jumlah.setError("Kolom tidak boleh kosong");
            status = false;
        }else if(Long.valueOf(jumlah.getText().toString()) > 1000000000){
            jumlah.setError("Max 1 Milyar");
            status = false;
        }
        //Deskripsi
        if (deskripsi.getText().toString().trim().equalsIgnoreCase("")) {
            deskripsi.setError("Kolom tidak boleh kosong");
            status = false;
        }
        return  status;
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        date.setText(String.valueOf(dayOfMonth)+ " " + waktu.getNamaBulan(monthOfYear+1)+ " " + String.valueOf(year));
        tanggal = dayOfMonth;
        bulan = monthOfYear+1;
        tahun = year;
    }


}
