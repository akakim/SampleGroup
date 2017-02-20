package com.example.sslab.samplegroupapplication.data;

import android.os.Bundle;

/**
 * Created by SSLAB on 2017-02-20.
 */

public class ActivityListBundleOption extends activityList {
    Bundle bundle;
    public ActivityListBundleOption(String activityName, Class aClass,Bundle bundle) {
        super(activityName, aClass);
        this.bundle = bundle;
    }

    public Bundle getBundle() {
        return bundle;
    }

    public void setBundle(Bundle bundle) {
        this.bundle = bundle;
    }
}
