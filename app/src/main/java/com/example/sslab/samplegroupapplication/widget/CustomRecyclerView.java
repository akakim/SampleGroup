package com.example.sslab.samplegroupapplication.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;

/**
 * Created by SSLAB on 2017-03-28.
 */

public class CustomRecyclerView extends RecyclerView {
    final String TAG = this.getClass().getSimpleName();
    public CustomRecyclerView(Context context) {
        super(context);
    }

    public CustomRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    @Override
    public void requestLayout() {
        Log.d(TAG,"requestLyaout()");
        super.requestLayout();
    }


    @Override
    protected void onAttachedToWindow() {
        Log.d(TAG,"onAttachedToWindow()");
        super.onAttachedToWindow();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        Log.d(TAG,"onLayout()");
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.d(TAG,"onMeasure()");
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public void onDraw(Canvas canvas) {
        Log.d(TAG,"onDraw()");

        super.onDraw(canvas);
    }
}
