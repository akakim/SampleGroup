package com.example.sslab.samplegroupapplication.popup;

import android.content.Context;

import com.example.sslab.samplegroupapplication.common.BaseDialog;

/**
 * Created by SSLAB on 2016-12-16.
 */

public class PopupNotification extends BaseDialog {


    public PopupNotification(Context context) {
        super(context);
    }

    public PopupNotification(Context context, int theme) {
        super(context, theme);
    }

    public PopupNotification(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }
}
