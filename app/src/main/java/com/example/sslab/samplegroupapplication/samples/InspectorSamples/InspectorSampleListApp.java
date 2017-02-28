package com.example.sslab.samplegroupapplication.samples.InspectorSamples;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ListView;
import android.widget.TextView;

import com.example.sslab.samplegroupapplication.BaseActivity;
import com.example.sslab.samplegroupapplication.R;
import com.example.sslab.samplegroupapplication.data.activityList;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class InspectorSampleListApp extends BaseActivity {

    ListView list;
    ArrayList<activityList> items = new ArrayList<>();
    ListViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inspector_sample_list_app);

        list = (ListView )findViewById(R.id.list);
        items.add(new activityList(GetGalleryActivity.class.getSimpleName(),GetGalleryActivity.class));
        adapter = new ListViewAdapter(this,android.R.layout.simple_list_item_1,items);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i  = new Intent(InspectorSampleListApp.this,items.get(position).getaClass());

                startActivity(i);
            }
        });
    }

    @Override
    public void responseData(JSONObject jsonObject) {

    }

    private class ListViewAdapter extends ArrayAdapter<activityList> {

        public ListViewAdapter(Context context, int resource, List<activityList> objects) {
            super(context, resource, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View v = super.getView(position, convertView, parent);
            final activityList item = getItem(position);
            TextView textView = (TextView)v.findViewById(android.R.id.text1);
            textView.setText(item.getActivityName());
            return v;
        }
    }
}
