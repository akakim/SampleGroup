package com.example.sslab.samplegroupapplication.samples.mvp;

/**
 * Created by SSLAB on 2017-08-09.
 */

public interface LoginPresenter {
    void validCredentials(String username, String password);
    void onDestroy();

}
