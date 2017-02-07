package com.example.sslab.samplegroupapplication.common;

import javax.inject.Singleton;

import dagger.Provides;

/**
 * Created by SSLAB on 2017-02-06.
 */

public class NetworkApiModule {
    @Provides
    @Singleton
    public NetworkApi getNetWork(){
        return new NetworkApi();
    }
}
