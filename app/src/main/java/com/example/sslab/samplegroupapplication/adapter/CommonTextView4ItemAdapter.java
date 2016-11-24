package com.example.sslab.samplegroupapplication.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.sslab.samplegroupapplication.R;
import com.example.sslab.samplegroupapplication.data.CategoryItem;
import com.example.sslab.samplegroupapplication.data.CategoryItemIntType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by SSLAB on 2016-11-24.
 */

public class CommonTextView4ItemAdapter extends ArrayAdapter {
    public CommonTextView4ItemAdapter(Context context, int resource, List objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if(v == null){

            //TODO: 스타일은 어떻게 주는가..
            v = LayoutInflater.from(getContext()).inflate(R.layout.footerview_sample,null);
        }
        final Object item = getItem(position);

        if(item != null) {
            TextView text1= (TextView)v.findViewById(R.id.text1);
            TextView text2= (TextView)v.findViewById(R.id.text2);
            TextView text3= (TextView)v.findViewById(R.id.text3);
            TextView text4= (TextView)v.findViewById(R.id.text4);
            if (item instanceof CategoryItem) {
                text1.setText(((CategoryItem) item).getOrder());
                text2.setText(((CategoryItem) item).getCategory());
                text3.setText(((CategoryItem) item).getSubCategory());
                text4.setText(((CategoryItem) item).getBody());
            }

            if (item instanceof CategoryItemIntType) {
                text1.setText(((CategoryItemIntType) item).getOrder() + " : ");
                text2.setText(((CategoryItemIntType) item).getCategory());
                text3.setText(((CategoryItemIntType) item).getSubCategory());
                text4.setText(((CategoryItemIntType) item).getBody());
            }
        }

        return v;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

}
