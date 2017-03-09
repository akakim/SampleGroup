package com.example.sslab.samplegroupapplication.samples.InspectorSamples;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.sslab.samplegroupapplication.R;

import java.util.List;

import io.fabric.sdk.android.Fabric;



public class SyncUserDataActivity extends AppCompatActivity {

    private final String COLOR_KEY = "favoriteColor";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Fabric.with(this);
        setContentView(R.layout.activity_sync_user_data);



    }

}
