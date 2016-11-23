package com.example.sslab.samplegroupapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.sslab.samplegroupapplication.common.CommonUIUpdateUtil;

import java.util.ArrayList;

public class ProgramaticallySettingLayoutHeightActivity extends AppCompatActivity {


    ArrayAdapter adapter;
    ArrayList<String> sampleData = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_programatically_setting_layout_height);

        final ListView programaticallyUpdatedListView = (ListView)findViewById(R.id.programaticallyUpdatedListView);
        for (int i =0;i<20;i++)
        sampleData.add("sample"+i);

        adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,sampleData);
        programaticallyUpdatedListView.setAdapter(adapter);

        for (int i =0;i<9;i++)
            sampleData.add("sample"+i);
        adapter.notifyDataSetChanged();
//        CommonUIUpdateUtil.setListViewHeight(programaticallyUpdatedListView);
//        programaticallyUpdatedListView.canScrollList(View.SCROLL_AXIS_VERTICAL);
        programaticallyUpdatedListView.smoothScrollToPosition(sampleData.size());
    }


}
