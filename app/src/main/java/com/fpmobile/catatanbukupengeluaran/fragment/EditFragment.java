package com.fpmobile.catatanbukupengeluaran.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.fpmobile.catatanbukupengeluaran.R;
import com.fpmobile.catatanbukupengeluaran.database.CreateDatabaseHandler;
import com.fpmobile.catatanbukupengeluaran.statik.Statik;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;

import info.hoang8f.widget.FButton;


public class EditFragment extends android.app.Fragment implements DatePickerDialog.OnDateSetListener{

    private Context context = null;
    private CreateDatabaseHandler db;
    private String id_pemasukan;
    private String judul;
    private String nominal;
    private String deskripsi;
    private String tanggal;
    private String bulan;
    private String tahun;

    //UI elemen
    private MaterialEditText edit_judul;
    private MaterialEditText edit_nominal;
    private MaterialEditText edit_deskripsi;
    private MaterialEditText edit_date;
    private ImageButton edit_show_date;
    private FButton edit_submit;
    TextView mToolBarTextView;

    private String diBukadari;

    Statik waktu = new Statik();
    Statik getStyleRupiah = new Statik();


    public EditFragment(){

    }

    public EditFragment(TextView mToolBarTextView, Context context, CreateDatabaseHandler db, String judul, String nominal, String deskripsi, String tanggal, String bulan, String tahun, String id_pemasukan, String diBukadari){
        this.context = context;
        this.db = db;
        this.judul = judul;
        this.nominal = nominal;
        this.tanggal = tanggal;
        this.deskripsi = deskripsi;
        this.bulan = bulan;
        this.tahun = tahun;
        this.id_pemasukan = id_pemasukan;
        this.mToolBarTextView = mToolBarTextView;
        this.diBukadari = diBukadari;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.edit_fragment, container, false);


        if(diBukadari.equals("pemasukan")){
            mToolBarTextView.setText(Statik.JUDUL_EDIT_PEMASUKAN);
        }else {
            mToolBarTextView.setText(Statik.JUDUL_EDIT_PENGELUARAN);
        }


        //inisialisasi UI
        edit_judul = (MaterialEditText) rootView.findViewById(R.id.edit_judul);
        edit_nominal = (MaterialEditText) rootView.findViewById(R.id.edit_jumlah);
        edit_deskripsi = (MaterialEditText) rootView.findViewById(R.id.edit_deskripsi);
        edit_date = (MaterialEditText) rootView.findViewById(R.id.edit_tanggal);
        edit_show_date = (ImageButton) rootView.findViewById(R.id.edit_show_tanggal);
        edit_submit = (FButton) rootView.findViewById(R.id.edit_submit);

        //set default
        edit_judul.setText(judul);
        edit_nominal.setText(nominal);
        edit_deskripsi.setText(deskripsi);

        //set tanggal bulan tahun
        String tanggalFix = tanggal + " " + waktu.getNamaBulan(Integer.parseInt(bulan)) + " " + tahun;
        edit_date.setText(tanggalFix);
        edit_date.setFocusable(false);
        edit_date.setEnabled(false);


        //set onClickListener
        edit_show_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar now = Calendar.getInstance();
                DatePickerDialog dpd = DatePickerDialog.newInstance(
                        EditFragment.this,
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

        edit_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(kalkulasiValue()){

                    MaterialDialog.Builder builder = new MaterialDialog.Builder(context)
                            .iconRes(R.mipmap.ic_launcher)
                            .title("Konfirmasi")
                            .content("Catatan terpilih akan diperbarui, anda yakin?")
                            .positiveText("Ok")
                            .negativeText("Batal")
                            .onPositive(new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                    if(diBukadari.equals("pemasukan")) {
                                        db.updatePemasukan(
                                                id_pemasukan,
                                                edit_judul.getText().toString(),
                                                edit_nominal.getText().toString(),
                                                edit_deskripsi.getText().toString(),
                                                tanggal,
                                                bulan,
                                                tahun
                                        );
                                        replaceFragment(new ListPemasukanFragment(context, db, mToolBarTextView));
                                    }
                                    if(diBukadari.equals("pengeluaran")) {
                                        db.updatePengeluaran(
                                                id_pemasukan,
                                                edit_judul.getText().toString(),
                                                edit_nominal.getText().toString(),
                                                edit_deskripsi.getText().toString(),
                                                tanggal,
                                                bulan,
                                                tahun
                                        );
                                        replaceFragment(new ListPengeluaranFragment(context, db, mToolBarTextView));
                                    }
                                    Toast.makeText(context, "Catatan berhasil diperbarui", Toast.LENGTH_LONG).show();
                                }
                            })
                            .onNegative(new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                    // TODO
                                    dialog.dismiss();
                                }
                            });

                    MaterialDialog dialog = builder.build();
                    dialog.show();
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
        if (edit_judul.getText().toString().trim().equalsIgnoreCase("")) {
            edit_judul.setError("Kolom tidak boleh kosong");
            status = false;
        }else if(edit_judul.getText().length() > 30){
            edit_judul.setError("Judul max 30 karakter");
            status = false;
        }
        //Jumlah
        if (edit_nominal.getText().toString().trim().equalsIgnoreCase("")) {
            edit_nominal.setError("Kolom tidak boleh kosong");
            status = false;
        }else if(Long.valueOf(edit_nominal.getText().toString()) > 1000000000){
            edit_nominal.setError("Max 1 Milyar");
            status = false;
        }
        //Deskripsi
        if (edit_deskripsi.getText().toString().trim().equalsIgnoreCase("")) {
            edit_deskripsi.setError("Kolom tidak boleh kosong");
            status = false;
        }
        return  status;
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        edit_date.setText(String.valueOf(dayOfMonth)+ " " + waktu.getNamaBulan(monthOfYear+1)+ " " + String.valueOf(year));
        tanggal = String.valueOf(dayOfMonth);
        bulan = String.valueOf(monthOfYear+1);
        tahun = String.valueOf(year);
    }
}
