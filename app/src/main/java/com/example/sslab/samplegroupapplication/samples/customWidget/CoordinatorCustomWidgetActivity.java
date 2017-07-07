package com.example.sslab.samplegroupapplication.samples.customWidget;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ListView;

import com.example.sslab.samplegroupapplication.R;
import com.example.sslab.samplegroupapplication.widget.CustomizingCoordinatorView;

import java.util.ArrayList;
import java.util.Arrays;

public class CoordinatorCustomWidgetActivity extends AppCompatActivity {


//    WebView webView;

    ListView listView;
    ArrayList<String> vegitableItems = new ArrayList<>();

    CustomizingCoordinatorView main_content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coodinator_custom_widget);

        main_content = ( CustomizingCoordinatorView ) findViewById( R.id.main_content);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setAdapter(new SimpleStringRecyclerViewAdapter(Arrays.asList(Cheeses.sCheeseStrings)));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        listView = (ListView) findViewById( R.id.listView);
        vegitableItems.add("apple");
        vegitableItems.add("coffee");
        vegitableItems.add("pineapple");
        vegitableItems.add("peach");
        vegitableItems.add("banana");
        vegitableItems.add("Groovy");
        vegitableItems.add("Group");
        vegitableItems.add("Gale");
        vegitableItems.add("apple");
        vegitableItems.add("apple");

//        webView = (WebView)findViewById(R.id.webView);
//        webView.getSettings().setJavaScriptEnabled(true);
//        webView.setWebViewClient(new WebViewClient());
//        webView.setWebChromeClient(new WebChromeClient());
//
//        webView.loadUrl("http://m.naver.com");
    }
}
