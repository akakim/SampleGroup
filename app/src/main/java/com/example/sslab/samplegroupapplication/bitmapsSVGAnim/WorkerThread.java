package com.example.sslab.samplegroupapplication.bitmapsSVGAnim;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by SSLAB on 2017-03-29.
 */

public class WorkerThread extends HandlerThread {

    private Handler mWorkerHandler;
    private Handler mResponseHandler;
    private static final String TAG = WorkerThread.class.getSimpleName();
    private Map<ImageView, String> mRequestMap = new HashMap<ImageView, String>();
    private Callback mCallback;




    public interface Callback {
        public void onImageDownloaded(ImageView imageView, Bitmap bitmap, int side);
    }

    public WorkerThread(Handler responseHandler, Callback callback) {
        super(TAG);
        mResponseHandler = responseHandler;
        mCallback = callback;
    }

    public void queueTask(String url,int side, ImageView imageView){
        mRequestMap.put(imageView,url);
        Log.d(TAG, url + " added to the queue");
        mWorkerHandler.obtainMessage(side,imageView).sendToTarget();
    }

    public void prepareHandler(){
        mWorkerHandler = new Handler(getLooper(), new Handler.Callback(){

            @Override
            public boolean handleMessage(Message msg) {

                try{
                    TimeUnit.SECONDS.sleep(2);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }

                ImageView imageView = (ImageView)msg.obj;
                String side = msg.what == useableProgressActivity.LEFT_SIDE ? "left side" : "right side";
                Log.i(TAG, String.format("Processing %s, %s", mRequestMap.get(imageView), side));
                handlerRequest(imageView,msg.what);
//                msg.recycle();

                return true;
            }
        });
    }

    private void handlerRequest(final ImageView imageView,final int side){
        String url = mRequestMap.get(imageView);
        try{
            HttpURLConnection urlConnection = ( HttpURLConnection )new URL(url).openConnection();
            final Bitmap bitmap = BitmapFactory.decodeStream( (InputStream) urlConnection.getContent());
            mRequestMap.remove(imageView);
            mResponseHandler.post(new Runnable() {
                @Override
                public void run() {
                    mCallback.onImageDownloaded(imageView,bitmap,side);
                }
            });
        }catch ( IOException e){
            e.printStackTrace();
        }
    }
}
