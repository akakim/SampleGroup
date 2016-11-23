package com.example.sslab.samplegroupapplication.common;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * Created by SSLAB on 2016-11-23.
 */

public class CommonUIUpdateUtil {

    private static final String TAG = CommonUIUpdateUtil.class.getSimpleName();

    public static void setListViewHeight( final ListView listView ) {
        listView.post( new Runnable() {
            @Override
            public void run() {
                // getListAdapter
                ListAdapter listAdapter = listView.getAdapter();
                if ( listAdapter == null ) {
                    return;
                }
                int totalHeight = 0;
                int desiredWidth = View.MeasureSpec.makeMeasureSpec( listView.getWidth(), View.MeasureSpec.AT_MOST );
                for ( int i = 0; i < listAdapter.getCount(); i++ ) {
                    View listItem = listAdapter.getView( i, null, listView );
                    listItem.measure( desiredWidth, View.MeasureSpec.UNSPECIFIED );
                    totalHeight += listItem.getMeasuredHeight();
                }
                LinearLayout.LayoutParams params = ( LinearLayout.LayoutParams ) listView.getLayoutParams();
                params.height = totalHeight + ( listView.getDividerHeight() * ( listAdapter.getCount() - 1 ) + listView.getPaddingBottom() + listView.getPaddingTop() );
                listView.setLayoutParams( params );
                listView.requestLayout();
            }
        } );
    }


}
