package com.fpmobile.catatanbukupengeluaran.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import com.fpmobile.catatanbukupengeluaran.R;


public class HelpActivity extends AppCompatActivity {
    private WebView webView;
    @Override
    protected void onCreate(Bundle savedincycle){
        super.onCreate(savedincycle);
        setContentView(R.layout.activity_help);

        webView = (WebView) findViewById(R.id.webview_help);
        webView.loadUrl("file:///android_asset/help.html");
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        finish();
    }

}
