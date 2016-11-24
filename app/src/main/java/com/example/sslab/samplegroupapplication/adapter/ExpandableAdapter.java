package com.example.sslab.samplegroupapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sslab.samplegroupapplication.R;
import com.example.sslab.samplegroupapplication.data.phoneData;

import java.util.ArrayList;

/**
 * Created by SSLAB on 2016-11-24.
 */

public class ExpandableAdapter extends BaseExpandableListAdapter{

    Context mContext;
    ArrayList<phoneData> mPhoneData;

    public ExpandableAdapter(Context context, ArrayList<phoneData> phone) {
        mContext = context;
        mPhoneData = phone;
    }

    @Override
    public int getGroupCount() {
        return mPhoneData.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mPhoneData.get(groupPosition).getPhoneType().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mPhoneData.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mPhoneData.get(groupPosition).getPhoneType().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return mPhoneData.get(groupPosition).getId();
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean b, View view, ViewGroup viewGroup) {

        View v = view;
        if(v == null) {
            v = getParentGenericView();


        } else {
            v = view;
        }

        ImageView img = (ImageView)v.findViewById(R.id.img);
        img.setImageResource(mContext.getResources().getIdentifier("ic_launcher", "drawable", mContext.getPackageName()));
        TextView text = (TextView)v.findViewById(R.id.text);
        text.setText(mPhoneData.get(groupPosition).getOS());
        return v;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean b, View convertView, ViewGroup viewGroup) {
        View view = convertView;
        if(convertView == view) {
            view = getChildGenericView();
        } else {
            view = convertView;
        }

        TextView text = (TextView)view.findViewById(android.R.id.text1);
        text.setText(mPhoneData.get(groupPosition).getPhoneType().get(childPosition));
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }


    //Child의 View의 XML을 생성
    public View getChildGenericView() {
        LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(android.R.layout.simple_expandable_list_item_1, null);
        return view;
    }


    //Parent(Group)의 View의 XML을 생성
    public View getParentGenericView() {
        LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.simple_expandable_list_item, null);
        return view;
    }
}
