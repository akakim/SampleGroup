package com.example.sslab.samplegroupapplication.samples.EfficientAndroidTreadEx;

import android.content.SharedPreferences;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.Process;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sslab.samplegroupapplication.R;

public class SharedPreferencesActivity extends AppCompatActivity implements View.OnClickListener{

    TextView mTextValue = null;
    Button updateButton = null;
    Button readButton = null;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preferences);
        mTextValue = ( TextView ) findViewById( R.id.resultTextView );
        updateButton = ( Button ) findViewById( R.id.updateButton );
        readButton = ( Button ) findViewById( R.id.readButton );

        updateButton.setOnClickListener( this );
        readButton.setOnClickListener( this );
        sharedPreferencesThread = new SharedPreferencesThread();
        sharedPreferencesThread.start();
        mCount = 0;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.updateButton:
                sharedPreferencesThread.write(mCount++);
                break;
            case R.id.readButton:
                sharedPreferencesThread.read();
                break;
        }
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
                           Toast.makeText(getApplicationContext(),"Key put in sharedPreference",Toast.LENGTH_SHORT).show();
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
            mHandler.sendMessage( Message.obtain(Message.obtain(mHandler,WRITE,i)) );
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
