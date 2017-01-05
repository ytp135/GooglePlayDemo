package com.itheima.googleplaydemo.network;

import android.os.Environment;

/**
 * Created by Leon on 2017/1/5.
 */

public class DownloadInfo {

    public static String DOWNLOAD_DIRECTORY = Environment.getExternalStorageDirectory() + "/Android/data/com.itheima.googleplaydemo/apk/";

    private String packageName;
    private String downloadUrl;
    private String appName;
    private int downloadStatus = DownloadManager.STATE_UN_DOWNLOAD;


    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }


    public String getApkName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public int getDownloadStatus() {
        return downloadStatus;
    }

    public void setDownloadStatus(int downloadStatus) {
        this.downloadStatus = downloadStatus;
    }
}
