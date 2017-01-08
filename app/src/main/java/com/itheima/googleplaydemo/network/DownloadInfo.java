package com.itheima.googleplaydemo.network;

import android.os.Environment;

/**
 * Created by Leon on 2017/1/5.
 */

public class DownloadInfo {

    public static String DOWNLOAD_DIRECTORY = Environment.getExternalStorageDirectory() + "/Android/data/com.itheima.googleplaydemo/apk/";

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    private String packageName;
    private String downloadUrl;
    private String appName;
    private int downloadStatus = DownloadManager.STATE_UN_DOWNLOAD;

    private int max;

    public Runnable getDownloadTask() {
        return downloadTask;
    }

    public void setDownloadTask(Runnable downloadTask) {
        this.downloadTask = downloadTask;
    }

    private Runnable downloadTask;

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    private int progress;


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
