package com.example.sslab.samplegroupapplication.samples;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.sslab.samplegroupapplication.R;
import com.example.sslab.samplegroupapplication.common.NetworkApiModule;
import com.example.sslab.samplegroupapplication.data.Vehicle;
import com.example.sslab.samplegroupapplication.data.VehicleComponent;

import javax.inject.Inject;

public class DaggerSample extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger_sample);
      //   InjectorClass.inject( this ) ;

//        VehicleComponent component = DaggerVehicleCompnent.build().
    }
}
