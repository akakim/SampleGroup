package com.example.sslab.samplegroupapplication.testActivity;

import android.support.v7.app.AppCompatActivity;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;

/**
 * Created by SSLAB on 2016-12-20.
 */

public class AndroidTestActivityTest extends ActivityInstrumentationTestCase2<AndroidTestActivity> {
    public AndroidTestActivityTest(Class<AndroidTestActivity> activityClass) {
        super(activityClass);
    }

    public void testHelloString(){
        AppCompatActivity activity = getActivity();

    }
}
