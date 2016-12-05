package com.example.sslab.samplegroupapplication.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.sslab.samplegroupapplication.R;
import com.example.sslab.samplegroupapplication.data.PushItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SSLAB on 2016-12-05.
 */

public class NotificationAdapter extends ArrayAdapter<PushItem>{


    InterfaceToFragment interfaceToFragment;
    int size = 20;
    public static final int TYPE_COUNT = 1;
    public static final int VIEW_TYPE_RELATIVE_LAYOUT = 1;


    public NotificationAdapter(Context context, int resource, List<PushItem> objects, InterfaceToFragment interfaceToFragment) {
        super(context, resource, objects);
        this.interfaceToFragment = interfaceToFragment;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = view;


        if(v == null){
            v = LayoutInflater.from(getContext()).inflate(R.layout.listview_notice_item,viewGroup,false);
            v.setId(i);
        }

        if(v instanceof RelativeLayout){

            final int position = i;
            RelativeLayout layout = (RelativeLayout)v;
            TextView description = (TextView)v.findViewById(R.id.description);
            TextView date = (TextView)v.findViewById(R.id.date);
            TextView isNew = (TextView)v.findViewById(R.id.isNew);

            final PushItem pushItem = getItem(i);
            description.setText(pushItem.getPushSummaryContent());
            date.setText(pushItem.getRegistDate());
            if(pushItem.isConfirmYesOrNo()){
                isNew.setVisibility(View.VISIBLE);

                description.setTextColor(Color.parseColor("#000000"));
                date.setTextColor(Color.parseColor("#5684FE"));

            }else {
                isNew.setVisibility(View.GONE);
                description.setTextColor(Color.parseColor("#A0A0A0"));
                date.setTextColor(Color.parseColor("#A0A0A0"));

            }
            layout.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    interfaceToFragment.UIUpdate(position);
                }
            });

        }

        return v;
    }

    public interface InterfaceToFragment{
        public void UIUpdate(int position);
    }
}
