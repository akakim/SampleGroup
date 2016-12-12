package com.example.sslab.samplegroupapplication.data;

/**
 * Created by SSLAB on 2016-12-12.
 */

public class ImgExplorerItem {
    public boolean toUp = false;        // 현재 위치가 root("/system")이라면 up 을 표시하지 않도록
    public boolean isFolder;            // 폴더? 아니면 그림 파일?
    public String filePath, fileName;

    public ImgExplorerItem( boolean isFolder, String filePath, String fileName ) {
        this.isFolder = isFolder;
        this.filePath = filePath;
        this.fileName = fileName;
    }

    public ImgExplorerItem( boolean toUp, boolean isFolder, String filePath, String fileName ) {
        this.toUp = toUp;
        this.isFolder = isFolder;
        this.filePath = filePath;
        this.fileName = fileName;
    }
}
