package com.example.sslab.samplegroupapplication.util;

import android.app.ActivityManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.sslab.samplegroupapplication.MainActivity;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

/**
 * Created by SSLAB on 2016-11-24.
 */

public class UncaughtExceptionHandlerApplication implements Thread.UncaughtExceptionHandler {
    private final String TAG = UncaughtExceptionHandlerApplication.class.getSimpleName();
    Thread.UncaughtExceptionHandler mUncaughtExceptionHandler;
    Context context;

    public UncaughtExceptionHandlerApplication(Thread.UncaughtExceptionHandler mUncaughtExceptionHandler,Context context) {
        this.mUncaughtExceptionHandler = mUncaughtExceptionHandler;
        this.context = context;
    }

    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {
        // 예외상황이 발행될경우 작업
        Log.e(TAG,getStackTrace(throwable));
        Intent intent = new Intent(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

//        PendingIntent pendingIntent = PendingIntent.getActivities(context,0,intent,PendingIntent.FLAG_ONE_SHOT);
        Bundle setStatus = new Bundle();
//        setStatus.putString("option");
        MainActivity mainActivity = new MainActivity();
        mainActivity.startActivity(intent);
        // 예외처리를 하지 않고 defaultUncaugthException으로 넘긴다.
        mUncaughtExceptionHandler.uncaughtException(thread,throwable);

    }

    private String getStackTrace(Throwable th){

        final Writer result = new StringWriter();
        final PrintWriter printWriter = new PrintWriter(result);

        Throwable cause = th;
        while (cause != null){
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }

        final String stacktraceAsString = result.toString();
        printWriter.close();

        return stacktraceAsString;
    }
}
