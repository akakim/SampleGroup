package com.example.sslab.samplegroupapplication.samples;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.sslab.samplegroupapplication.R;
import com.example.sslab.samplegroupapplication.adapter.ActivityViewListAdapter;
import com.example.sslab.samplegroupapplication.data.activityList;
import com.example.sslab.samplegroupapplication.samples.customWidget.CoodinatorLayoutSampleActivity;
import com.example.sslab.samplegroupapplication.samples.customWidget.CoordinatorCustomWidgetActivity;
import com.example.sslab.samplegroupapplication.samples.customWidget.CoordinatorExamples;
import com.example.sslab.samplegroupapplication.samples.customWidget.CustomProgressDialogTestActivity;
import com.example.sslab.samplegroupapplication.widget.CustomListView;

import java.util.ArrayList;

public class CustomWidgetListActivity extends AppCompatActivity implements ListView.OnItemClickListener{

    ListView listView;
    ArrayList<activityList> items = new ArrayList<>();
    ActivityViewListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_widget_list);

        listView = (CustomListView) findViewById(R.id.list);

        items.add(new activityList(CoordinatorCustomWidgetActivity.class.getSimpleName() , CoordinatorCustomWidgetActivity.class ));
        items.add(new activityList(CoodinatorLayoutSampleActivity.class.getSimpleName() , CoodinatorLayoutSampleActivity.class ));
        items.add(new activityList(CoordinatorExamples.class.getSimpleName(),CoordinatorExamples.class));
        items.add(new activityList(CustomProgressDialogTestActivity.class.getSimpleName(),CustomProgressDialogTestActivity.class));

        // now let's getting start CoodinatorLayout
        adapter = new ActivityViewListAdapter(this, -1, items);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener( this );
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent i = new Intent(this,items.get(position).getaClass());
        startActivity(i);
    }
}
