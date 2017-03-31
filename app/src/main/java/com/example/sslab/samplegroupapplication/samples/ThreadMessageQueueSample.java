package com.example.sslab.samplegroupapplication.samples;

import android.os.Handler;
import android.os.HandlerThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sslab.samplegroupapplication.R;

import java.util.concurrent.TimeUnit;

public class ThreadMessageQueueSample extends AppCompatActivity {
    private String TAG = ThreadMessageQueueSample.class.getSimpleName();
    private int mCount= 0;
    TextView    mCountTextView = null;
    Handler  mHandler = new Handler();
    WorkerThread workerThread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"onCreate()");
        setContentView(R.layout.activity_sample01);

        workerThread = new WorkerThread("workerThread1");
        Runnable task = new Runnable() {
            @Override
            public void run() {

                for(int i =0;i<4;i++){
                try{
                        TimeUnit.SECONDS.sleep(2);
                    }catch (InterruptedException e){
                        Log.e("InterruptedException",e.getMessage());
                        e.printStackTrace();
                    }

                    if( i == 2 ){
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(ThreadMessageQueueSample.this,
                                        "i'm at the middle of background task",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }

                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(ThreadMessageQueueSample.this,
                                "Background task is completed",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });
            }
        };

        workerThread.start();
        workerThread.run();
        workerThread.postTask(task);
        workerThread.postTask(task);
//        mCountTextView = ( TextView )findViewById(R.id.threadView);
//
//        // 10초당 1씩 카운트 하는 스레드 생성.
//        Thread countThread = new Thread("Count Thread"){
//            public void run(){
//                for (int i =0;i < 10; i++){
//                    mCount++;
//
//                    Runnable callback = new Runnable() {
//                        @Override
//                        public void run() {
//                            Log.d("RunnableCallBack","Count : "+ mCount);
//                            mCountTextView.setText("Count : "+mCount);
//                        }
//                    };
//                }
//            }
//
//        };

// 1회성 스레드이다.
//        Thread myThread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for(int i =0;i<4;i++){
//                    try{
//                        TimeUnit.SECONDS.sleep(2);
//                    }catch (InterruptedException e){
//                        Log.e("InterruptedException",e.getMessage());
//                        e.printStackTrace();
//                    }
//
//                    if( i == 2 ){
//                        mHandler.post(new Runnable() {
//                            @Override
//                            public void run() {
//                                Toast.makeText(ThreadMessageQueueSample.this,
//                                        "i'm at the middle of background task",
//                                        Toast.LENGTH_SHORT).show();
//                            }
//                        });
//                    }
//                }
//
//                mHandler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        Toast.makeText(ThreadMessageQueueSample.this,
//                                "Background task is completed",
//                                Toast.LENGTH_LONG)
//                                .show();
//                    }
//                });
//            }
//        });
//
//        myThread.start();

        // reuseable thread ..


    }

    @Override
    protected void onDestroy() {
        // reme
        workerThread.quit();
        mHandler = null;
        super.onDestroy();
    }

    private class WorkerThread extends HandlerThread{

        // looper를 관리하리 하지않고 해결할때사용
        private Handler mWorkerHandler;

        public WorkerThread(String name) {
            super(name);
        }

        public WorkerThread(String name, int priority) {
            super(name, priority);
        }

        public void postTask(Runnable task){
            mWorkerHandler.post(task);
        }

        @Override
        protected void onLooperPrepared() {
            super.onLooperPrepared();
            mWorkerHandler = new Handler(getLooper());
        }

        @Override
        public void run() {
            super.run();
        }
    }
}