package com.example.sslab.samplegroupapplication.SNSSamples;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.sslab.samplegroupapplication.R;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.kakao.auth.Session;
import com.kakao.auth.ISessionCallback;
import com.kakao.util.exception.KakaoException;
import com.kakao.util.helper.log.Logger;

public class KakaoLoginSample extends AppCompatActivity implements View.OnClickListener{
    private final String TAG = this.getClass().getSimpleName();
    TextView hashText;
    private ISessionCallback callback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kakao_login_sample);

        callback = new SessionCallback();

        Session.getCurrentSession().addCallback(callback);

        if ( !Session.getCurrentSession().checkAndImplicitOpen() ){
            setContentView(R.layout.activity_kakao_login_sample);
            initView();

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        setContentView(R.layout.activity_kakao_login_sample);
        initView();

        if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {
            return;
        }


        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,"onStop");

    }

    @Override
    protected void onDestroy() {
        Log.d(TAG,"onDestroy");
        Session.getCurrentSession().removeCallback(callback);
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch ( v.getId() ){
            case R.id.hashButton :
                hashText.setText ( getAppKeyHash () );
                break;
        }
    }

    private void initView(){
        findViewById( R.id.hashButton ).setOnClickListener( this );
        hashText = ( TextView ) findViewById( R.id.hashText );
    }

    /**
     * Kakao와 연동하기 위해 Hash 키 값을 얻기 위한 method.
     * 원래는 keytool이라는 cmd를 입력해서 구했지만 정확하게 나오지않아서
     *
     * 이 메소드를 사용해 Hash 키 값을 Log로 찍은 후 개발자센터에 등록.
     */
    private String getAppKeyHash() {
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md;

                md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String something = new String(Base64.encode(md.digest(), 0));
                System.out.println("HASH  " + something);
//                showSignedHashKey(something);
                Log.v("getKey ",something);
                return something;
            }
        } catch (PackageManager.NameNotFoundException e1) {
            // TODO Auto-generated catch block
            Log.e("name not found", e1.toString());
            return "name not found";
        } catch (NoSuchAlgorithmException e) {

            Log.e("no such an algorithm", e.toString());
            return "no such an algorithm";
        } catch (Exception e) {
            Log.e("exception", e.toString());
            return "something Error Occured";
        }
        return "";
    }

    private void redirectSignupActivity(){
        Intent i = new Intent(this, SignUpActivity.class );
        i.setFlags( Intent.FLAG_ACTIVITY_SINGLE_TOP ) ;
        startActivity( i );
    }
    private class SessionCallback implements ISessionCallback{
        public void onSessionOpened() {
            Log.v(TAG,"SessionCallBack"+ "onSessionOpen()");
            finish();
            redirectSignupActivity();
        }

        @Override
        public void onSessionOpenFailed(KakaoException exception) {
            if(exception != null) {
                Logger.e(exception);
            }
            Log.v(TAG,"SessionCallBack" + "onSessionOpenFailed()");
            setContentView(R.layout.activity_kakao_login_sample);
            initView();
        }
    }
}
