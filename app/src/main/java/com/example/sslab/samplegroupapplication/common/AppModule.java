package com.example.sslab.samplegroupapplication.common;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * 트위터에선 네트워크 모듈 + 캐싱을 하길 원한다.
 * 캐싱을 하기 위해선 context가 필요한데, 이를 위한 application 객체를 선언했다.
 *
 */

@Module
public class AppModule {
    Application application;

    @Provides
    @Singleton
    Application providesApplication(){
        return application;
    }
}
