package com.example.sslab.samplegroupapplication.samples;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.sslab.samplegroupapplication.MainActivity;
import com.example.sslab.samplegroupapplication.R;
import com.example.sslab.samplegroupapplication.adapter.ActivityViewListAdapter;
import com.example.sslab.samplegroupapplication.bitmaps.BitmapAsynchTaskActivity;
import com.example.sslab.samplegroupapplication.data.activityList;

import java.util.ArrayList;

public class BitmapSamplesActivity extends AppCompatActivity {

    ArrayList<activityList> items = new ArrayList<>();
    ActivityViewListAdapter adapter;
    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitmap_samples);
        listView = (ListView)findViewById(R.id.listView);

        items.add(new activityList("비트맵 비동기 activity",BitmapAsynchTaskActivity.class));
        adapter = new ActivityViewListAdapter(this,android.R.layout.simple_list_item_1,items);

        listView.setAdapter(adapter);
        listView.setOnItemSelectedListener(
                new ListView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent intent = new Intent(getApplicationContext(),items.get(i).getaClass());
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                }
        );
    }
}
