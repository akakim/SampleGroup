package com.example.sslab.samplegroupapplication.widget;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Window;

import com.example.sslab.samplegroupapplication.R;

/**
 * Created by SSLAB on 2017-07-27.
 */

public class CustomProgressDialog extends Dialog {
    public CustomProgressDialog(@NonNull Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView( R.layout.custom_dialog);
    }
}
