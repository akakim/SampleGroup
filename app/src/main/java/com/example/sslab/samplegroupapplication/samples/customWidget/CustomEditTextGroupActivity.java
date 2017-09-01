package com.example.sslab.samplegroupapplication.samples.customWidget;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.sslab.samplegroupapplication.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomEditTextGroupActivity extends AppCompatActivity implements EditText.OnEditorActionListener{


    EditText edit;
    ScrollView rootScrollView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_edit_text_group);
        rootScrollView = ( ScrollView ) findViewById( R.id.rootScrollView);
        edit = ( EditText ) findViewById( R.id.edit);



        Log.d(getClass().getSimpleName(),"oncreate()");

        edit.setOnEditorActionListener( new TextView.OnEditorActionListener(){

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                Log.d("memoView", "getText" + v.getText().toString());

                Log.d("memoView", "action id " + actionId);
                Log.d("memoView", "getEvent " + event.getCharacters());


                Pattern pattern = Pattern.compile("\n");

                Matcher matcher = pattern.matcher(v.getText().toString());

                Log.d("groupCount ", "groupCount" + matcher.groupCount());
                if ( matcher.groupCount() > 4 ){
                    int height = edit.getMeasuredHeight();
                    edit.setHeight( height * 2 );
                    rootScrollView.requestLayout();
                }
                return false;
            }
        } );

    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {


        return false;
    }
}
