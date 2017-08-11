package com.example.sslab.samplegroupapplication.samples.mvp;

import android.os.Handler;
import android.text.TextUtils;

/**
 * Created by SSLAB on 2017-08-09.
 */

public class LoginInteractorImpl implements LoginInteractor {
        @Override
    public void login(final String username, final String password, final OnLoginFinishedListener listener) {

       new Handler().postDelayed(new Runnable() {
           @Override
           public void run() {
                boolean error = false;
               if(TextUtils.isEmpty( username )){
                   listener.onUserNameError();
                   error = true;
                   return;
               }

               if(TextUtils.isEmpty( password )){
                   listener.onPasswordError();
                   error = true;
                   return;
               }

               if( !error ){
                   listener.onSuccess();
               }

           }
       },2000);
    }
}
