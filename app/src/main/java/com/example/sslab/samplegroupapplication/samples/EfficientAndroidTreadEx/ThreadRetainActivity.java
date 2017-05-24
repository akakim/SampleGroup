package com.example.sslab.samplegroupapplication.samples.EfficientAndroidTreadEx;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.sslab.samplegroupapplication.R;

public class ThreadRetainActivity extends AppCompatActivity implements View.OnClickListener {


    TextView resultTextView;
    Button networkSimulationButton;
    MyThread t;

    private static class MyThread extends Thread{
        private ThreadRetainActivity mThreadRetainActivity;


        public MyThread(ThreadRetainActivity mThreadRetainActivity) {
            this.mThreadRetainActivity = mThreadRetainActivity;
        }

        private void attach(ThreadRetainActivity mThreadRetainActivity) {
            this.mThreadRetainActivity = mThreadRetainActivity;
        }

        @Override
        public void run() {
            final String text = getTextFromNetwork();
            mThreadRetainActivity.setText(text);
        }


        // 긴 동작
        private String getTextFromNetwork(){
            SystemClock.sleep(5000);

            return getName() + " : " + System.currentTimeMillis();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_retain);
        resultTextView = ( TextView ) findViewById( R.id.resultTextView);
        networkSimulationButton = ( Button ) findViewById( R.id.networkSimulationButton);
        networkSimulationButton.setOnClickListener( this );

        Object retainedObject = getLastNonConfigurationInstance();
        if( retainedObject != null ){
            t = ( MyThread ) retainedObject ;
            t.attach( this );
        }
    }

    @Override
    public Object onRetainCustomNonConfigurationInstance() {

        if( t != null && t.isAlive() ){
            return t;
        }
        return null;
    }

    @Override
    public void onClick(View v) {
        switch ( v.getId() ){
            case R.id.networkSimulationButton :
                t = new MyThread ( this ) ;
                t.start();
                 break;
        }
    }

    public void setText(final String text){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                resultTextView.setText( text );
            }
        });
    }
}
