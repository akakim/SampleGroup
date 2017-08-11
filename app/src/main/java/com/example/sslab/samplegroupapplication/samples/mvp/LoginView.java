package com.example.sslab.samplegroupapplication.samples.mvp;

/**
 * 그러니까 로그인 화면에서 정책 에러가날땐 어떻게 하고 등등..
 */

public interface LoginView {

    void showProgress();
    void hideProgress();
    void setUserNameError();
    void setPasswordError();
    void navigateToHome();
}
