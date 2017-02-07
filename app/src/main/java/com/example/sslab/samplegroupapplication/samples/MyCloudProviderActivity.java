package com.example.sslab.samplegroupapplication.samples;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.sslab.samplegroupapplication.R;
import com.example.sslab.samplegroupapplication.provider.StorageProviderFragment;

public class MyCloudProviderActivity extends AppCompatActivity {

    private final String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cloud_provider);

        if(getSupportFragmentManager().findFragmentByTag(StorageProviderFragment.TAG) == null ){

        }
    }
}
