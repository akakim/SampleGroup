package com.example.sslab.samplegroupapplication.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.sslab.samplegroupapplication.data.activityList;

import java.util.List;

/**
 * Created by SSLAB on 2016-11-30.
 */

public class ActivityViewListAdapter extends ArrayAdapter {

    public ActivityViewListAdapter(Context context, int resource, List<? extends activityList> objects) {
        super(context, resource,objects);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = super.getView(position, convertView, parent);
        final activityList item = ( activityList )getItem(position);
        TextView textView = (TextView)v.findViewById(android.R.id.text1);
        textView.setText(item.getActivityName());
        return v;
    }
}
