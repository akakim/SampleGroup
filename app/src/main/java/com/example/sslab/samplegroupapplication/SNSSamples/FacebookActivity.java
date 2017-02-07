package com.example.sslab.samplegroupapplication.SNSSamples;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.sslab.samplegroupapplication.R;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

public class FacebookActivity extends AppCompatActivity {
    com.facebook.login.widget.LoginButton loginButton;
    com.facebook.CallbackManager callbackManager;
    com.facebook.login.LoginResult loginResultAcv;
    LoginManager loginManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facebook_login);
        FacebookSdk.sdkInitialize(getApplicationContext());
        loginButton = ( com.facebook.login.widget.LoginButton )findViewById( R.id.login_button );

        loginButton.setReadPermissions("email");
        callbackManager = com.facebook.CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
                Log.d("facebookCallback", "Success");
                Log.d("facbookCallback", loginResult.toString());

                loginResultAcv = loginResult;
                loginResultAcv.getAccessToken();
                Intent i = new Intent(FacebookActivity.this,GetUserInfoActivity.class );
                i.putExtra("facebook", loginResultAcv.getAccessToken());
                startActivity( i );
            }

            @Override
            public void onCancel() {
                // App code
                Log.d("onCancel", "");
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
                exception.printStackTrace();
//                Log.e("facebookException",exception.getMessage());
            }
        });
//        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
//            @Override
//            public void onSuccess(LoginResult loginResult) {
//                // App code
//                Log.d("facebookCallback","Success");
//                Log.d("facbookCallback",loginResult.toString());
//                loginResultAcv = loginResult;
//            }
//
//            @Override
//            public void onCancel() {
//                // App code
//                Log.d("onCancel","");
//            }
//
//            @Override
//            public void onError(FacebookException exception) {
//                // App code
//                exception.printStackTrace();
////                Log.e("facebookException",exception.getMessage());
//            }
//        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        callbackManager.onActivityResult(requestCode,resultCode,data);

    }


}
