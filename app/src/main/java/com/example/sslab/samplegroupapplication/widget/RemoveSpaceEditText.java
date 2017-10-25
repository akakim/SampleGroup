package com.example.sslab.samplegroupapplication.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

/**
 * Created by SSLAB on 2017-09-27.
 */

public class RemoveSpaceEditText extends EditText {
    OnFocusChangeListener onFocusChangeListener;

    public RemoveSpaceEditText(Context context) {
        super(context);
    }

    public RemoveSpaceEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RemoveSpaceEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);


    }

    public RemoveSpaceEditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
}
