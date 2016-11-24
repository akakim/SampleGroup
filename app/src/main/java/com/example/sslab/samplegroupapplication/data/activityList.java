package com.example.sslab.samplegroupapplication.data;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by SSLAB on 2016-11-14.
 */
public class activityList {
    private String activityName;
    private Class aClass;

    public activityList(String activityName, Class aClass) {
        this.activityName = activityName;
        this.aClass = aClass;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public Class getaClass() {
        return aClass;
    }

    public void setaClass(Class aClass) {
        this.aClass = aClass;
    }
}
