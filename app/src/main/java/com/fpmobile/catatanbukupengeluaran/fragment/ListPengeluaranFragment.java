package com.fpmobile.catatanbukupengeluaran.fragment;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.amulyakhare.textdrawable.TextDrawable;
import com.fpmobile.catatanbukupengeluaran.R;
import com.fpmobile.catatanbukupengeluaran.database.CreateDatabaseHandler;
import com.fpmobile.catatanbukupengeluaran.model.Pengeluaran;
import com.fpmobile.catatanbukupengeluaran.statik.Global;
import com.fpmobile.catatanbukupengeluaran.statik.Statik;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.Types.BoomType;
import com.nightonke.boommenu.Types.ButtonType;
import com.nightonke.boommenu.Types.PlaceType;
import com.nightonke.boommenu.Util;

import java.util.ArrayList;
import java.util.List;

public class ListPengeluaranFragment extends android.app.Fragment{

    //variabel konstruktor
    private Context context;
    private CreateDatabaseHandler db;
    TextView mToolbarTextView;
    
    //penampung variabel
    private List<Integer> listIdPengeluaran = new ArrayList<Integer>();
    private List<String> listJudulPengeluaran = new ArrayList<String>();
    private List<String> listDeskripsiPengeluaran = new ArrayList<String>();
    private List<Long> listJumlahPengeluaran = new ArrayList<Long>();
    private List<Integer> listTanggalPengeluaran = new ArrayList<Integer>();
    private List<Integer> listBulanPengeluaran = new ArrayList<Integer>();
    private List<Integer> listTahunPengeluaran = new ArrayList<Integer>();

    List<Pengeluaran> Pengeluaran;
    Statik waktu = new Statik();

    public ListPengeluaranFragment(){

    }

    public ListPengeluaranFragment(Context context, CreateDatabaseHandler db, TextView mToolbarTextView){
        this.context = context;
        this.db = db;
        this.mToolbarTextView = mToolbarTextView;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_list_view, container, false);

        //set fragment aktif
        Global lokasiFragment =  Global.getInstance();
        lokasiFragment.setLokasiFragmentAktif(lokasiFragment.listPengeluaranFragment);

        ((ListView)rootView.findViewById(R.id.list_view)).setAdapter(new MyAdapter());
        ((ListView)rootView.findViewById(R.id.list_view)).setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        ((BoomMenuButton)view.findViewById(R.id.boom_ham)).boom();
                    }
                });

        Pengeluaran = db.getAllPengeluaran();
        for (Pengeluaran keluar : Pengeluaran) {
            //menampung ke list
            listIdPengeluaran.add(Integer.valueOf(String.valueOf(keluar.getIdPengeluaran())));
            listJudulPengeluaran.add(String.valueOf(keluar.getJudulPengeluaran()));
            listDeskripsiPengeluaran.add(String.valueOf(keluar.getDeskripsiPengeluaran()));
            listJumlahPengeluaran.add(Long.valueOf(String.valueOf(keluar.getJumlahPengeluaran())));
            listTanggalPengeluaran.add(Integer.valueOf(keluar.getTanggalPengeluaran()));
            listBulanPengeluaran.add(Integer.valueOf(keluar.getBulanPengeluaran()));
            listTahunPengeluaran.add(Integer.valueOf(keluar.getTahunPengeluaran()));

            String log = "Id: " + keluar.getIdPengeluaran() + " ,judul: " + keluar.getJudulPengeluaran()
                    + " ,deskripsi: " + keluar.getDeskripsiPengeluaran() + " ,jumlah: " + keluar.getJumlahPengeluaran() + " ,tanggal: "+ keluar.getTanggalPengeluaran()+keluar.getBulanPengeluaran()+keluar.getTahunPengeluaran();
            Log.d("Name: ", log);
        }

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

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return listIdPengeluaran.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, final ViewGroup parent) {

            final ViewHolder viewHolder;
            if (convertView == null) {
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, null);

                viewHolder = new ViewHolder();
                viewHolder.image_ic = (ImageView) convertView.findViewById(R.id.image_ic);
                viewHolder.tv = (TextView) convertView.findViewById(R.id.text_view);
                viewHolder.textJumlah = (TextView) convertView.findViewById(R.id.text_jumlah);
                viewHolder.hamBoomMenuButton = (BoomMenuButton) convertView.findViewById(R.id.boom_ham);

                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            //set list ic
            char[] judul_ic = String.valueOf(listTanggalPengeluaran.get(position)).toCharArray();
            TextDrawable drawable = TextDrawable.builder()
                    .buildRound(String.valueOf(listTanggalPengeluaran.get(position)), Color.parseColor("#ea6153"));
            viewHolder.image_ic.setImageDrawable(drawable);

            //Set list judul
            if(listJudulPengeluaran.get(position).length() >13) {
                viewHolder.tv.setText(listJudulPengeluaran.get(position).substring(0, 13) + "...");
            }else {
                viewHolder.tv.setText(listJudulPengeluaran.get(position));
            }

            //set list jumlah
            long jumlahs;
            String jumlah = "";
            if(listJumlahPengeluaran.get(position)>1000000) {
                jumlahs = listJumlahPengeluaran.get(position) / 1000000;
                jumlah = String.valueOf(jumlahs+ " JT");
            }else if(listJumlahPengeluaran.get(position)>1000){
                jumlahs = listJumlahPengeluaran.get(position) / 1000;
                jumlah = String.valueOf(jumlahs+ " K");
            }else{
                jumlah = String.valueOf(listJumlahPengeluaran.get(position));
            }
            viewHolder.textJumlah.setText(jumlah);

            //set list boom
            final Drawable[] hamSubButtonDrawables = new Drawable[3];
            int[] drawablesResource = new int[]{
                    R.drawable.ic_buka,
                    R.drawable.ic_edit,
                    R.drawable.ic_delete
            };
            for (int i = 0; i < 3; i++)
                hamSubButtonDrawables[i]
                        = ContextCompat.getDrawable(parent.getContext(), drawablesResource[i]);

            final String[] hamSubButtonTexts = new String[]{
                    "Lihat Catatan",
                    "Edit Catatan",
                    "Hapus Catatan"
            };

            final int[][] subButtonColors = new int[3][2];
            subButtonColors[0][1] = Color.parseColor("#39D179");
            subButtonColors[1][1] = Color.parseColor("#E88834");
            subButtonColors[2][1] = Color.parseColor("#E95B4C");


            viewHolder.hamBoomMenuButton.postDelayed(new Runnable() {
                @Override
                public void run() {
                    // Now with Builder, you can init BMB more convenient
                    new BoomMenuButton.Builder()
                            .subButtons(hamSubButtonDrawables, subButtonColors, hamSubButtonTexts)
                            .button(ButtonType.HAM)
                            .boom(BoomType.PARABOLA)
                            .place(PlaceType.HAM_3_1)
                            .subButtonsShadow(Util.getInstance().dp2px(2), Util.getInstance().dp2px(2))
                            .onSubButtonClick(new BoomMenuButton.OnSubButtonClickListener() {
                                @Override
                                public void onClick(int buttonIndex) {
                                   // Toast.makeText(
                                     //       parent.getContext(),
                                       //     "On click " + hamSubButtonTexts[buttonIndex] + position,
                                         //   Toast.LENGTH_SHORT).show();

                                    String tanggalFix =
                                            String.valueOf(listTanggalPengeluaran.get(position))
                                                    + " "
                                                    + waktu.getNamaBulan(listBulanPengeluaran.get(position))
                                                    + " "
                                                    + listTahunPengeluaran.get(position);

                                    if(buttonIndex ==0){
                                        Global lokasiFragment =  Global.getInstance();
                                        lokasiFragment.setLokasiFragmentAktif(lokasiFragment.lihatPengeluaranFragment);
                                        replaceFragment(new LihatFragment(
                                                context,
                                                db,
                                                listJudulPengeluaran.get(position),
                                                String.valueOf(listJumlahPengeluaran.get(position)),
                                                tanggalFix,
                                                listDeskripsiPengeluaran.get(position),
                                                mToolbarTextView,
                                                "pengeluaran"
                                        ));
                                    }

                                    if(buttonIndex ==1){
                                        Global lokasiFragment =  Global.getInstance();
                                        lokasiFragment.setLokasiFragmentAktif(lokasiFragment.editPengeluaranFragment);
                                        replaceFragment(new EditFragment(
                                                mToolbarTextView,
                                                context,
                                                db,
                                                listJudulPengeluaran.get(position),
                                                String.valueOf(listJumlahPengeluaran.get(position)),
                                                listDeskripsiPengeluaran.get(position),
                                                String.valueOf(listTanggalPengeluaran.get(position)),
                                                String.valueOf(listBulanPengeluaran.get(position)),
                                                String.valueOf(listTahunPengeluaran.get(position)),
                                                String.valueOf(listIdPengeluaran.get(position)),
                                                "pengeluaran"
                                        ));
                                    }

                                    if(buttonIndex == 2){
                                        MaterialDialog.Builder builder = new MaterialDialog.Builder(context)
                                                .iconRes(R.mipmap.ic_launcher)
                                                .title("Hapus catatan?")
                                                .content("Catatan yang sudah di hapus akan hilang secara permanen," +
                                                        " apakah anda yakin ingin menghapus catatan terpilih?")
                                                .positiveText("Hapus")
                                                .negativeText("Batal")
                                                .onPositive(new MaterialDialog.SingleButtonCallback() {
                                                    @Override
                                                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                                        // TODO
                                                        db.hapusPengeluaran(String.valueOf(listIdPengeluaran.get(position)));
                                                        replaceFragment(new ListPengeluaranFragment(context, db, mToolbarTextView));
                                                        Toast.makeText(context, "Catatan berhasil di hapus", Toast.LENGTH_SHORT).show();
                                                        dialog.dismiss();
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
                            })
                            .init(viewHolder.hamBoomMenuButton);
                }
            }, 1);

            return convertView;
        }

        class ViewHolder {
            public ImageView image_ic;
            public TextView textJumlah;
            public TextView tv;
            public BoomMenuButton hamBoomMenuButton;
        }
    }

}
