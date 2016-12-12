package com.example.sslab.samplegroupapplication.samples;

import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.sslab.samplegroupapplication.R;
import com.example.sslab.samplegroupapplication.fragment.SampleSwipeBotttomLayer;
import com.example.sslab.samplegroupapplication.Constants;
public class SwipeRefreshBottomLayoutActivity extends AppCompatActivity implements SampleSwipeBotttomLayer.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_refresh_bottom_layout);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();


        ft.replace(R.id.container_layout,new SampleSwipeBotttomLayer(), Constants.TAG_SAMPLE_SWIPE_BOTTOM);

        ft.commit();


    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
