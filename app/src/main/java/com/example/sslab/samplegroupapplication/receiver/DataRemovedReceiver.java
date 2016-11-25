package com.example.sslab.samplegroupapplication.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class DataRemovedReceiver extends BroadcastReceiver {
    public DataRemovedReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        String action = intent.getAction();

        switch (action){
            case Intent.EXTRA_DATA_REMOVED:
                break;
            case Intent.ACTION_PACKAGE_DATA_CLEARED:
                break;
        }

        throw new UnsupportedOperationException("Not yet implemented");
    }
}
