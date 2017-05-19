package com.example.sslab.samplegroupapplication.samples.EfficientAndroidTreadEx;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.sslab.samplegroupapplication.R;

public class MessengerOnewayActivity extends AppCompatActivity implements View.OnClickListener {

    private boolean mBound = false;
    private Messenger mRemoteService;


    Button onBindButton;
    Button onUnbindButton;
    Button onSendButton;
    private ServiceConnection mRemoteCopnnection = new ServiceConnection() {
        // 서버로 부터 전달된 바인더로 부터 Messenger 인스턴스를 생성
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mRemoteService = new Messenger(service);
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
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
                Intent intent = new Intent(this,WorkerThreadService.class);
                bindService(intent, mRemoteCopnnection , Context.BIND_AUTO_CREATE );
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
                        mRemoteService.send(Message.obtain( null,2,0,0));
                    }catch ( RemoteException e ){

                    }
                }
                break;
        }
    }
}
