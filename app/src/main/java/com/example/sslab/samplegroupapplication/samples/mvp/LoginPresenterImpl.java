package com.example.sslab.samplegroupapplication.samples.mvp;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

/**
 * Created by SSLAB on 2017-08-09.
 */

public class LoginPresenterImpl implements LoginPresenter,LoginInteractor.OnLoginFinishedListener {


    private LoginView loginView;
    private LoginInteractor loginInteractor;


    public LoginPresenterImpl(LoginView loginView) {
        this.loginView = loginView;
        this.loginInteractor = new LoginInteractorImpl();
    }

    @Override
    public void validCredentials(String username, String password) {
        if( loginView != null ){
            loginView.showProgress();
        }

        loginInteractor.login(username,password,this);
    }

    @Override
    public void onDestroy() {
        loginView = null;
    }

    @Override
    public void onUserNameError() {
        if( loginView != null ){
            loginView.setUserNameError();
            loginView.hideProgress();
        }
    }

    @Override
    public void onPasswordError() {

        if (loginView != null) {
            loginView.setPasswordError();
            loginView.hideProgress();
        }

    }

    @Override
    public void onSuccess() {

        if (loginView != null) {
            loginView.navigateToHome();
        }

    }

}
