package com.example.sslab.samplegroupapplication.samples.EfficientAndroidTreadEx;

import android.content.SharedPreferences;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.Process;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.sslab.samplegroupapplication.R;

public class SharedPreferencesActivity extends AppCompatActivity {

    TextView mTextValue = null;

    // UI 스레드에 대한 핸들러 . 백그라운드
    private Handler mUiHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if( msg.what == 0 ){
                Integer i = (Integer) msg.obj ;
                if(mTextValue != null){
                    mTextValue.setText( i + "" );
                }
            }
        }
    };

    private int mCount;
    private SharedPreferencesThread sharedPreferencesThread;

    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preferences);
        resultTextView = ( TextView ) findViewById( R.id.resultTextView );
        sharedPreferencesThread = new SharedPreferencesThread();

    }


    // shared Preference를 읽고 쓰는 백그라운드 스레드
    private class SharedPreferencesThread extends HandlerThread {
        private static final String KEY = "key";
        private SharedPreferences mPrefs ;
        private static final int READ = 1;
        private static final int WRITE = 2;


        private Handler mHandler = null;

        public SharedPreferencesThread() {
            super("SharedPreferenceTread", Process.THREAD_PRIORITY_BACKGROUND );
            mPrefs = getSharedPreferences("LocalPrefs", MODE_PRIVATE) ;
        }

        public SharedPreferencesThread(String name) {
            super(name, Process.THREAD_PRIORITY_BACKGROUND );
            mPrefs = getSharedPreferences("LocalPrefs", MODE_PRIVATE) ;
        }


        @Override
        protected void onLooperPrepared() {
            super.onLooperPrepared();

            mHandler = new Handler(getLooper()){
                @Override
                public void handleMessage(Message msg) {
                   switch ( msg.what ){
                       case READ :
                           mUiHandler.sendMessage( mUiHandler.obtainMessage(0, mPrefs.getInt(KEY, 0 )));
                           break;
                       case WRITE:
                            SharedPreferences.Editor editor = mPrefs.edit();
                           editor.putInt(KEY,(Integer)msg.obj );
                           editor.commit();
                           break;
                   }
                }
            };
        }

        public void read(){
            mHandler.sendEmptyMessage(READ);
        }

        public void write(int i){
            mHandler.sendEmptyMessage(WRITE);
        }

        @Override
        public void run() {
            super.run();
        }

        @Override
        public synchronized void start() {
            super.start();
        }
    }
}
