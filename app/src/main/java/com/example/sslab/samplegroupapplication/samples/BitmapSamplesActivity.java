package com.example.sslab.samplegroupapplication.samples;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.sslab.samplegroupapplication.R;
import com.example.sslab.samplegroupapplication.adapter.ActivityViewListAdapter;
import com.example.sslab.samplegroupapplication.bitmapsSVGAnim.BitmapAsynchTaskActivity;
import com.example.sslab.samplegroupapplication.bitmapsSVGAnim.ExampleActivity;
import com.example.sslab.samplegroupapplication.bitmapsSVGAnim.GalleryAndViewPagerActivity;
import com.example.sslab.samplegroupapplication.bitmapsSVGAnim.useableProgressActivity;
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
        items.add(new activityList(ExampleActivity.class.getSimpleName(),ExampleActivity.class));
        items.add(new activityList(useableProgressActivity.class.getSimpleName(),useableProgressActivity.class));
//        items.add(new activityList(GalleryAndViewPagerActivity.class.getSimpleName(),GalleryAndViewPagerActivity.class));


        adapter = new ActivityViewListAdapter(this,android.R.layout.simple_list_item_1,items);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener(){

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(getApplicationContext(),items.get(position).getaClass());
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                }
        );
    }
}
