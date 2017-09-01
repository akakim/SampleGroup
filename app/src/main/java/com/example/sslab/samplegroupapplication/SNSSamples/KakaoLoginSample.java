package com.example.sslab.samplegroupapplication.SNSSamples;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sslab.samplegroupapplication.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.facebook.applinks.AppLinkData;
import com.kakao.auth.Session;
import com.kakao.auth.ISessionCallback;
import com.kakao.kakaolink.internal.AppActionInfo;
import com.kakao.kakaolink.v2.KakaoLinkResponse;
import com.kakao.message.template.LinkObject;
import com.kakao.kakaolink.v2.KakaoLinkService;
import com.kakao.message.template.ButtonObject;
import com.kakao.message.template.ContentObject;
import com.kakao.message.template.FeedTemplate;
import com.kakao.message.template.SocialObject;
import com.kakao.network.ErrorResult;
import com.kakao.network.callback.ResponseCallback;
import com.kakao.util.KakaoParameterException;
import com.kakao.util.exception.KakaoException;
import com.kakao.util.helper.log.Logger;
import com.kakao.kakaolink.*;
public class KakaoLoginSample extends AppCompatActivity implements View.OnClickListener{
    private final String TAG = this.getClass().getSimpleName();
    TextView hashText;
    private ISessionCallback callback;
    File imageFile;
    ImageView imageView;
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

        findViewById( R.id.kakaoSDKShareButton).setOnClickListener( this );

        imageView = ( ImageView ) findViewById( R.id.shareBitmapImageView );

        imageFile = new File (this.getExternalCacheDir()+"sample.png");

        new Thread(new Runnable() {
            @Override
            public void run() {
                if(imageFile.exists()){
                    Toast.makeText(KakaoLoginSample.this, "샘플파일이 존재합니다.", Toast.LENGTH_SHORT).show();
                }else {
                    try {
                        if(imageFile.createNewFile()){
                            Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.share_bitmap);
                            OutputStream outputStream = new FileOutputStream(imageFile);

                            bitmap.compress(Bitmap.CompressFormat.PNG,100,outputStream);
                            outputStream.close();
                        }
                    }catch (IOException e ){
                        Toast.makeText(KakaoLoginSample.this, "IO Exception occured 존재합니다.", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }catch (Exception e ){
                        Toast.makeText(KakaoLoginSample.this, "알수없는 에러 발생", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                }
            }
        }).run();
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
            case R.id.kakaoSDKShareButton:
                if (imageFile.exists()){

                    try {

                        FeedTemplate params = FeedTemplate.newBuilder(
                                        ContentObject.newBuilder("디저트 사진",
                                        "http://mud-kage.kakao.co.kr/dn/NTmhS/btqfEUdFAUf/FjKzkZsnoeE4o19klTOVI1/openlink_640x640s.jpg",
                                            LinkObject.newBuilder().setWebUrl("http://mud-kage.kakao.co.kr/dn/NTmhS/btqfEUdFAUf/FjKzkZsnoeE4o19klTOVI1/openlink_640x640s.jpg")
                                                                   .setMobileWebUrl("http://mud-kage.kakao.co.kr/dn/NTmhS/btqfEUdFAUf/FjKzkZsnoeE4o19klTOVI1/openlink_640x640s.jpg").build())
                                        .setDescrption("아메리카노, 빵, 케익")
                                        .build())
                                .setSocial( SocialObject.newBuilder().setLikeCount(10).setCommentCount(20) .setSharedCount(30).setViewCount(40).build())
                                .addButton(new ButtonObject("웹에서 보기", LinkObject.newBuilder().setWebUrl("'https://developers.kakao.com").setMobileWebUrl("'https://developers.kakao.com").build()))
                                .addButton(new ButtonObject("앱에서 보기", LinkObject.newBuilder()
                                        .setWebUrl("'https://developers.kakao.com")
                                        .setMobileWebUrl("'https://developers.kakao.com")
                                        .setAndroidExecutionParams("key1=value1")
                                        .setIosExecutionParams("key1=value1")
                                        .build()))
                                .build();

                        KakaoLinkService.getInstance().sendDefault(this, params, new ResponseCallback<KakaoLinkResponse>() {
                            @Override
                            public void onFailure(ErrorResult errorResult) {
                                Logger.e(errorResult.toString());
                            }

                            @Override
                            public void onSuccess(KakaoLinkResponse result) {

                            }
                        });




//
                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                }else {
                    Toast.makeText(this,"파일이 생성되어있지않다",Toast.LENGTH_SHORT).show();
                }
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
