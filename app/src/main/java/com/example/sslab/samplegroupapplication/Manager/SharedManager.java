package com.example.sslab.samplegroupapplication.Manager;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.content.ContextCompat;

/**
 * Created by SSLAB on 2017-02-24.
 */

public class SharedManager {

    public static final String defaultName=  "SharedManager";
    public static void setString (Context context , String key, String value ){
        context.getSharedPreferences( defaultName , Context.MODE_PRIVATE ).edit().putString( key , value );
    }

    public static String getString( Context context , String key ){
        return context.getSharedPreferences( defaultName, Context.MODE_PRIVATE).getString( key,"" );
    }
}
