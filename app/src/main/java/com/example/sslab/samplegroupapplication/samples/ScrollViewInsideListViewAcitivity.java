package com.example.sslab.samplegroupapplication.samples;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ListView;

import com.example.sslab.samplegroupapplication.R;
import com.example.sslab.samplegroupapplication.adapter.CommonTextView4ItemAdapter;
import com.example.sslab.samplegroupapplication.data.CategoryItem;

import java.util.ArrayList;

public class ScrollViewInsideListViewAcitivity extends AppCompatActivity {

    ListView listView;
    CommonTextView4ItemAdapter text4ItemAdapter;
    ArrayList<CategoryItem> categoryItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_view_inside_list_view_acitivity);

        listView = (ListView)findViewById(R.id.insideListView);

        for(int i =0;i<20;i++){
            CategoryItem item = new CategoryItem(String.valueOf(i),"category"+i,"SubCate","body");
            categoryItems.add(item);
        }


        text4ItemAdapter = new CommonTextView4ItemAdapter(this,R.layout.footerview_sample,categoryItems);

        LayoutInflater inflater = LayoutInflater.from(this);
        View headerView = inflater.inflate(R.layout.headerview_sample,null);
        View footerView = inflater.inflate(R.layout.footerview_sample,null);

        listView.addHeaderView(headerView);

        listView.addFooterView(footerView);
        listView.setAdapter(text4ItemAdapter);
    }

}
