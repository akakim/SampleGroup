package com.example.sslab.samplegroupapplication.samples.InspectorSamples;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v4.os.BuildCompat;
import android.util.Log;

import com.example.sslab.samplegroupapplication.BuildConfig;
import com.example.sslab.samplegroupapplication.widget.DialogBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by SSLAB on 2017-02-28.
 */

public class FileCopyTask extends AsyncTask<String,String,String> {

    final String TAG = FileCopyTask.class.getSimpleName();
    Uri uri;
    Handler handler;
    Context context;
    Message msg;
    public FileCopyTask(Context context,Uri uri,Handler handler){
        this.context = context;
        this.uri = uri;
        this.msg= new Message();
        this.handler = handler;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        // 버전 체크

        // 권한 체크

        // 다이얼로그 생성
    }

    @Override
    protected String doInBackground(String... params) {

        InputStream in = null;
        OutputStream out = null;

        if (params.length == 0){
            return "0001";
        }
        try {

            in = context.getContentResolver().openInputStream(uri);
            if(in == null){
                return "0002";
            }
            for(int i = 0;i<params.length;i++){
                File targetFile = new File( params[0] );
                if ( !targetFile.exists() )
                    targetFile.createNewFile();

                out = new FileOutputStream( targetFile );
                byte [] buffer = new byte[1024];
                int length= 0;
                while ( (length = in.read(buffer) ) > 0){
                    out.write( buffer,0, length );
                }

                if(handler != null && i == 0){
                    msg.what = 0;   // 아마도 전역 what을 쓰는 방향이 적절할거같다.
                    msg.obj = targetFile;
                    handler.sendMessage(msg);
                }
            }


            in.close();
            out.close();
        }catch (FileNotFoundException fileException){
            Log.e(TAG,context.getApplicationInfo().className+" can not file open");
            fileException.printStackTrace();
        }catch (Exception e ){

            e.printStackTrace();
        }
        return "0000";
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        // progressbar 삭제
        switch (s){
            case "0000":
                break;
            case "0001":
                Log.e(TAG,"target path does not exist ");
                break;
            case "0002":
                Log.e(TAG,"input Stream is null");
                break;
            default:
                Log.e(TAG," unknown error occurred");
                break;
        }

    }
}
