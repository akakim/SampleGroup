package com.example.sslab.samplegroupapplication.samples.InspectorSamples;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.sslab.samplegroupapplication.BaseActivity;
import com.example.sslab.samplegroupapplication.R;

import org.json.JSONObject;

public class DevFocusSampleActivity extends BaseActivity implements View.OnTouchListener,View.OnFocusChangeListener{


    Button button1,button2;
    EditText editText1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dev_focus_sample);
        button1 = ( Button )findViewById( R.id.button1 );
        button2 = ( Button )findViewById( R.id.button2 );
        editText1 = ( EditText ) findViewById( R.id.editText1 );


        button1.setFocusable( true );
        button2.setFocusable( true );
        button1.setOnFocusChangeListener( this );
        button2.setOnFocusChangeListener( this );
        editText1.setOnFocusChangeListener( this );


    }

    @Override
    public void responseData(JSONObject jsonObject) {

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if( hasFocus ){
            Log.d("hasFocused",(String )v.getTag());
        }else {
            Log.d("hasFocusout ", (String) v.getTag());
        }
    }
}
