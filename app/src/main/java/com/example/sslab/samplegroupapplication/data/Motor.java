package com.example.sslab.samplegroupapplication.data;

/**
 * Created by SSLAB on 2017-02-06.
 */

public class Motor {
    private int rpm;

    public Motor(){
        rpm = 0;
    }

    public int getRpm(){
        return  rpm;
    }

    public void setRpm(int rpm ){
        this.rpm = rpm;
    }

    public void accelerate(int value){
        rpm += value;
    }

    public void brake(){
        rpm = 0;
    }

}
