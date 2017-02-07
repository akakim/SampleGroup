package com.example.sslab.samplegroupapplication.samples;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.sslab.samplegroupapplication.R;
import com.example.sslab.samplegroupapplication.SNSSamples.FacebookActivity;
import com.example.sslab.samplegroupapplication.adapter.ListViewAdapter;
import com.example.sslab.samplegroupapplication.data.activityList;
import com.example.sslab.samplegroupapplication.SNSSamples.KakaoLoginSample;

import java.util.ArrayList;

public class SNSSampleActivity extends AppCompatActivity {

    ArrayList<activityList> items = new ArrayList<>();
    ListView listView;
    ListViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snssample);

        listView = (ListView ) findViewById( R.id.list );

        items.add(new activityList(KakaoLoginSample.class.getSimpleName(),KakaoLoginSample.class));
        items.add(new activityList(FacebookActivity.class.getSimpleName(),FacebookActivity.class));
        items.add(new activityList(KakaoLoginSample.class.getSimpleName(),KakaoLoginSample.class));
        items.add(new activityList(KakaoLoginSample.class.getSimpleName(),KakaoLoginSample.class));


        adapter = new ListViewAdapter(this,android.R.layout.simple_list_item_1,items );
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                activityList item = (activityList) adapterView.getItemAtPosition( i );
                Intent intent = new Intent(getApplicationContext(),item.getaClass());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }
}
