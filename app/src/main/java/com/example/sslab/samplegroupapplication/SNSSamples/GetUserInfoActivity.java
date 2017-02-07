package com.example.sslab.samplegroupapplication.SNSSamples;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.sslab.samplegroupapplication.R;
import com.facebook.AccessToken;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.kakao.auth.Session;
import com.kakao.usermgmt.response.model.UserProfile;

import java.util.ArrayList;

public class GetUserInfoActivity extends AppCompatActivity implements View.OnClickListener{
    ListView listView;
    ArrayAdapter adapter;
    ArrayList<String> arrayList = new ArrayList<>();
    UserProfile userProfile = UserProfile.loadFromCache();
    AccessToken accessToken;
    ProfileTracker profileTracker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_user_info);
        listView = ( ListView )findViewById( R.id.listView );
        Intent intent = getIntent();
        userProfile = (UserProfile) intent.getParcelableExtra("kakao");
        accessToken = (AccessToken) intent.getParcelableExtra("facebook");

        if (userProfile != null ) {
            arrayList.add(String.valueOf(userProfile.getId()));
            arrayList.add(String.valueOf(userProfile.getNickname()));
            arrayList.add(String.valueOf(userProfile.getProfileImagePath()));
            arrayList.add(String.valueOf(userProfile.getThumbnailImagePath()));
        }

        if ( accessToken != null ){
            profileTracker = new ProfileTracker() {
                @Override
                protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {

                }
            };
            arrayList.add(String.valueOf(accessToken.getApplicationId()));
            arrayList.add(String.valueOf(accessToken.getExpires()));
            arrayList.add(String.valueOf(accessToken.getLastRefresh()));
            arrayList.add(String.valueOf(accessToken.getUserId()));
            arrayList.add(String.valueOf(accessToken.getToken()));
        }
//        intent.getParcelableExtra()
        adapter = new ArrayAdapter( this,android.R.layout.simple_list_item_1,android.R.id.text1,arrayList );
        listView.setAdapter(adapter);

        findViewById( R.id.LogoutButton).setOnClickListener( this );
    }

    @Override
    public void onClick(View v) {
        switch ( v.getId() ){
            case R.id.LogoutButton:
                Session session = Session.getCurrentSession();
                if( session != null && session.isOpened() ){
                    session.close();
                }

                finish();
                break;
        }
    }
}
