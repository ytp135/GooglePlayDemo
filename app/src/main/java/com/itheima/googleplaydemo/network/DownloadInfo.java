package com.itheima.googleplaydemo.network;

/**
 * Created by Leon on 2017/1/5.
 */

public class DownloadInfo {


    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    private String packageName;
    private String downloadUrl;
    private String appName;


    public String getApkName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

}
