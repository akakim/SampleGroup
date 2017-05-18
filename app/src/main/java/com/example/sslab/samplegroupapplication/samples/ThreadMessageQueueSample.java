package com.example.sslab.samplegroupapplication.samples;

import android.os.Handler;
import android.os.HandlerThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sslab.samplegroupapplication.R;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.util.concurrent.TimeUnit;

/**
 * PipExampleActivity 실습 예제
 */
public class ThreadMessageQueueSample extends AppCompatActivity {
    private String TAG = ThreadMessageQueueSample.class.getSimpleName();
    private int mCount= 0;
    TextView    mCountTextView = null;
    Handler  mHandler = new Handler();
    WorkerThread workerThread;

    EditText pipeEditText;
    PipedReader pipedReader;
    PipedWriter pipedWriter;

    private Thread worker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        pipedReader = new PipedReader();
        pipedWriter = new PipedWriter();

        try {
            pipedWriter.connect(pipedReader);
        }catch (Exception e ){
            e.printStackTrace();
        }
        setContentView(R.layout.activity_sample01);

        Log.d(getClass().getSimpleName(),"pipeReader : " + pipedReader.hashCode() );
        Log.d(getClass().getSimpleName(),"pipedWriter : " + pipedWriter.hashCode() );

        pipeEditText = ( EditText )findViewById( R.id.pipeEditText);
        pipeEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try{
                    // 추가되는 문자열만 처리한다.
                    if (count > before){
                        // 마지막에 입력된 문자를 파이프로 쓴다.
                        pipedWriter.write(s.subSequence(before,count).toString());
                    }else if(count < before ){
                        if(count >= 1)
                            pipedWriter.write(s.charAt(count -1 ));
                    }else {
                        if(count >= 1)
                            pipedWriter.write(s.charAt(count));
                    }
                }catch( Exception e ){
                    e.printStackTrace();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        worker = new Thread( new TextHandlerTask(pipedReader));
        worker.start();
//        workerThread = new WorkerThread("workerThread1");
//        Runnable task = new Runnable() {
//            @Override
//            public void run() {
//
//                for(int i =0;i<4;i++){
//                try{
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
//        };
//
//        workerThread.start();
//        workerThread.run();
//        workerThread.postTask(task);
//        workerThread.postTask(task);



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
//        workerThread.quit();
        mHandler = null;
        super.onDestroy();
    }

    private static class TextHandlerTask implements Runnable{

        private final PipedReader reader;
        TextHandlerTask(PipedReader reader){
            this.reader = reader;
            Log.d(getClass().getSimpleName(),"reader : " + reader.hashCode() );
        }
        @Override
        public void run() {
            while( !Thread.currentThread().isInterrupted() ){
                try{
                    int i ;
                    while( (i = reader.read() ) != -1){
                        char c=  (char) i;
                        // 여기에 문자처리 로직을 추가.
                        Log.d(getClass().getSimpleName(),"Char "+ c);
                    }
                }catch (IOException e ){
                    e.printStackTrace();
                }
            }
        }
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