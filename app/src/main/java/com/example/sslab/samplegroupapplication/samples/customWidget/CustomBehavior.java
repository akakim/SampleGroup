package com.example.sslab.samplegroupapplication.samples.customWidget;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ScrollingView;
import android.view.View;

/**
 * Created by SSLAB on 2017-07-06.
 */

public class CustomBehavior extends AppBarLayout.ScrollingViewBehavior {

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return super.layoutDependsOn(parent, child, dependency);
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        return super.onDependentViewChanged(parent, child, dependency);
    }
}
