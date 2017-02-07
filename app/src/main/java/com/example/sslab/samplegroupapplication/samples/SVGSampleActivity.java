package com.example.sslab.samplegroupapplication.samples;

import android.content.res.Resources;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.os.Build;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.sslab.samplegroupapplication.R;

import java.util.Vector;

/**
 * wishlist
 * svg를 이용한 애니메이션 구현
 * Notification에
 */
public class SVGSampleActivity extends AppCompatActivity implements View.OnClickListener{

    Resources res = null;
    Drawable drawable = null;
    VectorDrawableCompat vectorDrawable = null;
    BitmapDrawable bitmapDrawable = null;
    ImageView sampleButton = null;
    AnimatedVectorDrawable animatedVectorDraable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_svgsample);

        sampleButton = ( ImageView )findViewById( R.id.sample_image_button );
        res = getResources();
        drawable = res.getDrawable(R.drawable.ic_list_black_24dp,null);

        if(Build.VERSION.SDK_INT >=  Build.VERSION_CODES.LOLLIPOP ){
            vectorDrawable = ( VectorDrawableCompat ) sampleButton.getDrawable() ;
        } else {
            bitmapDrawable = ( BitmapDrawable ) sampleButton.getDrawable() ;
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sample_image_button :
                int alpha = vectorDrawable.getAlpha();
                break;
        }
    }
}
