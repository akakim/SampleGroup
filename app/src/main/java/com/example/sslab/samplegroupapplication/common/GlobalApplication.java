package com.example.sslab.samplegroupapplication.common;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.kakao.auth.IApplicationConfig;
import com.kakao.auth.KakaoAdapter;
import com.kakao.auth.KakaoSDK;
import com.kakao.util.helper.log.Logger;


/**
 * Created by SSLAB on 2017-01-16.
 */

public class GlobalApplication extends Application  {
    private final String TAG = this.getClass().getSimpleName();
    private static volatile GlobalApplication instance = null;
    private static volatile Activity currentActivity = null;

    NetComponent netComponent;

    public static Activity getCurrentActivity() {
        Logger.d("++ currentActivity : " + (currentActivity != null ? currentActivity.getClass().getSimpleName() : ""));
        return currentActivity;
    }

    public static void setCurrentActivity(Activity currentActivity){
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
        Log.d(TAG,"onCreate() ");
        // 세션을 초기화한다.
        instance = this;
        KakaoSDK.init(new KakaoSDKAdapter());
//        netComponent = DaggerNetComponent.builder()
//                .appModule( new AppModule() )
//                .netModule( new NetModule("https://api.github.com"));

    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        instance = null;
    }
}
