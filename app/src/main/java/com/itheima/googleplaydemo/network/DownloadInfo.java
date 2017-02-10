package com.itheima.googleplaydemo.network;

/**
 * Created by Leon on 2017/1/5.
 */

public class DownloadInfo {

    private String packageName;
    private String downloadUrl;
    private String filePath;
    private int downloadStatus = DownloadManager.STATE_UN_DOWNLOAD;
    private Runnable downloadTask;
    private int size;
    private long progress;

    public Runnable getDownloadTask() {
        return downloadTask;
    }

    public void setDownloadTask(Runnable downloadTask) {
        this.downloadTask = downloadTask;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public long getProgress() {
        return progress;
    }

    public void setProgress(long progress) {
        this.progress = progress;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public int getDownloadStatus() {
        return downloadStatus;
    }

    public void setDownloadStatus(int downloadStatus) {
        this.downloadStatus = downloadStatus;
    }
}
