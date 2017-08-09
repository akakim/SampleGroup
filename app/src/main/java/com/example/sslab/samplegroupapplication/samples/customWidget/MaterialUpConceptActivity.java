package com.example.sslab.samplegroupapplication.samples.customWidget;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.example.sslab.samplegroupapplication.R;

public class MaterialUpConceptActivity extends AppCompatActivity implements AppBarLayout.OnOffsetChangedListener{


    private static final int PERCENTAGE_TO_ANIMATE_AVATAR = 20;
    private boolean mIsAvatarShown = true;

    private ImageView mProfile;
    private int mMaxScrollSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_up_concept);


        AppBarLayout appBarLayout = ( AppBarLayout ) findViewById( R.id.materialup_appbar);
        mProfile = ( ImageView ) findViewById( R.id.materialup_profile_image);

        Toolbar toolbar = (Toolbar) findViewById(R.id.materialup_toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                onBackPressed();
            }
        });
        appBarLayout.addOnOffsetChangedListener( this );
        mMaxScrollSize = appBarLayout.getTotalScrollRange();
    }

    public static void start(Context c) {
        c.startActivity(new Intent(c, MaterialUpConceptActivity.class));
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

        if (mMaxScrollSize == 0)
            mMaxScrollSize = appBarLayout.getTotalScrollRange();


        int percentage = (Math.abs(verticalOffset)) * 100 / mMaxScrollSize;

        // 프로필이 사라지는 애니메이션 설정
        if (percentage >= PERCENTAGE_TO_ANIMATE_AVATAR && mIsAvatarShown) {
            mIsAvatarShown = false;

            mProfile.animate()
                    .scaleY(0).scaleX(0)
                    .setDuration(200)
                    .start();
        }

        /// 프로필이 생기는 애니메이션 설정.

        if (percentage <= PERCENTAGE_TO_ANIMATE_AVATAR && !mIsAvatarShown) {
            mIsAvatarShown = true;

            mProfile.animate()
                    .scaleY(1).scaleX(1)
                    .start();
        }

    }
}
