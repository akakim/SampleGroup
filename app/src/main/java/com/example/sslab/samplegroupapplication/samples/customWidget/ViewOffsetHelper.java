package com.example.sslab.samplegroupapplication.samples.customWidget;

import android.os.Build;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import android.view.View;

/**
 * 뷰의 크기를 제공해주는 헬퍼
 */

public class ViewOffsetHelper {

    private final String TAG = getClass().getSimpleName();
    private final View mView;
    private int mLayoutTop;
    private int mOffsetTop;

    public ViewOffsetHelper(View view){

        Log.d(TAG,"Constructor() ");
        mView = view;
    }

    public void onViewLayout() {
        Log.d(TAG,"onViewLayout() ");
        mLayoutTop = mView.getTop();    // 상단 설정
        updateOffsets();                // 상단과의 간격 설정
    }

    private void updateOffsets() {
        Log.d(TAG,"updateOffsets() ");
        if(Build.VERSION.SDK_INT == 22) {
            ViewCompat.setTranslationY(mView, (float) mOffsetTop);
        } else {
            ViewCompat.offsetTopAndBottom(mView, mOffsetTop - mView.getTop() - mLayoutTop);
        }
    }

    public boolean setTopAndBottomOffset(int offset) {

        Log.d(TAG,"setTopAndBottomOffset() ");
        if(mOffsetTop != offset) {
            mOffsetTop = offset;
            updateOffsets();
            return true;
        } else {
            return false;
        }
    }

    public int getTopAndBottomOffset() {
        return mOffsetTop;
    }
}
