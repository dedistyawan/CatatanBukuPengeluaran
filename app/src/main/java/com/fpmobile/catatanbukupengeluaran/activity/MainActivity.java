package com.fpmobile.catatanbukupengeluaran.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.fpmobile.catatanbukupengeluaran.R;
import com.fpmobile.catatanbukupengeluaran.database.CreateDatabaseHandler;
import com.fpmobile.catatanbukupengeluaran.fragment.ListPemasukanFragment;
import com.fpmobile.catatanbukupengeluaran.fragment.ListPengeluaranFragment;
import com.fpmobile.catatanbukupengeluaran.fragment.StatistikLaporanFragment;
import com.fpmobile.catatanbukupengeluaran.fragment.TambahPemasukanFragment;
import com.fpmobile.catatanbukupengeluaran.fragment.TambahPengeluaranFragment;
import com.fpmobile.catatanbukupengeluaran.statik.Global;
import com.fpmobile.catatanbukupengeluaran.statik.Statik;
import com.yalantis.contextmenu.lib.ContextMenuDialogFragment;
import com.yalantis.contextmenu.lib.MenuObject;
import com.yalantis.contextmenu.lib.MenuParams;
import com.yalantis.contextmenu.lib.interfaces.OnMenuItemClickListener;
import com.yalantis.contextmenu.lib.interfaces.OnMenuItemLongClickListener;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements OnMenuItemClickListener, OnMenuItemLongClickListener {
    private FragmentManager fragmentManager;
    private ContextMenuDialogFragment mMenuDialogFragment;
    final Context context = this;
    CreateDatabaseHandler db;
    Statik getJudul = new Statik();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Membuat database
        db = new CreateDatabaseHandler(context);

        fragmentManager = getSupportFragmentManager();
        initToolbar();
        initMenuFragment();


       // addFragment(new StatistikLaporanFragment(), true, R.id.container);

        replaceFragment(new StatistikLaporanFragment(db, context));

    }

    private void initMenuFragment() {
        MenuParams menuParams = new MenuParams();
        menuParams.setActionBarSize((int) getResources().getDimension(R.dimen.tool_bar_height));
        menuParams.setMenuObjects(getMenuObjects());
        menuParams.setClosableOutside(false);
        mMenuDialogFragment = ContextMenuDialogFragment.newInstance(menuParams);
        mMenuDialogFragment.setItemClickListener(this);
        mMenuDialogFragment.setItemLongClickListener(this);
    }

    private List<MenuObject> getMenuObjects() {
        // You can use any [resource, bitmap, drawable, color] as image:
        // item.setResource(...)
        // item.setBitmap(...)
        // item.setDrawable(...)
        // item.setColor(...)
        // You can set image ScaleType:
        // item.setScaleType(ScaleType.FIT_XY)
        // You can use any [resource, drawable, color] as background:
        // item.setBgResource(...)
        // item.setBgDrawable(...)
        // item.setBgColor(...)
        // You can use any [color] as text color:
        // item.setTextColor(...)
        // You can set any [color] as divider color:
        // item.setDividerColor(...)

        List<MenuObject> menuObjects = new ArrayList<>();

        MenuObject close = new MenuObject("Tutup Menu");
        close.setResource(R.drawable.ic_close);
        close.setBgColor(Color.parseColor("#EA6052"));

        MenuObject statistik = new MenuObject("Statistik Laporan");
        statistik.setResource(R.drawable.ic_list);
        statistik.setBgColor(Color.parseColor("#e14938"));


        MenuObject send = new MenuObject("Riwayat Penghasilan");
        send.setResource(R.drawable.ic_stat);
        send.setBgColor(Color.parseColor("#40d47e"));

        MenuObject like = new MenuObject("Tambahkan Penghasilan");
        Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.ic_add);
        like.setBitmap(b);
        like.setBgColor(Color.parseColor("#2cc36b"));

        MenuObject addFr = new MenuObject("Riwayat Pengeluaran");
        BitmapDrawable bd = new BitmapDrawable(getResources(),
                BitmapFactory.decodeResource(getResources(), R.drawable.ic_stat));
        addFr.setDrawable(bd);
        addFr.setBgColor(Color.parseColor("#e98b39"));

        MenuObject addFav = new MenuObject("Tambahkan Pengeluaran");
        addFav.setResource(R.drawable.ic_add);
        addFav.setBgColor(Color.parseColor("#EC5E00"));

        MenuObject help = new MenuObject("Bantuan");
        help.setResource(R.drawable.ic_help2);
        help.setBgColor(Color.parseColor("#A66BBE"));

        MenuObject pengaturan = new MenuObject("Pengaturan");
        pengaturan.setResource(R.drawable.ic_setting3);
        pengaturan.setBgColor(Color.parseColor("#9B50BA"));

        MenuObject about = new MenuObject("Tentang");
        about.setResource(R.drawable.ic_about);
        about.setBgColor(Color.parseColor("#F4A62A"));

        menuObjects.add(close);
        menuObjects.add(statistik);
        menuObjects.add(send);
        menuObjects.add(like);
        menuObjects.add(addFr);
        menuObjects.add(addFav);
        menuObjects.add(help);
        menuObjects.add(pengaturan);
        menuObjects.add(about);
        return menuObjects;
    }

    Toolbar mToolbar;
    TextView mToolBarTextView;
    private void initToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolBarTextView = (TextView) findViewById(R.id.text_view_toolbar_title);
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        mToolbar.setNavigationIcon(R.mipmap.ic_launcher);
       /* mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Apakah anda yakin ingin menutup aplikasi ini?", Toast.LENGTH_SHORT).show();
                //onBackPressed();
            }
        });*/
       // getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        mToolBarTextView.setText(getJudul.JUDUL_STATISTIK);
    }


    protected void addFragment(Fragment fragment, boolean addToBackStack, int containerId) {
        invalidateOptionsMenu();
        String backStackName = fragment.getClass().getName();
        boolean fragmentPopped = fragmentManager.popBackStackImmediate(backStackName, 0);
        if (!fragmentPopped) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.add(containerId, fragment, backStackName)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            if (addToBackStack)
                transaction.addToBackStack(backStackName);
            transaction.commit();
        }
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

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.context_menu:
                if (fragmentManager.findFragmentByTag(ContextMenuDialogFragment.TAG) == null) {
                    mMenuDialogFragment.show(fragmentManager, ContextMenuDialogFragment.TAG);
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Global lokasiFragment =  Global.getInstance();
        if (mMenuDialogFragment != null && mMenuDialogFragment.isAdded()) {
            mMenuDialogFragment.dismiss();
        }

         if(lokasiFragment.getLokasiFragmentAktif().equals(lokasiFragment.statistikLaporanFragment)) {

             MaterialDialog.Builder builder = new MaterialDialog.Builder(context)
                     .iconRes(R.mipmap.ic_launcher)
                     .title("Keluar?")
                     .content("Apakah anda ingin menutup aplikasi ini?")
                     .positiveText("Ya")
                     .negativeText("Batal")
                     .onPositive(new MaterialDialog.SingleButtonCallback() {
                         @Override
                         public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                             // TODO
                             finish();
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
         } else if(lokasiFragment.getLokasiFragmentAktif().equals(lokasiFragment.listPemasukanFragment)){
             mToolBarTextView.setText(getJudul.JUDUL_STATISTIK);
             replaceFragment(new StatistikLaporanFragment(db, context));
         } else if(lokasiFragment.getLokasiFragmentAktif().equals(lokasiFragment.tambahPemasukanFragment)){
             mToolBarTextView.setText(getJudul.JUDUL_STATISTIK);
             replaceFragment(new StatistikLaporanFragment(db, context));
         } else if(lokasiFragment.getLokasiFragmentAktif().equals(lokasiFragment.lihatPemasukanFragment)){
             mToolBarTextView.setText(getJudul.JUDUL_LIST_PEMASUKAN);
             replaceFragment(new ListPemasukanFragment(context, db, mToolBarTextView));
         } else if(lokasiFragment.getLokasiFragmentAktif().equals(lokasiFragment.editPemasukanFragment)){
             mToolBarTextView.setText(getJudul.JUDUL_LIST_PEMASUKAN);
             replaceFragment(new ListPemasukanFragment(context, db, mToolBarTextView));
         }

         else if(lokasiFragment.getLokasiFragmentAktif().equals(lokasiFragment.listPengeluaranFragment)){
             mToolBarTextView.setText(getJudul.JUDUL_STATISTIK);
             replaceFragment(new StatistikLaporanFragment(db, context));
         } else if(lokasiFragment.getLokasiFragmentAktif().equals(lokasiFragment.tambahPengeluaranFragment)){
             mToolBarTextView.setText(getJudul.JUDUL_STATISTIK);
             replaceFragment(new StatistikLaporanFragment(db, context));
         }else if(lokasiFragment.getLokasiFragmentAktif().equals(lokasiFragment.lihatPengeluaranFragment)){
             mToolBarTextView.setText(getJudul.JUDUL_LIST_PENGELUARAN);
             replaceFragment(new ListPengeluaranFragment(context, db, mToolBarTextView));
         }else if(lokasiFragment.getLokasiFragmentAktif().equals(lokasiFragment.editPengeluaranFragment)){
             mToolBarTextView.setText(getJudul.JUDUL_LIST_PENGELUARAN);
             replaceFragment(new ListPengeluaranFragment(context, db, mToolBarTextView));
         }
    }

    @Override
    public void onMenuItemClick(View clickedView, int position) {
       // Toast.makeText(this, "Clicked on position: " + position, Toast.LENGTH_SHORT).show();
        switch (position){
            case 1:
                // addFragment(new ListPemasukanFragment(context, db), true, R.id.container);
                mToolBarTextView.setText(getJudul.JUDUL_STATISTIK);
                replaceFragment(new StatistikLaporanFragment(db, context));
                break;
            case 2:
               // addFragment(new ListPemasukanFragment(context, db), true, R.id.container);
                mToolBarTextView.setText(getJudul.JUDUL_LIST_PEMASUKAN);
                replaceFragment(new ListPemasukanFragment(context, db, mToolBarTextView));
                break;
            case 3:

                mToolBarTextView.setText(getJudul.JUDUL_TAMBAH_PEMASUKAN);
                replaceFragment(new TambahPemasukanFragment(context, db, mToolBarTextView));
                break;
            case 4:

                mToolBarTextView.setText(getJudul.JUDUL_LIST_PENGELUARAN);
                replaceFragment(new ListPengeluaranFragment(context, db, mToolBarTextView));
                break;
            case 5:
                mToolBarTextView.setText(getJudul.JUDUL_TAMBAH_PENGELUARAN);
                replaceFragment(new TambahPengeluaranFragment(context, db, mToolBarTextView));
                break;
            case 6:
                Intent help = new Intent(getBaseContext(), HelpActivity.class);
                startActivity(help);
                break;
            case 7:
                Intent pengaturan = new Intent(getBaseContext(),SettingActivity.class);
                startActivity(pengaturan);
                finish();
                break;
            case 8:
                Intent about = new Intent(getBaseContext(), AboutActivity.class);
                startActivity(about);
                break;
            default:
                break;
        }
    }

    @Override
    public void onMenuItemLongClick(View clickedView, int position) {
        //Toast.makeText(this, "Long clicked on position: " + position, Toast.LENGTH_SHORT).show();
    }

}
