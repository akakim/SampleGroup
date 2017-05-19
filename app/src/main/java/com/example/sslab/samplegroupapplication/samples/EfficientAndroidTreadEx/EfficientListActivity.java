package com.example.sslab.samplegroupapplication.samples.EfficientAndroidTreadEx;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.sslab.samplegroupapplication.R;
import com.example.sslab.samplegroupapplication.adapter.ActivityViewListAdapter;
import com.example.sslab.samplegroupapplication.data.activityList;

import java.util.ArrayList;

public class EfficientListActivity extends AppCompatActivity {

    ArrayList<activityList> items = new ArrayList<>();

    ListView efficientListView;
    ActivityViewListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_efficient_list);

        efficientListView = ( ListView ) findViewById( R.id.EfficientListView );

        adapter = new ActivityViewListAdapter(this, -1, items);
        efficientListView.setAdapter(adapter);
        efficientListView.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent (EfficientListActivity.this, items.get(position).getaClass());
                startActivity( i );
            }
        });
        items.add(new activityList(LooperActivity.class.getSimpleName(),LooperActivity.class));
        adapter.notifyDataSetChanged();
    }
}
