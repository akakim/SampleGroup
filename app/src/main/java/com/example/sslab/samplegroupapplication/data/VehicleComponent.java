package com.example.sslab.samplegroupapplication.data;

import android.support.test.espresso.core.deps.dagger.Component;

import javax.inject.Singleton;

/**
 * Created by SSLAB on 2017-02-06.
 */
@Singleton
@Component(modules = {VehicleModule.class})

public interface VehicleComponent {
    Vehicle provideVehicle();
}
