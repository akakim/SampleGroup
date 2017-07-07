package com.example.sslab.samplegroupapplication.samples.customWidget;

import android.support.design.widget.CoordinatorLayout;
import android.view.View;

/**
 * Created by SSLAB on 2017-07-07.
 */

public class ViewOffsetBehavior <V extends View> extends CoordinatorLayout.Behavior<V>{

    private ViewOffsetHelper mViewOffsetHelper;
    private int mTempTopBottomOffset = 0;

}
