package com.example.sslab.samplegroupapplication.data;

import java.util.ArrayList;

/**
 * Created by SSLAB on 2016-11-24.
 */

public class phoneData {
    private int id;
    private String OS;
    private ArrayList<String> PhoneType;

    public phoneData(int id, String OS, ArrayList<String> phoneType) {
        this.id = id;
        this.OS = OS;
        PhoneType = phoneType;
    }

    public phoneData(String os, ArrayList<String> phoneType) {
        this.id = -1;
        this.OS = OS;
        PhoneType = phoneType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOS() {
        return OS;
    }

    public void setOS(String OS) {
        this.OS = OS;
    }

    public ArrayList<String> getPhoneType() {
        return PhoneType;
    }

    public void setPhoneType(ArrayList<String> phoneType) {
        PhoneType = phoneType;
    }
}
