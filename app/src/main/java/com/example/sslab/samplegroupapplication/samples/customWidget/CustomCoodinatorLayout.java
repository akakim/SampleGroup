package com.example.sslab.samplegroupapplication.samples.customWidget;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.ViewGroup;

/**
 * Created by SSLAB on 2017-07-10.
 */

public class CustomCoodinatorLayout extends CoordinatorLayout {
    public CustomCoodinatorLayout(Context context) {
        super(context);
    }

    public CustomCoodinatorLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomCoodinatorLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public ViewGroup.LayoutParams getLayoutParams() {
        return super.getLayoutParams();
    }
}
