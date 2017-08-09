package com.example.sslab.samplegroupapplication.samples.customWidget;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.sslab.samplegroupapplication.R;

public class IOActivityExample extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ioexample);
    }

    public static void start(Context c) {
        c.startActivity(new Intent(c, IOActivityExample.class));
    }

}
