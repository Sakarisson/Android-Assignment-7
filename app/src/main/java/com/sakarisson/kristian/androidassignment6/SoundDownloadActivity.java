package com.sakarisson.kristian.androidassignment6;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class SoundDownloadActivity extends AppCompatActivity {
    private WebView web;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sound_download);
        toolbar = findViewById(R.id.toolbar_menu);
        setSupportActionBar(toolbar);

        web = findViewById(R.id.webview);
        web.setWebViewClient(new SoundDownloadWebViewClient());
        web.loadUrl("http://dt031g.programvaruteknik.nu/dialpad/sounds/");
    }

    public class SoundDownloadWebViewClient extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon){
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}
