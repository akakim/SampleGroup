package com.example.sslab.samplegroupapplication.samples.servicePackage;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;


/**
 * 시뮬레이션과같은 예제인데 생각만큼 돌아가진않는다....
 */

public class WorkerThreadService  extends Service {

    WorkerThread workerThread;
    Messenger messenger;

    final String TAG = getClass().getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();

        workerThread = new WorkerThread();

        workerThread.start();

        // 메세지는 서비스 생성시  시작된 스레드에서 처리된다.
        // 바인딩된 모든 클라이언트는 같은 작업자 스레드를 사용한다.
    }


    /**
     * 작업자 스레드가 루퍼와 핸들러를 준비했다.
     */
    private void onWorkerPrepared() {
        Log.d(TAG,"onWorkerPrepared 작업자스레드에서 작업중 ");
        messenger = new Messenger(workerThread.handler);
        synchronized (this) {
            notifyAll();
        }

        // 잡업자 스레드에 대한 핸들러는 생성시 메신저에 연결된다.
        // 이 핸들러는 클라이언트 프로세스에서 들어오는 메세지를 처리한다.
        // 근데 왜 내부 클래스에 선언을 안했지 ??????
        // 나중에 따로 분리해서 실질적으로 만드는 예제는 없는건가??
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG,"onBind getAction : " + intent.getAction() );
        synchronized (this){
            while (messenger == null){
                try{
                    wait();
                }catch (InterruptedException e){

                }
            }
        }

        return messenger.getBinder();
    }


    @Override
    public void onDestroy() {

        super.onDestroy();
        workerThread.quite();
    }

    class WorkerThread extends Thread{
        public Handler handler;

        @Override
        public void run() {
            Looper.prepare();
            handler= new Handler(){
                @Override
                public void handleMessage(Message msg) {
                    switch (msg.what){
                        case 1 :
                            try{
                                msg.replyTo.send(Message.obtain(null,msg.what,0,0));
                            }catch (RemoteException e ){
                                e.printStackTrace();
                            }
                            break;
                        case 2:
                            Log.e(TAG,"error coccured");
                            Toast.makeText(getApplicationContext(),"handle Message",Toast.LENGTH_SHORT).show();
                            break;
                        default:
                            Log.e(TAG,"error coccured");
                            Toast.makeText(getApplicationContext(),"default Message",Toast.LENGTH_SHORT).show();
                            break;
                    }
                    // ,메세지 구형
                    Log.d("WorkerThread","workger");
                }
            };
            onWorkerPrepared();
            Looper.loop();


        }

        public void quite(){
            handler.getLooper().quit();
        }
    }

}
