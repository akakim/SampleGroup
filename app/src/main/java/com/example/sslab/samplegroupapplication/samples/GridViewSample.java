package com.example.sslab.samplegroupapplication.samples;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

import com.example.sslab.samplegroupapplication.R;
import com.example.sslab.samplegroupapplication.adapter.HistoryDetailAdapter;
import com.example.sslab.samplegroupapplication.data.HistoryDetailItem;

import java.util.ArrayList;
import java.util.List;

public class GridViewSample extends AppCompatActivity {
    GridView gridViewSample;
    HistoryDetailAdapter adapter;
    List items;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view_sample);
        items = new ArrayList<HistoryDetailItem>();
        for(int i =0; i< 4;i++){
            for (int r = 0; r< 3;r++){
                items.add(new HistoryDetailItem("category"+i,"body:"+r));
                if(i == 3 && r == 1)
                    break;
            }
        }

        gridViewSample = (GridView)findViewById(R.id.gridViewSample);
        adapter = new HistoryDetailAdapter(this,items);
        gridViewSample.setAdapter(adapter);
    }



}
