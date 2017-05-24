package com.example.sslab.samplegroupapplication.samples.EfficientAndroidTreadEx;

import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.sslab.samplegroupapplication.R;

public class ThreadRetainWithFragmentActivity extends AppCompatActivity implements ThreadFragment.OnFragmentInteractionListener, View.OnClickListener{

    ThreadFragment tf = null;
    TextView resultTextView;
    Button networkSimulationButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_retain_with_fragment);




        FragmentManager fragmentManager = getSupportFragmentManager();

        tf = ( ThreadFragment ) fragmentManager.findFragmentByTag(tf.getClass().getSimpleName());
        if( tf == null) {
            tf = new ThreadFragment();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.add(R.id.fragmentContainer,tf,tf.getClass().getSimpleName());
            // add와 replace의 차이는 stack에 fragment에 대한 정보를  대체하느냐 아니면 그냥 더하느냐의 차이인거같다 .
            transaction.commit();
        }

        resultTextView = ( TextView ) findViewById( R.id.resultTextView);
        networkSimulationButton = ( Button ) findViewById( R.id.networkSimulationButton);
        networkSimulationButton.setOnClickListener( this );

    }



    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public void setText(final String text){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                resultTextView.setText( text );
            }
        });
    }
    @Override
    public void onClick(View v) {
        switch (v.getId() ){
            case R.id.networkSimulationButton:

                break;
        }
    }
}
