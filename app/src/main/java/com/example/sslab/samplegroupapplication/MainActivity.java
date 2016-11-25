package com.example.sslab.samplegroupapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.sslab.samplegroupapplication.adapter.CommonTextView4ItemAdapter;
import com.example.sslab.samplegroupapplication.data.activityList;
import com.example.sslab.samplegroupapplication.samples.CustomTextViewActivity;
import com.example.sslab.samplegroupapplication.samples.ExpandableListViewSampleActivity;
import com.example.sslab.samplegroupapplication.samples.GridViewSample;
import com.example.sslab.samplegroupapplication.samples.ProgramaticallySettingLayoutHeightActivity;
import com.example.sslab.samplegroupapplication.samples.Sample02Activity;
import com.example.sslab.samplegroupapplication.samples.ScrollViewInsideListViewAcitivity;
import com.example.sslab.samplegroupapplication.samples.ThreadMessageQueueSample;
import com.example.sslab.samplegroupapplication.util.UncaughtExceptionHandlerApplication;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ArrayList<activityList> items = new ArrayList<>();
    ListViewAdapter adapter;
    ListView listView;

    private Thread.UncaughtExceptionHandler mUncaughtExceptionHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.list);

        mUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();

        Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandlerApplication(mUncaughtExceptionHandler,this));


        items.add(new activityList(ThreadMessageQueueSample.class.getSimpleName(), ThreadMessageQueueSample.class));
        items.add(new activityList(Sample02Activity.class.getSimpleName(), Sample02Activity.class));
        items.add(new activityList(CustomTextViewActivity.class.getSimpleName(),CustomTextViewActivity.class));
        items.add(new activityList(GridViewSample.class.getSimpleName(),GridViewSample.class));
        items.add(new activityList(ProgramaticallySettingLayoutHeightActivity.class.getSimpleName(),ProgramaticallySettingLayoutHeightActivity.class));
        items.add(new activityList(ScrollViewInsideListViewAcitivity.class.getSimpleName(),ScrollViewInsideListViewAcitivity.class));
        items.add(new activityList(ExpandableListViewSampleActivity.class.getSimpleName(),ExpandableListViewSampleActivity.class));
        adapter = new ListViewAdapter(this, android.R.layout.simple_list_item_1, items);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(),items.get(i).getaClass());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);

    }

    @Override
    public void startActivity(Intent intent, Bundle options) {
        super.startActivity(intent, options);
        String option = options.getString("option");
        switch (option){
            case "error_conflict":
                break;
        }
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
