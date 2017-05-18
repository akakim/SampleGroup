package com.example.sslab.samplegroupapplication.imageFileView;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.sslab.samplegroupapplication.R;
import com.example.sslab.samplegroupapplication.adapter.ActivityViewListAdapter;
import com.example.sslab.samplegroupapplication.data.ActivityListBundleOption;
import com.example.sslab.samplegroupapplication.data.activityList;

import java.util.ArrayList;

public class ModeSettingActivity extends AppCompatActivity {

    ListView listView;
    Toolbar toolbar;

    ArrayList<ActivityListBundleOption> items = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode_setting);

        toolbar = ( Toolbar ) findViewById( R.id.toolbar );
        listView = ( ListView )findViewById( R.id.listView );
        toolbar.setTitle("ModeSetting plz");
        setSupportActionBar( toolbar );
        toolbar.setCollapsible( true );
        toolbar.setNavigationIcon(R.mipmap.indicator);

        Bundle listViewModeBundle = new Bundle();
        Bundle gridViewModeBundle = new Bundle();
        listViewModeBundle.putString("Mode","ListViewMode");
        gridViewModeBundle.putString("Mode","GridViewMode");
        items.add(new ActivityListBundleOption("ListViewMode",FileShowActivity.class,listViewModeBundle));

        items.add(new ActivityListBundleOption("GridViewMode",FileShowActivity.class,gridViewModeBundle));
        items.add(new ActivityListBundleOption("GridViewMode",FileShowActivity.class,gridViewModeBundle));


//        ActivityViewListAdapter adapter = new ActivityViewListAdapter(this,android.R.layout.simple_list_item_1, items);
//        listView.setAdapter(adapter);
//        listView.setOnItemClickListener(new ListView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent i = new Intent(ModeSettingActivity.this,items.get(position).getaClass());
//                i.putExtras(items.get(position).getBundle());
//                startActivity(i);
//            }
//        });
    }
}
