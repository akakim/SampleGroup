package com.example.sslab.samplegroupapplication.bitmap;

import android.graphics.Bitmap;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.sslab.samplegroupapplication.R;

import java.util.Random;

public class useableProgressActivity extends AppCompatActivity implements WorkerThread.Callback {
    private static boolean isVisible;
    public static final int LEFT_SIDE = 0;
    public static final int RIGHT_SIDE = 1;

    private LinearLayout mLeftSideLayout;
    private LinearLayout mRightSideLayout;
    private WorkerThread mWorkerThread;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_useable_progress);

        isVisible = true;
        mLeftSideLayout = ( LinearLayout )findViewById( R.id.leftSide );
        mRightSideLayout = ( LinearLayout )findViewById( R.id.rightSide );

        String[] urls = new String[]{"http://developer.android.com/design/media/principles_delight.png",
                "http://developer.android.com/design/media/principles_real_objects.png",
                "http://developer.android.com/design/media/principles_make_it_mine.png",
                "http://developer.android.com/design/media/principles_get_to_know_me.png"};

        mWorkerThread = new WorkerThread(new Handler(), this);
        mWorkerThread.start();
        mWorkerThread.prepareHandler();
        Random random = new Random();
        for (String url : urls){
            mWorkerThread.queueTask(url, random.nextInt(2), new ImageView(this));
        }
    }

    @Override
    protected void onPause() {
        isVisible = false;
        super.onPause();

    }

    @Override
    protected void onDestroy() {
        mWorkerThread.quit();
        super.onDestroy();
    }

    @Override
    public void onImageDownloaded(ImageView imageView, Bitmap bitmap, int side) {
        imageView.setImageBitmap(bitmap);
        if (isVisible && side == LEFT_SIDE){
            mLeftSideLayout.addView(imageView);
            mLeftSideLayout.requestLayout();
        } else if (isVisible && side == RIGHT_SIDE){
            mRightSideLayout.addView(imageView);
            mRightSideLayout.requestLayout();
        }
    }
}
