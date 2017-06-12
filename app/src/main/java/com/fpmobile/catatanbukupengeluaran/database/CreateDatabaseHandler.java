package com.fpmobile.catatanbukupengeluaran.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.fpmobile.catatanbukupengeluaran.model.Pemasukan;
import com.fpmobile.catatanbukupengeluaran.model.Pengeluaran;
import com.fpmobile.catatanbukupengeluaran.statik.Statik;

import java.util.ArrayList;
import java.util.List;


public class CreateDatabaseHandler extends SQLiteOpenHelper {
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "finansial";

    // Database Table
    private static final String TABEL_PEMASUKAN = "pemasukan";
    private static final String TABEL_PENGELUARAN = "pengeluaran";

    // Kolom tabel pemasukan
    private static final String ID_PEMASUKAN = "id_pemasukan";
    private static final String JUDUL_PEMASUKAN = "judul_pemasukan";
    private static final String DESKRIPSI_PEMASUKAN = "deskripsi_pemasukan";
    private static final String JUMLAH_PEMASUKAN = "jumlah_pemasukan";
    private static final String TANGGAL_PEMASUKAN = "tanggal_pemasukan";
    private static final String BULAN_PEMASUKAN = "bulan_pemasukan";
    private static final String TAHUN_PEMASUKAN = "tahun_pemasukan";


    // Kolom tabel pengeluaran
    private static final String ID_PENGELUARAN = "id_pengeluaran";
    private static final String JUDUL_PENGELUARAN = "judul_pengeluaran";
    private static final String DESKRIPSI_PENGELUARAN = "deskripsi_pengeluaran";
    private static final String JUMLAH_PENGELUARAN = "jumlah_pengeluaran";
    private static final String TANGGAL_PENGELUARAN = "tanggal_pengeluaran";
    private static final String BULAN_PENGELUARAN = "bulan_pengeluaran";
    private static final String TAHUN_PENGELUARAN = "tahun_pengeluaran";

    public CreateDatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PEMASUKAN_TABLE = "CREATE TABLE " + TABEL_PEMASUKAN + "("
                + ID_PEMASUKAN + " INTEGER PRIMARY KEY," + JUDUL_PEMASUKAN + " TEXT,"
                + DESKRIPSI_PEMASUKAN + " TEXT,"+ JUMLAH_PEMASUKAN + " TEXT," + TANGGAL_PEMASUKAN + " INTEGER,"
                + BULAN_PEMASUKAN + " INTEGER," + TAHUN_PEMASUKAN + " INTEGER"
                + ")";
        db.execSQL(CREATE_PEMASUKAN_TABLE);

        String CREATE_PENGELUARAN_TABLE = "CREATE TABLE " + TABEL_PENGELUARAN + "("
                + ID_PENGELUARAN + " INTEGER PRIMARY KEY," + JUDUL_PENGELUARAN + " TEXT,"
                + DESKRIPSI_PENGELUARAN + " TEXT,"+ JUMLAH_PENGELUARAN + " TEXT,"+ TANGGAL_PENGELUARAN + " INTEGER,"
                + BULAN_PENGELUARAN + " INTEGER," + TAHUN_PENGELUARAN + " INTEGER"
                + ")";
        db.execSQL(CREATE_PENGELUARAN_TABLE);
    }


    //Fungsi tabel pemasukan
    public void tambahPemasukan(Pemasukan pemasukan) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(JUDUL_PEMASUKAN, pemasukan.getJudulPemasukan());
        values.put(DESKRIPSI_PEMASUKAN, pemasukan.getDeskripsiPemasukan());
        values.put(JUMLAH_PEMASUKAN, pemasukan.getJumlahPemasukan());

        values.put(TANGGAL_PEMASUKAN, pemasukan.getTanggalPemasukan());
        values.put(BULAN_PEMASUKAN,  pemasukan.getBulanPemasukan());
        values.put(TAHUN_PEMASUKAN, pemasukan.getTahunPemasukan());

        // Inserting Row
        db.insert(TABEL_PEMASUKAN, null, values);
        db.close(); // Closing database connection
    }

    public void hapusPemasukan(String id_pemasukan){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABEL_PEMASUKAN, ID_PEMASUKAN + "=" + id_pemasukan, null);
        db.close();
    }

    public void updatePemasukan(String id_pemasukan, String judul_pemasukan, String jumlahpemasukan, String deskripsiPemasukan, String tanggalPemasukan, String bulanPemasukan, String tahunPemasukan){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues data=new ContentValues();
        data.put(JUDUL_PEMASUKAN, judul_pemasukan);
        data.put(JUMLAH_PEMASUKAN, jumlahpemasukan);
        data.put(DESKRIPSI_PEMASUKAN, deskripsiPemasukan);
        data.put(TANGGAL_PEMASUKAN, tanggalPemasukan);
        data.put(BULAN_PEMASUKAN, bulanPemasukan);
        data.put(TAHUN_PEMASUKAN, tahunPemasukan);

        db.update(TABEL_PEMASUKAN, data, ID_PEMASUKAN + "=" + id_pemasukan, null);
        db.close();
    }

    public List<Pemasukan> getTahunIniPemasukans() {
        List<Pemasukan> PemasukanList = new ArrayList<>();
        // Select All Query
        Statik waktu = new Statik();
        String selectQuery = "SELECT  * FROM " + TABEL_PEMASUKAN + " WHERE " + TAHUN_PEMASUKAN + " = " + String.valueOf(waktu.getTahun());

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Pemasukan pemasukan = new Pemasukan();
                pemasukan.setIdPemasukan(Integer.parseInt(cursor.getString(0)));
                pemasukan.setJudulPemasukan(cursor.getString(1));
                pemasukan.setDeskripsiPemasukan(cursor.getString(2));
                pemasukan.setJumlahPemasukan(cursor.getString(3));

                pemasukan.setTanggalPemasukan(Integer.parseInt(cursor.getString(4)));
                pemasukan.setBulanPemasukan(Integer.parseInt(cursor.getString(5)));
                pemasukan.setTahunPemasukan(Integer.parseInt(cursor.getString(6)));
                // Adding ListPemasukanFragment to list
                PemasukanList.add(pemasukan);
            } while (cursor.moveToNext());
        }
        cursor.close();
        // return ListPemasukanFragment list
        return PemasukanList;
    }

    public List<Pemasukan> getTahunKemarinPemasukans() {
        List<Pemasukan> PemasukanList = new ArrayList<>();
        // Select All Query
        Statik waktu = new Statik();
        String selectQuery = "SELECT  * FROM " + TABEL_PEMASUKAN + " WHERE " + TAHUN_PEMASUKAN + " = " + String.valueOf(waktu.getTahun()-1);

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Pemasukan pemasukan = new Pemasukan();
                pemasukan.setIdPemasukan(Integer.parseInt(cursor.getString(0)));
                pemasukan.setJudulPemasukan(cursor.getString(1));
                pemasukan.setDeskripsiPemasukan(cursor.getString(2));
                pemasukan.setJumlahPemasukan(cursor.getString(3));

                pemasukan.setTanggalPemasukan(Integer.parseInt(cursor.getString(4)));
                pemasukan.setBulanPemasukan(Integer.parseInt(cursor.getString(5)));
                pemasukan.setTahunPemasukan(Integer.parseInt(cursor.getString(6)));
                // Adding ListPemasukanFragment to list
                PemasukanList.add(pemasukan);
            } while (cursor.moveToNext());
        }
        cursor.close();
        // return ListPemasukanFragment list
        return PemasukanList;
    }


    public List<Pemasukan> getBulanIniPemasukans() {
        List<Pemasukan> PemasukanList = new ArrayList<>();
        // Select All Query
        Statik waktu = new Statik();
        String selectQuery = "SELECT  * FROM " + TABEL_PEMASUKAN + " WHERE " + BULAN_PEMASUKAN + " = " + String.valueOf(waktu.getBulan());

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Pemasukan pemasukan = new Pemasukan();
                pemasukan.setIdPemasukan(Integer.parseInt(cursor.getString(0)));
                pemasukan.setJudulPemasukan(cursor.getString(1));
                pemasukan.setDeskripsiPemasukan(cursor.getString(2));
                pemasukan.setJumlahPemasukan(cursor.getString(3));

                pemasukan.setTanggalPemasukan(Integer.parseInt(cursor.getString(4)));
                pemasukan.setBulanPemasukan(Integer.parseInt(cursor.getString(5)));
                pemasukan.setTahunPemasukan(Integer.parseInt(cursor.getString(6)));
                // Adding ListPemasukanFragment to list
                PemasukanList.add(pemasukan);
            } while (cursor.moveToNext());
        }
        cursor.close();
        // return ListPemasukanFragment list
        return PemasukanList;
    }

    public List<Pemasukan> getBulanKemarinPemasukans() {
        List<Pemasukan> PemasukanList = new ArrayList<>();
        // Select All Query
        Statik waktu = new Statik();
        String selectQuery = "SELECT  * FROM " + TABEL_PEMASUKAN + " WHERE " + BULAN_PEMASUKAN + " = " + String.valueOf(waktu.getBulan()-1);

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Pemasukan pemasukan = new Pemasukan();
                pemasukan.setIdPemasukan(Integer.parseInt(cursor.getString(0)));
                pemasukan.setJudulPemasukan(cursor.getString(1));
                pemasukan.setDeskripsiPemasukan(cursor.getString(2));
                pemasukan.setJumlahPemasukan(cursor.getString(3));

                pemasukan.setTanggalPemasukan(Integer.parseInt(cursor.getString(4)));
                pemasukan.setBulanPemasukan(Integer.parseInt(cursor.getString(5)));
                pemasukan.setTahunPemasukan(Integer.parseInt(cursor.getString(6)));
                // Adding ListPemasukanFragment to list
                PemasukanList.add(pemasukan);
            } while (cursor.moveToNext());
        }

        cursor.close();
        // return ListPemasukanFragment list
        return PemasukanList;
    }

    public List<Pemasukan> getAllPemasukans() {
        List<Pemasukan> PemasukanList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABEL_PEMASUKAN;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Pemasukan pemasukan = new Pemasukan();
                pemasukan.setIdPemasukan(Integer.parseInt(cursor.getString(0)));
                pemasukan.setJudulPemasukan(cursor.getString(1));
                pemasukan.setDeskripsiPemasukan(cursor.getString(2));
                pemasukan.setJumlahPemasukan(cursor.getString(3));

                pemasukan.setTanggalPemasukan(Integer.parseInt(cursor.getString(4)));
                pemasukan.setBulanPemasukan(Integer.parseInt(cursor.getString(5)));
                pemasukan.setTahunPemasukan(Integer.parseInt(cursor.getString(6)));
                // Adding ListPemasukanFragment to list
                PemasukanList.add(pemasukan);
            } while (cursor.moveToNext());
        }

        cursor.close();
        // return ListPemasukanFragment list
        return PemasukanList;
    }

    //Fungsi tabel pengeluaran
    public void tambahPengeluaran(Pengeluaran pengeluaran) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(JUDUL_PENGELUARAN, pengeluaran.getJudulPengeluaran());
        values.put(DESKRIPSI_PENGELUARAN, pengeluaran.getDeskripsiPengeluaran());
        values.put(JUMLAH_PENGELUARAN, pengeluaran.getJumlahPengeluaran());
        values.put(TANGGAL_PENGELUARAN, pengeluaran.getTanggalPengeluaran());
        values.put(BULAN_PENGELUARAN, pengeluaran.getBulanPengeluaran());
        values.put(TAHUN_PENGELUARAN, pengeluaran.getTahunPengeluaran());

        // Inserting Row
        db.insert(TABEL_PENGELUARAN, null, values);
        db.close(); // Closing database connection
    }


    public void hapusPengeluaran(String id_pengeluaran){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABEL_PENGELUARAN, ID_PENGELUARAN + "=" + id_pengeluaran, null);
        db.close();
    }

    public void updatePengeluaran(String id_pengeluaran, String judul_pengeluaran, String jumlahpengeluaran, String deskripsipengeluaran, String tanggalpengeluaran, String bulanpengeluaran, String tahunpengeluaran){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues data=new ContentValues();
        data.put(JUDUL_PENGELUARAN, judul_pengeluaran);
        data.put(JUMLAH_PENGELUARAN, jumlahpengeluaran);
        data.put(DESKRIPSI_PENGELUARAN, deskripsipengeluaran);
        data.put(TANGGAL_PENGELUARAN, tanggalpengeluaran);
        data.put(BULAN_PENGELUARAN, bulanpengeluaran);
        data.put(TAHUN_PENGELUARAN, tahunpengeluaran);

        db.update(TABEL_PENGELUARAN, data, ID_PENGELUARAN + "=" + id_pengeluaran, null);
        db.close();
    }


    public List<Pengeluaran> getAllPengeluaran() {
        List<Pengeluaran> PengeluaranList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABEL_PENGELUARAN;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Pengeluaran pengeluaran = new Pengeluaran();
                pengeluaran.setIdPengeluaran(Integer.parseInt(cursor.getString(0)));
                pengeluaran.setJudulPengeluaran(cursor.getString(1));
                pengeluaran.setDeskripsiPengeluaran(cursor.getString(2));
                pengeluaran.setJumlahPengeluaran(cursor.getString(3));

                pengeluaran.setTanggalPengeluaran(Integer.parseInt(cursor.getString(4)));
                pengeluaran.setBulanPengeluaran(Integer.parseInt(cursor.getString(5)));
                pengeluaran.setTahunPengeluaran(Integer.parseInt(cursor.getString(6)));
                // Adding ListPemasukanFragment to list
                PengeluaranList.add(pengeluaran);
            } while (cursor.moveToNext());
        }

        cursor.close();
        // return ListPemasukanFragment list
        return PengeluaranList;
    }

    public List<Pengeluaran> getBulanIniPengeluaran() {
        List<Pengeluaran> PengeluaranList = new ArrayList<>();
        // Select All Query
        Statik waktu = new Statik();
        String selectQuery = "SELECT  * FROM " + TABEL_PENGELUARAN + " WHERE " + BULAN_PENGELUARAN + " = " + String.valueOf(waktu.getBulan());

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Pengeluaran pengeluaran = new Pengeluaran();
                pengeluaran.setIdPengeluaran(Integer.parseInt(cursor.getString(0)));
                pengeluaran.setJudulPengeluaran(cursor.getString(1));
                pengeluaran.setDeskripsiPengeluaran(cursor.getString(2));
                pengeluaran.setJumlahPengeluaran(cursor.getString(3));

                pengeluaran.setTanggalPengeluaran(Integer.parseInt(cursor.getString(4)));
                pengeluaran.setBulanPengeluaran(Integer.parseInt(cursor.getString(5)));
                pengeluaran.setTahunPengeluaran(Integer.parseInt(cursor.getString(6)));
                // Adding ListPemasukanFragment to list
                PengeluaranList.add(pengeluaran);
            } while (cursor.moveToNext());
        }

        cursor.close();
        // return ListPemasukanFragment list
        return PengeluaranList;
    }

    public List<Pengeluaran> getBulanKemarinPengeluaran() {
        List<com.fpmobile.catatanbukupengeluaran.model.Pengeluaran> PengeluaranList = new ArrayList<>();
        // Select All Query
        Statik waktu = new Statik();
        String selectQuery = "SELECT  * FROM " + TABEL_PENGELUARAN + " WHERE " + BULAN_PENGELUARAN + " = " + String.valueOf(waktu.getBulan()-1);

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                com.fpmobile.catatanbukupengeluaran.model.Pengeluaran pengeluaran = new com.fpmobile.catatanbukupengeluaran.model.Pengeluaran();
                pengeluaran.setIdPengeluaran(Integer.parseInt(cursor.getString(0)));
                pengeluaran.setJudulPengeluaran(cursor.getString(1));
                pengeluaran.setDeskripsiPengeluaran(cursor.getString(2));
                pengeluaran.setJumlahPengeluaran(cursor.getString(3));

                pengeluaran.setTanggalPengeluaran(Integer.parseInt(cursor.getString(4)));
                pengeluaran.setBulanPengeluaran(Integer.parseInt(cursor.getString(5)));
                pengeluaran.setTahunPengeluaran(Integer.parseInt(cursor.getString(6)));
                // Adding ListPemasukanFragment to list
                PengeluaranList.add(pengeluaran);
            } while (cursor.moveToNext());
        }

        cursor.close();
        // return ListPemasukanFragment list
        return PengeluaranList;
    }

    public List<Pengeluaran> getTahunIniPengeluaran() {
        List<com.fpmobile.catatanbukupengeluaran.model.Pengeluaran> PengeluaranList = new ArrayList<>();
        // Select All Query
        Statik waktu = new Statik();
        String selectQuery = "SELECT  * FROM " + TABEL_PENGELUARAN + " WHERE " + TAHUN_PENGELUARAN + " = " + String.valueOf(waktu.getTahun());

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                com.fpmobile.catatanbukupengeluaran.model.Pengeluaran pengeluaran = new com.fpmobile.catatanbukupengeluaran.model.Pengeluaran();
                pengeluaran.setIdPengeluaran(Integer.parseInt(cursor.getString(0)));
                pengeluaran.setJudulPengeluaran(cursor.getString(1));
                pengeluaran.setDeskripsiPengeluaran(cursor.getString(2));
                pengeluaran.setJumlahPengeluaran(cursor.getString(3));

                pengeluaran.setTanggalPengeluaran(Integer.parseInt(cursor.getString(4)));
                pengeluaran.setBulanPengeluaran(Integer.parseInt(cursor.getString(5)));
                pengeluaran.setTahunPengeluaran(Integer.parseInt(cursor.getString(6)));
                // Adding ListPemasukanFragment to list
                PengeluaranList.add(pengeluaran);
            } while (cursor.moveToNext());
        }

        cursor.close();
        // return ListPemasukanFragment list
        return PengeluaranList;
    }

    public List<Pengeluaran> getTahunKemarinPengeluaran() {
        List<com.fpmobile.catatanbukupengeluaran.model.Pengeluaran> PengeluaranList = new ArrayList<>();
        // Select All Query
        Statik waktu = new Statik();
        String selectQuery = "SELECT  * FROM " + TABEL_PENGELUARAN + " WHERE " + TAHUN_PENGELUARAN + " = " + String.valueOf(waktu.getTahun()-1);

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                com.fpmobile.catatanbukupengeluaran.model.Pengeluaran pengeluaran = new com.fpmobile.catatanbukupengeluaran.model.Pengeluaran();
                pengeluaran.setIdPengeluaran(Integer.parseInt(cursor.getString(0)));
                pengeluaran.setJudulPengeluaran(cursor.getString(1));
                pengeluaran.setDeskripsiPengeluaran(cursor.getString(2));
                pengeluaran.setJumlahPengeluaran(cursor.getString(3));

                pengeluaran.setTanggalPengeluaran(Integer.parseInt(cursor.getString(4)));
                pengeluaran.setBulanPengeluaran(Integer.parseInt(cursor.getString(5)));
                pengeluaran.setTahunPengeluaran(Integer.parseInt(cursor.getString(6)));
                // Adding ListPemasukanFragment to list
                PengeluaranList.add(pengeluaran);
            } while (cursor.moveToNext());
        }

        cursor.close();
        // return ListPemasukanFragment list
        return PengeluaranList;
    }
    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABEL_PEMASUKAN);
        db.execSQL("DROP TABLE IF EXISTS " + TABEL_PENGELUARAN);

        // Create tables again
        onCreate(db);
    }

}
