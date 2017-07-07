package com.example.sslab.samplegroupapplication.samples.EfficientAndroidTreadEx;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.sslab.samplegroupapplication.R;

/**
 * 클라이언트 측에서 액티비티는 서버 프로세스안의 서비스에바인딩되고 메시지를 보낸다.
 */
public class MessengerOnewayActivity extends AppCompatActivity implements View.OnClickListener {

    private boolean mBound = false;
    private Messenger mRemoteService;


    Button onBindButton;
    Button onUnbindButton;
    Button onSendButton;
    private ServiceConnection mRemoteCopnnection = new ServiceConnection() {
        final String TAG = "ServiceConnection";
        // 서버로 부터 전달된 바인더로 부터 Messenger 인스턴스를 생성
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG,"service Connected ");
            mRemoteService = new Messenger(service);
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(TAG,"service DisConnected ");
            mRemoteService = null;
            mBound = false;
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messenger_oneway);
        onBindButton = (Button)findViewById( R.id.onBind);
        onUnbindButton = (Button)findViewById( R.id.onUnbind);
        onSendButton = (Button)findViewById( R.id.onSend);
        onBindButton.setOnClickListener( this );
        onUnbindButton.setOnClickListener( this );
        onSendButton.setOnClickListener( this );
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.onBind:
                Intent intent = new Intent("com.example.sslab.samplegroupapplication.samples.servicePackage.WorkerThreadService.ACTION_BIND");
                bindService(intent, mRemoteCopnnection , Context.BIND_AUTO_CREATE );
                Toast.makeText(this,"onBindService",Toast.LENGTH_SHORT).show();


                break;
            case R.id.onUnbind:
                if(mBound){
                    unbindService(mRemoteCopnnection);
                    mBound = false;
                }
                break;
            case R.id.onSend:
                if(mBound){
                    try{

                        // 원격 서비스로 전달되는 메신저를 생성한다. 메신저는
                        // 다른 프로세스로 부터 메세ㅣ지를 실행하는 현제 스레드에 대한 핸들러 참조를 가진다.
                        Message msg = Message.obtain(null,1,0,0);
                        msg.replyTo = new Messenger( new Handler(){
                            @Override
                            public void handleMessage(Message msg) {
                                Toast.makeText(MessengerOnewayActivity.this,"msg what : " + msg.what ,Toast.LENGTH_SHORT).show();
                            }
                        });
                        mRemoteService.send(msg);
                    }catch ( RemoteException e ){
                        e.printStackTrace();
                    }
                }
                break;
        }
    }
}
