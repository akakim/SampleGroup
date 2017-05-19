package com.example.sslab.samplegroupapplication.samples.EfficientAndroidTreadEx;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.MessageQueue;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.sslab.samplegroupapplication.R;

import java.util.Random;

public class LooperActivity extends AppCompatActivity implements View.OnClickListener{

    LooperThread mLooperThread;
    ConsumeAndQuitThread mCosumeAndQuitThread;
    Button testButton ;
    Button newThreadButton ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_looper);

        mLooperThread = new LooperThread(LooperActivity.this);
        mLooperThread.start();

        mCosumeAndQuitThread = new ConsumeAndQuitThread(LooperActivity.this);
        mCosumeAndQuitThread.start();


        testButton = ( Button ) findViewById( R.id. testButton);
        newThreadButton = ( Button ) findViewById( R.id. newThreadButton);
        testButton.setOnClickListener( this );
        newThreadButton.setOnClickListener( this );
        testButton.setText("testButton");
        newThreadButton.setText( " newThreadButton ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLooperThread.mHandler.getLooper().quit(); // 백스라운드 스레드 종료
        mCosumeAndQuitThread.mConsumerHandler.getLooper().quit(); // 백스라운드 스레드 종료

    }

    @Override
    public void onClick(View v) {
        switch ( v.getId()){
            case R.id.testButton:
                if(mLooperThread.mHandler != null ){
                    Message msg = mLooperThread.mHandler.obtainMessage( 0 );
                    mLooperThread.mHandler.sendEmptyMessage( 0 );
                }
            break;
            case R.id.newThreadButton:
                for ( int i = 0; i<10;i++){
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            for( int k = 0; k< 10 ; k++){
//                                Log.d(getClass().getSimpleName(), )
                                SystemClock.sleep(new Random().nextInt(10));
                                mCosumeAndQuitThread.enqueueData(k,"hello"+k);
                            }
                        }
                    }).run();
                }
                break;
        }

    }

    // 메시지 큐에서 소비자 역할 하는 작업자 스레드를 명시함.
    private static class LooperThread extends Thread{
        public Handler mHandler;
        Context mContext;

        LooperThread(Context context){
            super();
            this.mContext = context ;
        }
        @Override
        public void run(){
            Looper.prepare();   // 스레드와 루퍼를 연결하고 암시적으로 메세지 큐도 연결한다.

            /**
             * 큐에 메시지를 삽입하기 위한 생산자에 의해 사용되는 핸들러를 설정함
             */
            mHandler = new Handler(){
                @Override
                public void handleMessage(Message msg) {
                    switch (msg.what){
                        case 0:
                            doLongRunningOperation(String.valueOf(msg.getWhen()));
                            break;
                    }
                }

            };
            Looper.loop(); // 메시지 큐에서 소비자 스레드로 메시지 전달을 시작한다.
        }

        private void doLongRunningOperation(String timeStamp){
            // 긴테스크 내용을 추가한다.
            Toast.makeText(mContext,timeStamp + " in LooperThread",Toast.LENGTH_SHORT ).show();


        }
    }

    private static class ConsumeAndQuitThread extends Thread implements MessageQueue.IdleHandler{

        private static final String ThreadName = "ConsumeAndQuitThread";

        public Handler mConsumerHandler;
        boolean mIsFirstIdle = true;

        Context mContext;
        public ConsumeAndQuitThread(Context context){
            super( ThreadName );
            this.mContext = context ;
        }

        @Override
        public void run() {
            Looper.prepare();
            mConsumerHandler = new Handler(){
                @Override
                public void handleMessage(Message msg) {
                    // 데이터를 소비한다.
                    Toast.makeText(mContext,
                            "data : " + msg.what + " ThreadName " +msg.obj.toString() + '\n' +
                            "getMessageTimeStamp : " + msg.getWhen(),
                            Toast.LENGTH_SHORT
                    ).show();
                    Log.d(getClass().getSimpleName(),"data : " + msg.what + " ThreadName " +msg.obj.toString() );
                    Log.d(getClass().getSimpleName(),"getMessageTimeStamp : " + msg.getWhen());
                };
            };
            Looper.myQueue().addIdleHandler( this );
            Looper.loop();
        }

        @Override
        public boolean queueIdle() {
//            if(mIsFirstIdle){
//                mIsFirstIdle = false;
//                return true;
//            }
//            mConsumerHandler.getLooper().quit();
            return false;
        }

        public void enqueueData(int i ,String threadName){
            Message msg = mConsumerHandler.obtainMessage(i,threadName);
            mConsumerHandler.sendMessage( msg );
            Log.d(getClass().getSimpleName(),"enQueueData " + i + " " );
        }
    }
}
