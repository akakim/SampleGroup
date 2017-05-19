package com.example.sslab.samplegroupapplication.samples.EfficientAndroidTreadEx;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.support.annotation.Nullable;

/**
 * Created by SSLAB on 2017-05-19.
 */

public class WorkerThreadService extends Service {

    WorkerThread mWorker ;
    Messenger mMessenger;
    @Override
    public void onCreate() {
        super.onCreate();

        mWorker = new WorkerThread();               // 서비스 생성시 시작된 스레드에서 처리된다.
                                                    // 바인딩된 모든 클라이언트는 같은 작업자 스레드를 사용한다.
        mWorker.start();
    }

    //클라이언트가 서비스안에서 연관된 핸들러와 통신할 수 있도록,
    // 바인딩된 클라이언트는 신저의 IBinder객체를 받는다.
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        synchronized ( this ){
            while (mMessenger == null){
                try{
                    wait();
                } catch (InterruptedException e ){
                    mMessenger = null;
                    e.printStackTrace();
                }
            }
        }
        return mMessenger.getBinder();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();

        mWorker.quite();
    }

    private class WorkerThread extends Thread{

        Handler mHandler;
        @Override
        public void run() {
            Looper.prepare();
            mHandler = new Handler(){
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                }
            };

            onWorkerPrepared();
            Looper.loop();
        }

        public void quite(){
            mHandler.getLooper().quit();
        }
    }

    /**
     * 작업자 스레드가 루퍼와 핸들러를 준비했다.
     */
    public void onWorkerPrepared(){
        mMessenger = new Messenger( mWorker.mHandler);  // 작업자 스레드에 대한 핸들러는 생성시 메신저에 연결된다.
        // 이 핸들러는 클라이언트 프로세스에서 들어오는 메세지를 처리한다.
        synchronized (this){
            notifyAll();
        }
    }
}
