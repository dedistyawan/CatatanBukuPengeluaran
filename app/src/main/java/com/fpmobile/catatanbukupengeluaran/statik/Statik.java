package com.fpmobile.catatanbukupengeluaran.statik;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Statik {
    //Judul fragment
    public static String JUDUL_STATISTIK = "Statistik Laporan";
    public static String JUDUL_LIST_PEMASUKAN = "Riwayat Penghasilan";
    public static String JUDUL_LIHAT_PEMASUKAN = "Data Penghasilan";
    public static String JUDUL_TAMBAH_PEMASUKAN = "Tambah Penghasilan";
    public static String JUDUL_EDIT_PEMASUKAN = "Ubah Penghasilan";
    public static String JUDUL_LIST_PENGELUARAN = "Riwayat Pengeluaran";
    public static String JUDUL_LIHAT_PENGELUARAN = "Data Pengeluaran";
    public static String JUDUL_EDIT_PENGELUARAN = "Ubah Pengeluaran";
    public static String JUDUL_TAMBAH_PENGELUARAN = "Tambah Pengeluaran";



    public int getTahun(){
        DateFormat dateFormatter = new SimpleDateFormat("yyyy");
        dateFormatter.setLenient(false);
        Date today = new Date();
        int s = Integer.parseInt(dateFormatter.format(today));
        return s;
    }
    public int getBulan(){
        DateFormat dateFormatter = new SimpleDateFormat("MM");
        dateFormatter.setLenient(false);
        Date today = new Date();
        int s =  Integer.parseInt(dateFormatter.format(today));
        return s;
    }
    public int getHari(){
        DateFormat dateFormatter = new SimpleDateFormat("dd");
        dateFormatter.setLenient(false);
        Date today = new Date();
        int s = Integer.parseInt(dateFormatter.format(today));
        return s;
    }
    public int getJam(){
        DateFormat dateFormatter = new SimpleDateFormat("hh");
        dateFormatter.setLenient(false);
        Date today = new Date();
        int s = Integer.parseInt(dateFormatter.format(today));
        return s;
    }
    public int getMenit(){
        DateFormat dateFormatter = new SimpleDateFormat("mm");
        dateFormatter.setLenient(false);
        Date today = new Date();
        int s = Integer.parseInt(dateFormatter.format(today));
        return s;
    }
    public int getDetik(){
        DateFormat dateFormatter = new SimpleDateFormat("ss");
        dateFormatter.setLenient(false);
        Date today = new Date();
        int s = Integer.parseInt(dateFormatter.format(today));
        return s;
    }
    public String getNamaBulan(int bulan){
        String namaBulan = "";
        switch (bulan) {
            case 1:
                namaBulan = "Januari";
                break;
            case 2:
                namaBulan = "Februari";
                break;
            case 3:
                namaBulan = "Maret";
                break;
            case 4:
                namaBulan = "April";
                break;
            case 5:
                namaBulan = "Mei";
                break;
            case 6:
                namaBulan = "Juni";
                break;
            case 7:
                namaBulan = "Juli";
                break;
            case 8:
                namaBulan = "Agustus";
                break;
            case 9:
                namaBulan = "September";
                break;
            case 10:
                namaBulan = "Oktober";
                break;
            case 11:
                namaBulan = "November";
                break;
            case 12:
                namaBulan = "Desember";
                break;
            default:
                namaBulan = "Bulan tidak diketahui";
                break;
        }
        return namaBulan;
    }

    public String getStyleRupiah(long nomer){
        String hasil ="";
        String temp  = String.valueOf(nomer);
        char[] tempArray = temp.toCharArray();

        if(tempArray.length==4){
            //1.000
            hasil += String.valueOf(tempArray[0])
                    +  "."
                    + String.valueOf(tempArray[1])
                    + String.valueOf(tempArray[2])
                    + String.valueOf(tempArray[3]);
        }
        else if(tempArray.length == 5){
            //10.000
            hasil += String.valueOf(tempArray[0])
                    + String.valueOf(tempArray[1])
                    +  "."
                    + String.valueOf(tempArray[2])
                    + String.valueOf(tempArray[3])
                    + String.valueOf(tempArray[4]);
        }
        else if(tempArray.length == 6){
            //100.000
            hasil += String.valueOf(tempArray[0])
                    + String.valueOf(tempArray[1])
                    + String.valueOf(tempArray[2])
                    +  "."
                    + String.valueOf(tempArray[3])
                    + String.valueOf(tempArray[4])
                    + String.valueOf(tempArray[5]);
        }
        else if(tempArray.length == 7){
            //1.000.000
            hasil += String.valueOf(tempArray[0])
                    +  "."
                    + String.valueOf(tempArray[1])
                    + String.valueOf(tempArray[2])
                    + String.valueOf(tempArray[3])
                    +  "."
                    + String.valueOf(tempArray[4])
                    + String.valueOf(tempArray[5])
                    + String.valueOf(tempArray[6]);
        }
        else if(tempArray.length == 8){
            //10.000.000
            hasil += String.valueOf(tempArray[0])
                    + String.valueOf(tempArray[1])
                    +  "."
                    + String.valueOf(tempArray[2])
                    + String.valueOf(tempArray[3])
                    + String.valueOf(tempArray[4])
                    +  "."
                    + String.valueOf(tempArray[5])
                    + String.valueOf(tempArray[6])
                    + String.valueOf(tempArray[7]);
        }
        else if(tempArray.length == 9){
            //100.000.000
            hasil += String.valueOf(tempArray[0])
                    + String.valueOf(tempArray[1])
                    + String.valueOf(tempArray[2])
                    +  "."
                    + String.valueOf(tempArray[3])
                    + String.valueOf(tempArray[4])
                    + String.valueOf(tempArray[5])
                    +  "."
                    + String.valueOf(tempArray[6])
                    + String.valueOf(tempArray[7])
                    + String.valueOf(tempArray[8]);;
        }
        else if(tempArray.length == 10){
            //1.000.000.000
            hasil += String.valueOf(tempArray[0])
                    +  "."
                    + String.valueOf(tempArray[1])
                    + String.valueOf(tempArray[2])
                    + String.valueOf(tempArray[3])
                    +  "."
                    + String.valueOf(tempArray[4])
                    + String.valueOf(tempArray[5])
                    + String.valueOf(tempArray[6])
                    +  "."
                    + String.valueOf(tempArray[7])
                    + String.valueOf(tempArray[8])
                    + String.valueOf(tempArray[9]);
        }
        else if(tempArray.length == 11){
            //10.000.000.000
            hasil += String.valueOf(tempArray[0])
                    + String.valueOf(tempArray[1])
                    +  "."
                    + String.valueOf(tempArray[2])
                    + String.valueOf(tempArray[3])
                    + String.valueOf(tempArray[4])
                    +  "."
                    + String.valueOf(tempArray[5])
                    + String.valueOf(tempArray[6])
                    + String.valueOf(tempArray[7])
                    +  "."
                    + String.valueOf(tempArray[8])
                    + String.valueOf(tempArray[9])
                    + String.valueOf(tempArray[10]);
        }
        else if(tempArray.length == 12){
            //100.000.000.000
            hasil += String.valueOf(tempArray[0])
                    + String.valueOf(tempArray[1])
                    + String.valueOf(tempArray[2])
                    +  "."
                    + String.valueOf(tempArray[3])
                    + String.valueOf(tempArray[4])
                    + String.valueOf(tempArray[5])
                    +  "."
                    + String.valueOf(tempArray[6])
                    + String.valueOf(tempArray[7])
                    + String.valueOf(tempArray[8])
                    +  "."
                    + String.valueOf(tempArray[9])
                    + String.valueOf(tempArray[10])
                    + String.valueOf(tempArray[11]);
        }
        else{
            hasil = temp;
        }
        return hasil;
    }
}
