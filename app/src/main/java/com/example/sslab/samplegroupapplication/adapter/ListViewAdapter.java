package com.example.sslab.samplegroupapplication.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.sslab.samplegroupapplication.data.activityList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SSLAB on 2017-01-16.
 */

public class ListViewAdapter extends ArrayAdapter<activityList>  {

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

    @Override
    public int getViewTypeCount() {
        return 1;
    }
}
