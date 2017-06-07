package com.example.sslab.samplegroupapplication.samples.EfficientAndroidTreadEx;

import android.support.annotation.NonNull;
import android.support.test.espresso.base.ThreadPoolExecutorExtractor_Factory;

import java.util.ArrayDeque;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by SSLAB on 2017-06-05.
 */

public class SerialExecutor implements Executor {
    final ArrayDeque<Runnable> mTask = new ArrayDeque<Runnable>();
    Runnable mActive;


    @Override
    public synchronized void execute(@NonNull final Runnable command) {

        //
//            @Override
//            public void run() {
//                try{
//                    command.run();
//                }finally {
//                    scheduleNext();
//                }
//            }
//        });

//        if(mActive == null){
//            scheduleNext();
//        }
    }

//    protected synchronized void scheduleNext(){
//        if( (mActive = mTask.poll()) != null ){
//            ThreadPoolExecutor.excute(mActive);
//        }
//    }
}
