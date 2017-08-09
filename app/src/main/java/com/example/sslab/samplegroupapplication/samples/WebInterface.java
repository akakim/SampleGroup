package com.example.sslab.samplegroupapplication.samples;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

/**
 * Created by SSLAB on 2017-04-28.
 */

public class WebInterface {
    Context mContext;

    WebInterface(Context context){
        mContext = context;
    }

    @JavascriptInterface
    public void showToast(String toast) {
        Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
    }

}
