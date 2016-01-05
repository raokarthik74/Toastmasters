package com.karthikravindrarao.TM_District_92;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class webView extends AppCompatActivity {
    
    WebView fullPageWebView;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        Intent intent = getIntent();
        fullPageWebView = (WebView) findViewById(R.id.webView);
        fullPageWebView.loadUrl(intent.getStringExtra(MainActivity.urlForWebView));
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressbarForWebView);
        //fullPageWebView.getSettings().setPluginState(WebSettings.PluginState.ON);
        //fullPageWebView.getSettings().setBuiltInZoomControls(true);
        progressBar.setVisibility(View.VISIBLE);
        fullPageWebView.getSettings().setJavaScriptEnabled(true);
        //fullPageWebView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        //fullPageWebView.setWebViewClient(new MyWebViewClient());
        fullPageWebView.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                if (progress == 100) {
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
        if(!isNetworkAvailable()) {
            TextView textView = new TextView(this);
            textView.setTextSize(25);
            textView.setText("Internet required \n Check internet connection and try again");
            textView.setGravity(Gravity.CENTER);
            setContentView(textView);
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_web_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

//    private class MyWebViewClient extends WebViewClient {
//
//        @Override
//        public boolean shouldOverrideUrlLoading(WebView view, String url) {
//            view.loadUrl(url);
//            return false;
//        }
//    }


}

