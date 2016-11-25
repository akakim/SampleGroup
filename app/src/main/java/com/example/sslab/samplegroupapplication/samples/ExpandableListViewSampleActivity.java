package com.example.sslab.samplegroupapplication.samples;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import com.example.sslab.samplegroupapplication.R;
import com.example.sslab.samplegroupapplication.adapter.ExpandableAdapter;
import com.example.sslab.samplegroupapplication.data.phoneData;

import java.util.ArrayList;

public class ExpandableListViewSampleActivity extends AppCompatActivity {
    ExpandableListView expandableList;
    ExpandableAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandable_list_view_sample);

        LayoutInflater inflater = LayoutInflater.from(this);
        View headerView = inflater.inflate(R.layout.headerview_sample,null);
        View footerView = inflater.inflate(R.layout.footerview_sample,null);

        expandableList = (ExpandableListView)findViewById(R.id.expandableList);
        expandableList.addHeaderView(headerView);
        expandableList.addFooterView(footerView);
        adapter = new ExpandableAdapter(this,createData());
        expandableList.setAdapter(adapter);
    }

    private ArrayList<phoneData> createData(){

        ArrayList<phoneData> items = new ArrayList<>();
        ArrayList<String> androidItems = new ArrayList<>();
        ArrayList<String> iOSItems = new ArrayList<>();
        ArrayList<String> blackBerryItems = new ArrayList<>();
        ArrayList<String> windowPhoneItems = new ArrayList<>();

        androidItems.add("Nexus10");
        androidItems.add("Nexus7");
        androidItems.add("Nexus5");
        androidItems.add("Nexus5X");
        androidItems.add("Galaxy");

        iOSItems.add("iPhone");
        iOSItems.add("iPhone2");
        iOSItems.add("iPhone3");
        iOSItems.add("iPhone4");
        iOSItems.add("iPhone5");
        iOSItems.add("iPhone5S");
        iOSItems.add("iPhone6");
        for(int x= 0;x<5;x++)
            blackBerryItems.add("blackBerry"+ x);
        for(int x= 0;x<5;x++)
            windowPhoneItems.add("windowPhone"+ x);



        items.add(new phoneData(0,"ANDROID",androidItems));
        items.add(new phoneData(1,"IOS",iOSItems));
        items.add(new phoneData(2,"BlackBerry",blackBerryItems));
        items.add(new phoneData(3,"WindowPhone",windowPhoneItems));
        return items;
    }
}
