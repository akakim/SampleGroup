package com.example.sslab.samplegroupapplication;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sslab.samplegroupapplication.adapter.CommonTextView4ItemAdapter;
import com.example.sslab.samplegroupapplication.data.activityList;
import com.example.sslab.samplegroupapplication.samples.BitmapSamplesActivity;
import com.example.sslab.samplegroupapplication.samples.*;
import com.example.sslab.samplegroupapplication.util.UncaughtExceptionHandlerApplication;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ArrayList<activityList> items = new ArrayList<>();
    ArrayList<activityList> filteredItems = new ArrayList<>();
    ListViewAdapter adapter;
    ListView listView;
    EditText searchEditText;
    private Thread.UncaughtExceptionHandler mUncaughtExceptionHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.list);
        searchEditText = (EditText) findViewById(R.id.searchEditText);
        searchEditText.setFocusable(false);
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                MainActivity.this.adapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        mUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();

        Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandlerApplication(mUncaughtExceptionHandler,this));


        items.add(new activityList(ThreadMessageQueueSample.class.getSimpleName(), ThreadMessageQueueSample.class));
        items.add(new activityList(Sample02Activity.class.getSimpleName(), Sample02Activity.class));
        items.add(new activityList(CustomTextViewActivity.class.getSimpleName(),CustomTextViewActivity.class));
        items.add(new activityList(GridViewSample.class.getSimpleName(),GridViewSample.class));
        items.add(new activityList(ProgramaticallySettingLayoutHeightActivity.class.getSimpleName(),ProgramaticallySettingLayoutHeightActivity.class));
        items.add(new activityList(ScrollViewInsideListViewAcitivity.class.getSimpleName(),ScrollViewInsideListViewAcitivity.class));
        items.add(new activityList(ExpandableListViewSampleActivity.class.getSimpleName(),ExpandableListViewSampleActivity.class));
        items.add(new activityList(SwipeRefreshBottomLayoutActivity.class.getSimpleName(),SwipeRefreshBottomLayoutActivity.class));
        items.add(new activityList(FocusingSampleActivity.class.getSimpleName(),FocusingSampleActivity.class));
        items.add(new activityList(FocusingLinearActivity.class.getSimpleName(),FocusingLinearActivity.class));
        items.add(new activityList(URLConnectionSampleActivity.class.getSimpleName(),URLConnectionSampleActivity.class));
        filteredItems.addAll(items);
//        items.add(new activityList(BitmapSamplesActivity.class.getSimpleName(),BitmapSamplesActivity.class));
        adapter = new ListViewAdapter(this, android.R.layout.simple_list_item_1, filteredItems);


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
    }


    private class ListViewAdapter extends ArrayAdapter<activityList> implements Filterable{

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

        @NonNull
        @Override
        public Filter getFilter() {
            Filter filter = new Filter() {


                @Override
                protected FilterResults performFiltering(CharSequence charSequence) {
                    FilterResults results = new FilterResults();
                    ArrayList<activityList> FilteredArrayNames = new ArrayList<activityList>();


                    charSequence = charSequence.toString().toLowerCase();

                    for (int i = 0; i < items.size(); i++) {
                        activityList item = items.get(i);
                        if (item.getActivityName().toLowerCase().startsWith(charSequence.toString()))  {
                            FilteredArrayNames.add(item);
                        }
                    }

                    results.count = FilteredArrayNames.size();
                    results.values = FilteredArrayNames;
                    Log.d("VALUES", results.values.toString());


                    return results;
                }

                @Override
                protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                    filteredItems = (ArrayList<activityList>) filterResults.values;

                    listView.post(new Runnable() {
                        @Override
                        public void run() {
                            listView.invalidate();
                            adapter.notifyDataSetChanged();
                        }
                    });
                }
            };
            return filter;
        }

    }
}
