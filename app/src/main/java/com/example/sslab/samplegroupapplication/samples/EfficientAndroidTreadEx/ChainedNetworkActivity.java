package com.example.sslab.samplegroupapplication.samples.EfficientAndroidTreadEx;

import android.app.Dialog;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.Process;
import android.os.SystemClock;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.sslab.samplegroupapplication.R;

import cz.msebera.android.httpclient.impl.client.SystemDefaultHttpClient;

public class ChainedNetworkActivity extends AppCompatActivity {


    private static final int DIALOG_LOADING = 0;

    private static final int SHOW_LOADING = 1;

    private static final int DISMISS_LOADING = 2;

    Handler dialogHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what){
                case SHOW_LOADING:
                    showDialog(DIALOG_LOADING);
                    break;
                case DIALOG_LOADING:
                    showDialog(DIALOG_LOADING);
                    break;
            }
        }
    };


    private class NetworkHandlerThread extends HandlerThread {
        private static final int STATE_A = 1;
        private static final int STATE_B = 2;
        private Handler mHandler;
        public NetworkHandlerThread() {
            super(NetworkHandlerThread.class.getSimpleName(), Process.THREAD_PRIORITY_BACKGROUND );
        }

        public NetworkHandlerThread(String name) {
            super(name,Process.THREAD_PRIORITY_BACKGROUND );
        }

        @Override
        protected void onLooperPrepared() {
            super.onLooperPrepared();

            mHandler = new Handler(getLooper()){
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    switch ( msg.what ){
                        case STATE_A:
                            dialogHandler.sendEmptyMessage(SHOW_LOADING);
                            String result = networkOpertaion1();
                            if( result != null){
                                sendMessage( obtainMessage( STATE_B, result));
                            }else {
                                dialogHandler.sendEmptyMessage( DISMISS_LOADING );
                            }
                            break;
                        case STATE_B:
                            networkOpertaion2( (String) msg.obj );
                            dialogHandler.sendEmptyMessage( DISMISS_LOADING );
                            break;

                    }
                }
            };

            //
            fetchDataFromNetWork();

        }

        private String networkOpertaion1(){
            SystemClock.sleep(2000); // 더미
            return "A String";
        }

        private void networkOpertaion2( String data ){
            SystemClock.sleep( 2000 );
        }

        /**
         * 공개적으로 노출된 네트워크 작업
         */

        public void fetchDataFromNetWork(){
            mHandler.sendEmptyMessage( STATE_A );
        }
   }

   private NetworkHandlerThread mThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chained_network);

            mThread = new NetworkHandlerThread();
            mThread.start();

    }

    @Override
    protected Dialog onCreateDialog(int id) {

        Dialog dialog = null;
        switch (id){
            case DIALOG_LOADING:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Loading .... ");
                dialog = builder.create();
                break;
        }
        return dialog;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mThread.quit();
    }
}
