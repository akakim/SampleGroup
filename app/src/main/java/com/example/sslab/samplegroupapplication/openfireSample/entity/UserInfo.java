package com.example.sslab.samplegroupapplication.openfireSample.entity;

import android.support.test.internal.runner.ClassPathScanner;
import android.util.Log;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ser.Serializers;

import org.json.JSONObject;
import org.junit.Test;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by SSLAB on 2017-05-29.
 */
public class UserInfo implements Serializable{

    @JsonProperty("userName")
    private String userName;
    @JsonProperty("userSex")
    private String userSex;
    @JsonProperty("systemProperty")
    private Map<String,String> systemProperty;
    @JsonProperty("SessionName")
    private String SessionName;
    @JsonProperty("root")
    JSONObject rootObject;

    public UserInfo (){

    }
    public UserInfo(String userName, String userSex, Map<String, String> systemProperty, String sessionName) {
        Log.d(UserInfo.class.getSimpleName(),"not root ");
        this.userName = userName;
        this.userSex = userSex;
        this.systemProperty = systemProperty;
        SessionName = sessionName;
    }

    public UserInfo (UserInfo userInfo ){
//        Log.d(getClass().getSimpleName(),userInfo.getUserName());
//        Log.d(getClass().getSimpleName(),userInfo.getUserSex());
//        Log.d(getClass().getSimpleName(),userInfo.getSystemProperty()+"");
//        Log.d(getClass().getSimpleName(),userInfo.getUserSex());
    }
    public UserInfo(JSONObject root){
        this.rootObject = root;

        Log.d(UserInfo.class.getSimpleName(),"root");
        if(rootObject != null) {

            Log.d(UserInfo.class.getSimpleName(), rootObject.toString());
        }else{
            Log.e(UserInfo.class.getSimpleName(),"fooooo.. rootObject is null");
        }
//        try{
//            if(jsonObject.get("userName") != null){
//                this.userName = jsonObject.getString("userName");
//            }
//
//            if(jsonObject.get("userSex") != null){
//                this.userSex = jsonObject.getString("userSex");
//            }
//            if(jsonObject.get("systemProperty") != null){
//               JSONObject systemPropertyObject = jsonObject.getJSONObject("systemProperty");
//                this.systemProperty = null;
//            }
//            if(jsonObject.get("SessionName") != null){
//                this.SessionName = jsonObject.getString("SessionName");
//            }
//
//        }catch (Exception e ){
//            e.printStackTrace();
//        }
    }



    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public Map<String, String> getSystemProperty() {
        return systemProperty;
    }

    public void setSystemProperty(Map<String, String> systemProperty) {
        this.systemProperty = systemProperty;
    }
    public String getSessionName() {
        return SessionName;
    }

    public void setSessionName(String sessionName) {
        SessionName = sessionName;
    }
}
