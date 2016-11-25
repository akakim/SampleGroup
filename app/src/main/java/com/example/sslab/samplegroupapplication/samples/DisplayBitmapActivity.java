package com.example.sslab.samplegroupapplication.samples;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.sslab.samplegroupapplication.R;

public class DisplayBitmapActivity extends FragmentActivity {
    private static final String TAG = "DisplayBitmapActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getSupportFragmentManager().findFragmentByTag(TAG) == null){
            final FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//            fragmentTransaction.add(android.R.id.content, new ImageGridLayoutFragment(),TAG);
//            fragmentTransaction.commit();
        }

    }
}
