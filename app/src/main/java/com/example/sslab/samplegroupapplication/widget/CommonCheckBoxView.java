package com.example.sslab.samplegroupapplication.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.widget.TextView;
import android.view.View;

/**
 * Created by SSLAB on 2017-06-29.
 */

public class CommonCheckBoxView extends AppCompatTextView implements View.OnClickListener {
    public CommonCheckBoxView(Context context) {
        super(context);

    }

    public CommonCheckBoxView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CommonCheckBoxView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onClick(View v) {

    }
}
