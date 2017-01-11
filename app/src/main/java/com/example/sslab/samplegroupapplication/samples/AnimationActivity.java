package com.example.sslab.samplegroupapplication.samples;

import android.graphics.drawable.AnimationDrawable;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.sslab.samplegroupapplication.R;

public class AnimationActivity extends AppCompatActivity {


    ImageView imageView;
    AnimationDrawable animationDrawable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        findViewById( R.id.trigger ).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // startAnimation
//                        if( animationDrawable.isVisible() ) {
//                            animationDrawable.stop();
//                            animationDrawable.setVisible(false,true);
//                        }else {
//                            animationDrawable.setVisible(true,true);
//                            animationDrawable.start();
//                        }
                        animationDrawable.start();
                    }
                }
        );

        findViewById( R.id.stop ).setOnClickListener(
                new View.OnClickListener(){

                    @Override
                    public void onClick(View v) {
                        animationDrawable.stop();
                    }
                }
        );

        findViewById( R.id.invisible ).setOnClickListener(
                new View.OnClickListener(){

                    @Override
                    public void onClick(View v) {
                        imageView.setVisibility( View.GONE );
                    }
                }
        );

        findViewById( R.id.visible ).setOnClickListener(
                new View.OnClickListener(){

                    @Override
                    public void onClick(View v) {
                        imageView.setVisibility( View.VISIBLE );
                    }
                }
        );


        imageView = ( ImageView ) findViewById( R.id.imageView );
        imageView.setBackgroundResource( R.drawable.frame_animation );
        animationDrawable = ( AnimationDrawable ) imageView.getBackground();
    }

    public void showAniamation(){
        animationDrawable.start();

    }



    @Override
    protected void onStop() {
        super.onStop();
        imageView = null;
        animationDrawable = null;
    }
}
