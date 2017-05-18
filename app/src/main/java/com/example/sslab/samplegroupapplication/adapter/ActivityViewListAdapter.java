package com.example.sslab.samplegroupapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.TextView;

import com.example.sslab.samplegroupapplication.R;
import com.example.sslab.samplegroupapplication.data.activityList;

import java.util.List;

/**
 * Created by SSLAB on 2016-11-30.
 */

public class ActivityViewListAdapter extends ArrayAdapter<activityList> {

    private int mPosition = -1;

    public ActivityViewListAdapter(Context context, int resource, List<activityList> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if(v == null){
            v = LayoutInflater.from(getContext()).inflate( R.layout.listview_check_activity_item,null);
        }
        final activityList item = ( activityList )getItem(position);
        final CheckedTextView textView = (CheckedTextView)v.findViewById(R.id.CheckedTextView);
//        textView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mPosition = position;
//                notifyDataSetChanged();
//            }
//        });
//
//        if(position == mPosition){
//            textView.setChecked(true);
//        }else{
//            textView.setChecked(false);
//        }
//        if (simpleCheckedTextView.isChecked()) {
//// set cheek mark drawable and set checked property to false
//            value = "un-Checked";
//            simpleCheckedTextView.setCheckMarkDrawable(0);
//            simpleCheckedTextView.setChecked(false);
//        } else {
//// set cheek mark drawable and set checked property to true
//            value = "Checked";
//            simpleCheckedTextView.setCheckMarkDrawable(R.drawable.checked);
//            simpleCheckedTextView.setChecked(true);
//        }
//        Toast.makeText(context, value, Toast.LENGTH_SHORT).show();

        textView.setText(item.getActivityName());
        return v;
    }

    public void setPosition(int mPosition){
        this.mPosition = mPosition;
    }
}
