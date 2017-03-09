package com.example.sslab.samplegroupapplication.common;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.answers.Answers;
import com.example.sslab.samplegroupapplication.BaseActivity;
import com.kakao.auth.IApplicationConfig;
import com.kakao.auth.KakaoAdapter;
import com.kakao.auth.KakaoSDK;
import com.kakao.util.helper.log.Logger;
import io.fabric.sdk.android.Fabric;


/**
 * Created by SSLAB on 2017-01-16.
 */

public class GlobalApplication extends Application  {
    private final String TAG = this.getClass().getSimpleName();
    private static volatile GlobalApplication instance = null;
    private static volatile BaseActivity currentActivity = null;

    public Handler handler;
    NetComponent netComponent;

    public static BaseActivity getCurrentActivity() {

//        ActivityManager activityManager = ( ActivityManager )getSystemService(S)
        Logger.d("++ currentActivity : " + (currentActivity != null ? currentActivity.getClass().getSimpleName() : ""));
        return currentActivity;
    }

    public static void setCurrentActivity(BaseActivity currentActivity){
        GlobalApplication.currentActivity = currentActivity;
    }

    /**
     * singleton 애플리케이션 객체를 얻는다.
     * @return singleton 애플리케이션 객체
     */
    public static GlobalApplication getGlobalApplicationContext() {
        if(instance == null)
            throw new IllegalStateException("this application does not inherit com.kakao.GlobalApplication");
        return instance;
    }


    @Override
    public void registerActivityLifecycleCallbacks(ActivityLifecycleCallbacks callback) {
        super.registerActivityLifecycleCallbacks(callback);
    }

    private static class KakaoSDKAdapter extends KakaoAdapter {
        @Override
        public IApplicationConfig getApplicationConfig() {
            return new IApplicationConfig() {
                @Override
                public Context getApplicationContext() {
                    return GlobalApplication.getGlobalApplicationContext();
                }
            };
        }
/**
 *
 */
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this,new Crashlytics());
        Fabric.with(this, new Answers());

        Log.d(TAG,"onCreate() ");
        // 세션을 초기화한다.
        instance = this;

        KakaoSDK.init(new KakaoSDKAdapter());
//        netComponent = DaggerNetComponent.builder()
//                .appModule( new AppModule() )
//                .netModule( new NetModule("https://api.github.com"));

    }

    @Override
    public Looper getMainLooper() {

        return super.getMainLooper();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        instance = null;
    }
}
