package com.example.sslab.samplegroupapplication.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * Created by SSLAB on 2017-03-28.
 */

public class CustomListView extends ListView {


    final String TAG = getClass().getSimpleName();
    public CustomListView(Context context) {
        super(context);
    }

    public CustomListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CustomListView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

//    @Override
//    public void requestLayout() {
//        Log.d(TAG,"requestLyaout()");
//        super.requestLayout();
//    }
//
//
//    @Override
//    protected void onAttachedToWindow() {
//        Log.d(TAG,"onAttachedToWindow()");
//        super.onAttachedToWindow();
//    }
//
//    @Override
//    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
//        Log.d(TAG,"onLayout()");
//        super.onLayout(changed, left, top, right, bottom);
//    }
//
//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        Log.d(TAG,"onMeasure()");
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//    }
//
//    @Override
//    protected void onDraw(Canvas canvas) {
//        Log.d(TAG,"onDraw()");
//
//        super.onDraw(canvas);
//    }
//
//    @Override
//    public void setAdapter(ListAdapter adapter) {
//        Log.d(TAG,"setAdapter()");
//        super.setAdapter(adapter);
//    }
//
//    @Override
//    protected void layoutChildren() {
//        Log.d(TAG,"layoutChildren");
//        super.layoutChildren();
//        Log.d(TAG,"after layoutChildren");
//
//    }



}
