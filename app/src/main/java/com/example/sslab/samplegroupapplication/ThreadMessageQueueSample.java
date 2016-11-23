package com.example.sslab.samplegroupapplication;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class ThreadMessageQueueSample extends AppCompatActivity {
    private String TAG = ThreadMessageQueueSample.class.getSimpleName();
    private int mCount= 0;
    TextView    mCountTextView = null;
    Handler  mHandler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"onCreate()");
        setContentView(R.layout.activity_sample01);
        mCountTextView = ( TextView )findViewById(R.id.threadView);

        // 10초당 1씩 카운트 하는 스레드 생성.
        Thread countThread = new Thread("Count Thread"){
            public void run(){
                for (int i =0;i < 10; i++){
                    mCount++;

                    Runnable callback = new Runnable() {
                        @Override
                        public void run() {
                            Log.d("RunnableCallBack","Count : "+ mCount);
                            mCountTextView.setText("Count : "+mCount);
                        }
                    };
                }
            }

        };
    }
}