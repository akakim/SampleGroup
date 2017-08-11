package com.example.sslab.samplegroupapplication.samples.mvp;

/**
 * Created by SSLAB on 2017-08-09.
 */

public interface LoginInteractor {

    interface OnLoginFinishedListener{
        void onUserNameError();

        void onPasswordError();

        void onSuccess();
    }

    void login(String username,String password, OnLoginFinishedListener listener );
}
