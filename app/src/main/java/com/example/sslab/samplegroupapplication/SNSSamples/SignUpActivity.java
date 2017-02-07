package com.example.sslab.samplegroupapplication.SNSSamples;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.sslab.samplegroupapplication.R;
import com.kakao.auth.ApiResponseCallback;
import com.kakao.auth.ErrorCode;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeResponseCallback;
import com.kakao.usermgmt.response.model.UserProfile;
import com.kakao.util.helper.log.Logger;

import java.util.Map;

public class SignUpActivity extends AppCompatActivity {

    Intent getUserInformation;
    String profileUrl = "";
    String userName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        requestMe();
    }

    /**
     * 사용자의 상태를 알아 보기 위해 me API 호출을 한다.
     */
    protected void requestMe() {
        UserManagement.requestMe(new MeResponseCallback() {
            @Override
            public void onFailure(ErrorResult errorResult) {
                String message = "failed to get user info. msg=" + errorResult;
                Logger.d(message);

                ErrorCode result = ErrorCode.valueOf(errorResult.getErrorCode());
                if (result == ErrorCode.CLIENT_ERROR_CODE) {
                    Toast.makeText(getApplicationContext(), "service unvaluable", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    redirectLoginActivity();
                }
            }

            @Override
            public void onSessionClosed(ErrorResult errorResult) {
                redirectLoginActivity();
            }

            @Override
            public void onSuccess(UserProfile userProfile) {
                //   Log.v(TAG, "onSuccess");
                //   Logger.d("UserProfile : " + userProfile);


                String profileUrl = userProfile.getProfileImagePath();
                String userName = userProfile.getNickname();
                long id = userProfile.getId();
                String UUID = userProfile.getUUID();
                Map<String, String> properties = userProfile.getProperties();


                redirectGetUserInfoActivity(userProfile);
            }

            @Override
            public void onNotSignedUp() {
                showSignup();
            }
        });
    }


    protected void showSignup() {
        setContentView(R.layout.activity_login);

        final ExtraUserPropertyLayout extraUserPropertyLayout = (ExtraUserPropertyLayout) findViewById(R.id.extra_user_property);
        Button signupButton = (Button) findViewById(R.id.buttonSignup);
        signupButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                requestSignUp(extraUserPropertyLayout.getProperties());
            }
        });
    }
    private void requestSignUp(final Map<String, String> properties) {
        UserManagement.requestSignup(new ApiResponseCallback<Long>() {
            @Override
            public void onNotSignedUp() {
            }

            @Override
            public void onSuccess(Long result) {

                requestMe();
            }

            @Override
            public void onFailure(ErrorResult errorResult) {
                final String message = "UsermgmtResponseCallback : failure : " + errorResult;
                com.kakao.util.helper.log.Logger.w(message);
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                finish();
            }

            @Override
            public void onSessionClosed(ErrorResult errorResult) {
            }
        }, properties);
    }

    private void redirectLoginActivity(){
        final Intent intent = new Intent(this.getApplicationContext(), KakaoLoginSample.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
        finish();
    }
    private void redirectGetUserInfoActivity(UserProfile userProfile){

        Intent i = new Intent(this,GetUserInfoActivity.class);
//
//        properties.
//        database.execSQL( "insert into USER_INFO(id,nickname,profile_url) values ("+ String.valueOf(id)+ "," + username +"," +url+");" );

//        i.putExtra("ProfileURL",url);
//        i.putExtra("UserName",username);
//        i.putExtra("ID",id);
//        i.putExtra("UUID",UUID);


        i.putExtra( "kakao",userProfile );



//        i.putExtra("properties",properties);
        startActivity(i);
//        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

//    public void requestProfile() {
//        KakaoTalkService.requestProfile(new KakaoTalkResponseCallback<KakaoTalkProfile>() {
//            @Override
//            public void onSuccess(KakaoTalkProfile talkProfile) {
//                final String nickName = talkProfile.getNickName();
//                final String profileImageURL = talkProfile.getProfileImageURL();
//                final String thumbnailURL = talkProfile.getThumbnailURL();
//                final String countryISO = talkProfile.getCountryISO();
//            }
//        });
//    }

}
