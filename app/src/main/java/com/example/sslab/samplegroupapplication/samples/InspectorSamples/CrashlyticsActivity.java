package com.example.sslab.samplegroupapplication.samples.InspectorSamples;

import android.os.Debug;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.answers.Answers;
import com.crashlytics.android.answers.CustomEvent;
import com.crashlytics.android.core.CrashlyticsCore;
import com.example.sslab.samplegroupapplication.BuildConfig;
import com.example.sslab.samplegroupapplication.R;

import io.fabric.sdk.android.Fabric;

public class CrashlyticsActivity extends AppCompatActivity implements View.OnClickListener{

    Crashlytics crashlytics;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crashlytics);

//        Crashlytics crashlytics = new Crashlytics.Builder()
//                .core(new CrashlyticsCore.Builder().disabled(BuildConfig.DEBUG).build())
//                .build();

        Fabric.with(this,new Crashlytics());

//        logUser();


        findViewById(R.id.forceCrash).setOnClickListener(this);
        findViewById(R.id.onKeyMetric).setOnClickListener(this);
        findViewById(R.id.customCrashLog).setOnClickListener( this );
        findViewById(R.id.customKeysLog).setOnClickListener( this );


    }

    /**
     * Exception을 강제로 일으킨다.
     */
    public void forceCrash() {
        throw new RuntimeException("This is a crash");
    }
    private void logUser() {
        // TODO: Use the current user's information
        // You can call any combination of these three methods
        Crashlytics.setUserIdentifier("get Users Identitiy");
        Crashlytics.setUserEmail("daphne5009@gmail.com");
        Crashlytics.setUserName("PRIMARY");
        Crashlytics.setDouble("setDouble", 2.2222);
        Crashlytics.setDouble("value ", 2.505050);
        Crashlytics.setInt("getColor",40);

        throw new RuntimeException("modifiedCrashLogUser ");
    }

    private void customCrashLog(){

        Crashlytics.log("customCrashlytics Log");
        Crashlytics.log(3,"TAG","priority zero");

        Crashlytics.log(3,"TAG","priority Debug");
        throw new RuntimeException("modifiedCustomCrashLog ");

    }

    // TODO: Move this method and use your own event name to track your key metrics
    public void onKeyMetric() {
        // TODO: Use your own string attributes to track common values over time
        // TODO: Use your own number attributes to track median value over time
        Answers.getInstance().logCustom(new CustomEvent("Video Played")
                .putCustomAttribute("Category", "Comedy")
                .putCustomAttribute("Length", 350));

    }

    @Override
    public void onClick(View v) {
        try {
            switch (v.getId()){
                case R.id.forceCrash:
                    forceCrash();
                    break;
                case R.id.onKeyMetric:
                    onKeyMetric();
                    break;
                case R.id.customCrashLog:
                    customCrashLog();
                    break;
                case R.id.customKeysLog:
                    logUser();
                    break;
            }
        }catch (Exception e ){

            e.printStackTrace();
            Crashlytics.logException(e);
//            Crashlytics.crash();
        }
    }
}
