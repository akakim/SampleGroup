package com.example.sslab.samplegroupapplication.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class ErrorReceiver extends BroadcastReceiver {
    public ErrorReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        String action = intent.getAction();
        switch (action){


        }

        throw new UnsupportedOperationException("Not yet implemented");




    }
}
