package com.example.sslab.samplegroupapplication.samples.EfficientAndroidTreadEx;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.sslab.samplegroupapplication.R;

import java.util.Random;

public class HandlerExampleActivity extends AppCompatActivity {

    private final static int SHOW_PRGRESS_BAR = 1 ;
    private final static int HIDE_PRGRESS_BAR = 0 ;
    private BackgroundThread backgroundThread;

    TextView text1;
    ProgressBar progressBar2;
    Button triggerButton;

    // UI 스레드 핸들러
    private final Handler mUiHandler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            switch ( msg.what ){
                case SHOW_PRGRESS_BAR :
                    if( progressBar2 != null)
                        progressBar2.setProgress(View.VISIBLE);
                       break;
                case HIDE_PRGRESS_BAR :
                    if( text1 != null ){
                        text1.setText(String.valueOf(msg.arg1));
                    }
                    if( progressBar2 != null) {
                        progressBar2.setProgress(View.GONE);
                        progressBar2.postInvalidate();
                    }
                    break;

            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_example);

        backgroundThread = new BackgroundThread();

        /**
         * Handler ExampleActivity 가 생성될 때 메시지 큐를 포함한 백그라운드 스레드가 시작된다.
         * 백그라운드 스레드는 UI 스레드 테스크를 처리한다.
         *
         */
        backgroundThread.start();


        text1           = ( TextView ) findViewById( R.id.text1);
        progressBar2    = ( ProgressBar ) findViewById( R.id.progressBar2 );
        triggerButton   = ( Button ) findViewById( R.id.triggerButton );
        triggerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backgroundThread.doWork();
            }
        });
    }

    private class BackgroundThread extends Thread{

        private Handler mBackgroundHandler;
        @Override
        public synchronized void start() {
            super.start();
            Log.d(getClass().getSimpleName(),"Start Thread");
        }

        @Override
        public void run() {
            super.run();
            Log.d(getClass().getSimpleName(),"run Thread");

            // 루퍼를 스레드와 연결한다.
            Looper.prepare();
            Log.d(getClass().getSimpleName(),"prePare looper. Automatically connected message queue ");

            // 핸들러는 runnable만 처리한다. 따라서 Handler.hanlderMessage를 구현할 필요가 없다.
            mBackgroundHandler = new Handler ();
            Log.d(getClass().getSimpleName(),"to prevent ");

            Looper.loop();

        }

        public void doWork(){
            // 백그라운드로 실행될 긴 테스크를 보낸다.
            mBackgroundHandler.post(new Runnable() {
                @Override
                public void run() {
                    // ProgressBar를 포함하도록 객체를 생성한다.
                    Message uiMessage = mUiHandler.obtainMessage(
                            SHOW_PRGRESS_BAR, 0 ,0 , null);

                    //UI 스레드로 시작메세지를 보낸다.
                    mUiHandler.sendMessage ( uiMessage );

                    Random r = new Random();
                    int randomInt = r.nextInt( 5000 );
                    SystemClock.sleep(randomInt );

                    uiMessage = mUiHandler.obtainMessage( HIDE_PRGRESS_BAR,randomInt, 0, null );
                    mUiHandler.sendMessage( uiMessage );
                }
            });
        }

        public void exit(){
            mBackgroundHandler.getLooper().quit();
        }
    }
}
