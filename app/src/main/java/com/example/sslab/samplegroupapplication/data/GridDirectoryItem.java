package com.example.sslab.samplegroupapplication.data;

import java.io.File;

/**
 * Created by SSLAB on 2017-02-20.
 */

public class GridDirectoryItem {
    private String name;
    private String path;
    private File thumbnail;
    public GridDirectoryItem(String name, String path, File thumbnail) {
        this.name = name;
        this.path = path;
        this.thumbnail = thumbnail;
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

    public File getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(File thumbnail) {
        this.thumbnail = thumbnail;
    }
}
