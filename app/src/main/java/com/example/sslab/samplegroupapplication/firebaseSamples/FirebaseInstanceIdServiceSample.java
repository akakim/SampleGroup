package com.example.sslab.samplegroupapplication.firebaseSamples;

import android.content.SharedPreferences;
import android.util.Log;

import com.example.sslab.samplegroupapplication.Manager.SharedManager;
import com.example.sslab.samplegroupapplication.common.Constants;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by SSLAB on 2017-02-24.
 */

public class FirebaseInstanceIdServiceSample extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        String token = FirebaseInstanceId.getInstance().getToken();
        SharedManager.setString( getApplicationContext(), Constants.FCM_TOKEN, token );
        Log.d(this.getClass().getSimpleName(), "FCM Token: " + token);

    }
}
