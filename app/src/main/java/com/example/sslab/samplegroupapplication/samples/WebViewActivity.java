package com.example.sslab.samplegroupapplication.samples;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.example.sslab.samplegroupapplication.R;

public class WebViewActivity extends AppCompatActivity {

    WebView webView;
    WebChromeClient webChromeClient;
    ConsoleMessage consoleMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
//        consoleMessage.message(ConsoleMessage.MessageLevel.DEBUG, )
        webChromeClient = new WebChromeClient();
        webView = ( WebView )findViewById( R.id.webView );
        webView.addJavascriptInterface(new WebInterface(this),"android");
        webView.loadUrl("file:///android_asset/www/index.html");
        webView.getSettings().setJavaScriptEnabled( true );
        webView.setWebChromeClient( webChromeClient);
        webChromeClient.onHideCustomView();
//        webChromeClient.onConsoleMessage()

    }
}
