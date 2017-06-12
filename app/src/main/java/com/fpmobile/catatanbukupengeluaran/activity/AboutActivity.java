package com.fpmobile.catatanbukupengeluaran.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import com.fpmobile.catatanbukupengeluaran.R;


public class AboutActivity extends AppCompatActivity {
    private WebView webView;
    @Override
    protected void onCreate(Bundle savedIncycle){
        super.onCreate(savedIncycle);
        setContentView(R.layout.activity_about);

        webView = (WebView) findViewById(R.id.webview_about);
        webView.loadUrl("file:///android_asset/about.html");
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        finish();
    }
}
