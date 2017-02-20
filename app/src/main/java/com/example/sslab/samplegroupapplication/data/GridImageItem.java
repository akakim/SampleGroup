package com.example.sslab.samplegroupapplication.data;

/**
 * Created by SSLAB on 2017-02-20.
 */

public class GridImageItem {

    String name;
    String path;

    public GridImageItem(String name, String path) {
        this.name = name;
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
