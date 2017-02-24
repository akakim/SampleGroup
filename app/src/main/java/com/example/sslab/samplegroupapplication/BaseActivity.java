package com.example.sslab.samplegroupapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import com.example.sslab.samplegroupapplication.common.GlobalApplication;
import com.example.sslab.samplegroupapplication.data.ICFO;
import com.example.sslab.samplegroupapplication.firebaseSamples.FirebaseMessagingServiceSample;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by SSLAB on 2016-12-26.
 */

public abstract class BaseActivity extends AppCompatActivity{
    protected ProgressDialog progressDialog;
    public static final int ACTIVITY_REQUEST = 1;
    public static final int FRAGMENT_REQUEST = 2;
    final String LOADING_SHOW = "LOADING_SHOW";
    final String REMOVE_SHOW = "REMOVE_SHOW";


    Toast mToast;
    public Handler basehandler = new Handler(){
        public void handleMessage(Message msg){
//            if(msg.obj instanceof String){
//                String strObj = (String)msg.obj;
//
//                switch (strObj){
//                    case LOADING_SHOW:
//                        break;
//                    case REMOVE_SHOW:
//                        break;
//                }
//
//            }else if(msg.obj instanceof Integer){
//                Integer intObj = (Integer)msg.obj;
//
//
//
//            }else if(msg.obj instanceof Double){
//                Double doubleObj = (Double)msg.obj;
//
//
//            }
            Log.d("message", msg.toString());
            switch ( msg.what ){
                case 0:
                    showToast("Hell");
                    break;
            }
        }


    };


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GlobalApplication.setCurrentActivity( this );
        FirebaseMessagingServiceSample.h = basehandler;
    }

    @Override
    protected void onDestroy() {
        basehandler = null; // 핸들러의 경우 쓰고나면 반드시 null처리를 해주자.
        super.onDestroy();
    }

    protected void showLoading(String target){

        Message targetMessage = Message.obtain();
        targetMessage.obj = LOADING_SHOW;

        basehandler.removeMessages(1,targetMessage);

        if(progressDialog == null){
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage(target);
            progressDialog.setCancelable(true);
            //TODO: 네트워크 작업도 같이 취소한다.
            progressDialog.show();
        }else {
            progressDialog.setMessage(target);
        }

//        targetMessage.set

//        basehandler.removeMessages();
//

    }

    protected boolean isLoading(){
        if( progressDialog == null){
            return false;
        }else {
            return progressDialog.isShowing();
        }
    }

    /**
     * Toast 메세지를 보여준다.
     * @param msg
     */
    public void showToast(String msg) {
        if(mToast == null){
            mToast = Toast.makeText(BaseActivity.this, msg, Toast.LENGTH_SHORT);
            mToast.setGravity(Gravity.CENTER, 0, 0);
        }else{
            mToast.setText(msg);
        }
        mToast.show();
    }



    public void requestData(String urlstr,final ICFO icfo){
        HTTPURLConnectionTask task ;
        URL url;


        try{
            url= new URL(urlstr);
        }
        catch (MalformedURLException e){
            showToast("올바르지않는 URL입니디ㅏ. ");
            return;
        }
        catch (Exception e){
            e.printStackTrace();
            return;
        }

        HTTPURLConnectionTask URLConnectionTask = new HTTPURLConnectionTask(url);
        URLConnectionTask.execute(icfo);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Intent getData = data;
        showToast(data.toString());

        switch (requestCode){
            case ACTIVITY_REQUEST:
                break;
            case FRAGMENT_REQUEST:
                break;
        }
    }


    public void showGalleryForActivity() {
        if ( Build.VERSION.SDK_INT < 19 ) {
            Intent intent = new Intent( Intent.ACTION_GET_CONTENT );
            intent.addCategory( Intent.CATEGORY_OPENABLE );
            intent.setType( "image/*" );
            startActivityForResult( intent, ACTIVITY_REQUEST );
        } else {
            Intent intent = new Intent( Intent.ACTION_OPEN_DOCUMENT );
            intent.addCategory( Intent.CATEGORY_OPENABLE );
            intent.setType( "image/*" );
            startActivityForResult( intent, ACTIVITY_REQUEST );
        }
    }


    /**
     * 버전별로 갤러리를 띄우는 양식
     * <p/>
     * 킷캣 이후로 파일 첨부 방식이 변경되어 버전별로 쪼개줘야 한다(현재처럼 단일 기기만 쓸 땐 큰 의미 없음)
     * <p/>
     * 현재 사용되지 않음(파일 탐색기 형식을 사용)
     */
    public void showGalleryForFragment() {
        if ( Build.VERSION.SDK_INT < 19 ) {
            Intent intent = new Intent( Intent.ACTION_GET_CONTENT );
            intent.addCategory( Intent.CATEGORY_OPENABLE );
            intent.setType( "image/*" );
            startActivityForResult( intent, FRAGMENT_REQUEST );
        } else {
            Intent intent = new Intent( Intent.ACTION_OPEN_DOCUMENT );
            intent.addCategory( Intent.CATEGORY_OPENABLE );
            intent.setType( "image/*" );
            startActivityForResult( intent, FRAGMENT_REQUEST );
        }
    }

    public abstract void responseData(JSONObject jsonObject);

    private class HTTPURLConnectionTask extends AsyncTask<ICFO,Integer,Integer>{
        URL[] url;
        JSONObject jsonObject = new JSONObject();
        final int timeoutCount = 5000;
        public HTTPURLConnectionTask(URL... url){
            this.url = url;
        }
        @Override
        protected Integer doInBackground(ICFO... icfos) {
            if(icfos == null){
                return 0;
            }
            if(url.length == 0 || icfos.length == 0){
                return 1;
            }
            if(url.length != icfos.length){
                return 1;
            }
            HttpURLConnection connection [] = new HttpURLConnection[icfos.length];
            InputStream inputStream [] = new InputStream[icfos.length];

            try {
                for (int i = 0; i < icfos.length; i++) {
                    connection[ i ] = (HttpURLConnection) url[ i ].openConnection();
                    connection[ i ].setReadTimeout(timeoutCount);
                    connection[ i ].setConnectTimeout(timeoutCount);

                    // 서버의 responseData 타입설정.
                    connection[ i ].setRequestProperty("Accept", "application/json");
                    ////타입설정을 application/json방식으로 전송한다.
                    connection[ i ].setRequestProperty("Content-Type", "application/json");
                    connection[ i ].setRequestProperty("Cache-Control","no-cache");
                    connection[ i ].setDoOutput(true);
                    connection[ i ].setDoInput(true);

                    Log.d(getApplicationContext().getClass().getSimpleName(),"connection outputSetting start");

                    // 이제 서버로 보낸다.
                    OutputStream outputstream = connection[ i ].getOutputStream();
                    outputstream.write(icfos[ i ].getJsonObject().toString().getBytes());
                    outputstream.flush();
                    Log.d(getApplicationContext().getClass().getSimpleName(),"outputStreamFlushed");
                    String response="";
                    int responseCode = connection[i].getResponseCode();

                    if(responseCode == HttpURLConnection.HTTP_OK){
                        JSONObject tmpObj = new JSONObject(response);
                        jsonObject.put(icfos[i].getId(),tmpObj);
                    }

                }
            }catch (IOException e){
              e.printStackTrace();
                return 2;
            }catch (JSONException e){
                e.printStackTrace();
                return 3;
            }catch (Exception e){
                return 4;
            }


            return 5;
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);

            switch (integer){
                case 0:
                    showToast(integer + " : 알수 없는 에러발생");
                    break;
                case 1:
                    showToast(integer + " : requst 생성 실패.");
                    break;
                case 2:
                    showToast(integer + " : request 생성 실패/");
                    break;
                case 3:
                    showToast(integer + " : JSONException 발생.");
                    break;
                case 4:
                    showToast(integer + " : 알수 없는 에러발생");
                    break;
                case 5:
                    showToast(integer + " : 응답성공");
                    responseData(jsonObject);
                    break;
            }
        }
    }

}
