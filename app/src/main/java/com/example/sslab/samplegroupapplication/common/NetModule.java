package com.example.sslab.samplegroupapplication.common;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import javax.inject.Singleton;

import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.Converter.Factory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by SSLAB on 2017-02-06.
 */

public class NetModule {
    String baseUrl;

    public NetModule( String baseUrl ){
        this.baseUrl = baseUrl;
    }

    // Dagger는 @Privides라는 어노테이션 이 선언된 메소드만을 본다.
    @Provides
    @Singleton
    // Applicatoin reference must come from AppModule
    SharedPreferences providesSharedPreference( Application application ){
        return PreferenceManager.getDefaultSharedPreferences( application );
    }

    @Provides
    @Singleton
    Cache provideOkHttpCache(Application application ){
        int cacheSize = 10 * 1024 * 1024;
        Cache cache = new Cache ( application.getCacheDir(), cacheSize );
        return cache ;
    }
    @Provides
    @Singleton
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_DASHES);
        return gsonBuilder.create();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(Cache cache) {
        OkHttpClient client = new OkHttpClient();
        client.newBuilder().cache(cache);
        return client;
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .build();
        return retrofit;
    }

}
