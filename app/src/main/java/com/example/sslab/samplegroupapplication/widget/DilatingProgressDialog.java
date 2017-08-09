package com.example.sslab.samplegroupapplication.widget;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Window;

import com.example.sslab.samplegroupapplication.R;

/**
 * Created by SSLAB on 2017-07-28.
 */

public class DilatingProgressDialog extends Dialog {
    DilatingDotsProgressBar dilatingProgressBar;

    public DilatingProgressDialog(@NonNull Context context) {
        this(context,-1);
    }

    public DilatingProgressDialog(Context context, int theme) {
        super(context, theme);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView( R.layout.dilating_progress_bar);
        dilatingProgressBar = (DilatingDotsProgressBar)findViewById(R.id.dilatingProgressBar);
        setCancelable(true);
    }

    @Override
    public void show() {
        dilatingProgressBar.showNow();

        super.show();
    }

    @Override
    public void dismiss() {
        dilatingProgressBar.hide();
        super.dismiss();
    }
}
