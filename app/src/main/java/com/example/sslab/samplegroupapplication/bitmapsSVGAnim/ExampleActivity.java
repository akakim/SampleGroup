package com.example.sslab.samplegroupapplication.bitmapsSVGAnim;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sslab.samplegroupapplication.R;

public class ExampleActivity extends AppCompatActivity implements View.OnClickListener{
    TextView example;
    TextView rotationOnly;
    TextView path;
    TextView custom;

    final String TAG = this.getClass().getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);
        rotationOnly = ( TextView )findViewById( R.id.secondTextView);
        path = ( TextView )findViewById( R.id.thirdTextView);
        custom = ( TextView )findViewById( R.id.forthTextView);
        example = ( TextView )findViewById( R.id.firstTextView);

        rotationOnly.setOnClickListener( this );
        path.setOnClickListener( this );
        custom.setOnClickListener( this );
        example.setOnClickListener( this );

    }

    @Override
    public void onClick(View v) {
        Drawable[] drawables = null;
        switch ( v.getId() ){
            case R.id.firstTextView :
                drawables = example.getCompoundDrawables();
                break;
            case R.id.secondTextView :
                drawables =  rotationOnly.getCompoundDrawables();
                break;
            case R.id.thirdTextView :
                drawables = path.getCompoundDrawables();
                break;
            case R.id.forthTextView :
                drawables = custom.getCompoundDrawables();
                break;
        }
        if( drawables != null ){
            int i = 0;
            for (final Drawable drawable : drawables ){

                if(drawable != null ) {
                    Log.d(TAG,"drawable : "+i +" animation Start ");
                    ((Animatable) drawable).start();

                }
                else {
                    Log.d(TAG,"drawable : "+i +" animation is null ");

                }
                i++;
            }
        }
    }
}
