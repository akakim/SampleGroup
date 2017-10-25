package com.example.sslab.samplegroupapplication.bitmap;

import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;

import com.example.sslab.samplegroupapplication.fragment.ImageGridLayoutFragment;

public class BitmapAsynchTaskActivity extends FragmentActivity implements ImageGridLayoutFragment.OnFragmentInteractionListener {
    private static final String TAG = "BitmapAsynchTaskActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getSupportFragmentManager().findFragmentByTag(TAG) == null) {
            final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(android.R.id.content, new ImageGridLayoutFragment(), TAG);
            ft.commit();
        }

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
