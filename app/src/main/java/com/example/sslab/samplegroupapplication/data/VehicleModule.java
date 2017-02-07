package com.example.sslab.samplegroupapplication.data;

import android.support.test.espresso.core.deps.dagger.Module;

import javax.inject.Singleton;

import dagger.Provides;

/**
 * Created by SSLAB on 2017-02-06.
 */

@Module
public class VehicleModule  {

    @Provides
    @Singleton
    Motor privideModule(){
        return new Motor();
    }

    @Provides
    @Singleton
    Vehicle provideVehicle(){
        return new Vehicle(new Motor());
    }
}
