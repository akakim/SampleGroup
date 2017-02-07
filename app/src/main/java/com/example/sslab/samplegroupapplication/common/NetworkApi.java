package com.example.sslab.samplegroupapplication.common;

/**
 * Created by SSLAB on 2017-02-06.
 */

public class NetworkApi {

    public boolean validateUser(String username,String password){

        // imagine an actual network call here
        // for demo purpose return false in "real" life
        if (username == null || username.length() == 0) {
            return false;
        } else {
            return true;
        }
    }

}
