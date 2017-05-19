package com.example.sslab.samplegroupapplication.samples.EfficientAndroidTreadEx;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.sslab.samplegroupapplication.R;

// 메세지를 가로채기 위해 Callback인터페이스를 구현한다.
public class HandlerCallbackActivity extends AppCompatActivity implements Handler.Callback,View.OnClickListener{

    Handler activityHandler  = null;

    Button sendMessageButton;
    Button sendMessageButton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_callback);
        activityHandler = new Handler( this );

        sendMessageButton = ( Button ) findViewById( R.id.sendMessageButton);
        sendMessageButton2 = ( Button ) findViewById( R.id.sendMessageButton2);

        sendMessageButton.setOnClickListener( this );
        sendMessageButton2.setOnClickListener( this );
    }

    @Override
    public boolean handleMessage(Message msg) {

        switch (msg.what){
            case 1 :
                msg.what = 11;
                Toast.makeText(getApplicationContext(),"in handler Callback msg : " + msg.what, Toast.LENGTH_SHORT ).show();
                return true;        // 메세지가 처리가 된다.
            default:
                msg.what = 22;
                return false;   // 메세지가 처리되지않는다. 처리되지않는 메세지는 handle Message로 구현된다.
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        activityHandler = null;

    }

    public void onHandlerCallback(View v ){
        Handler handler = new Handler ( this ){
            @Override
            public void handleMessage(Message msg) {
                // 콜백에서 무시한 메세지를 처리하게 된다.
                Toast.makeText(getApplicationContext(),"msg : " + msg.what, Toast.LENGTH_SHORT ).show();
            }
        };
        handler.sendEmptyMessage( 1 );
        handler.sendEmptyMessage( 2 );
//        switch ( v.getId() ){
//            case R.id.sendMessageButton:
//                handler.sendEmptyMessage( 1 );
//                break;
//            case R.id.sendMessageButton2:
//                handler.sendEmptyMessage( 2 );
//                break;
//
//        }



    }

    @Override
    public void onClick(View v) {
        onHandlerCallback(v);
    }
}
