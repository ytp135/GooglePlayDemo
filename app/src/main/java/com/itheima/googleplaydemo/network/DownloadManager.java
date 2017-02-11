package com.itheima.googleplaydemo.network;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;

import com.itheima.googleplaydemo.utils.ThreadPoolProxy;
import com.itheima.googleplaydemo.utils.URLUtils;

import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Observer;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Leon on 2017/1/5.
 */

public class DownloadManager {
    //下载apk的存放路径，当应用被卸载时，该路径下的文件也会被删除
    private static final String DOWNLOAD_DIRECTORY = Environment.getExternalStorageDirectory() + "/Android/data/com.itheima.googleplaydemo/apk/";
    private static DownloadManager sDownloadManager;
    private OkHttpClient mOkHttpClient;
    public static final int STATE_UN_DOWNLOAD = 0;//未下载
    public static final int STATE_DOWNLOADING = 1;//下载中
    public static final int STATE_PAUSE = 2;//暂停下载
    public static final int STATE_WAITING = 3;//等待下载
    public static final int STATE_FAILED = 4;//下载失败
    public static final int STATE_DOWNLOADED = 5;//下载完成
    public static final int STATE_INSTALLED = 6;//已安装

    private Map<String, DownloadInfo> mDownloadInfoMap = new HashMap<String, DownloadInfo>();

    //DownloadManager作为被观察者，保存DownloadManager的观察者，一个应用的包名对应一个观察者（也可以设计成对应多个观察者）
    //一个应用的下载状态就会通知对应的观察者
    private Map<String, Observer> mDownloadObservers = new HashMap<String, Observer>();

    private DownloadManager() {
        mOkHttpClient = new OkHttpClient();
    }

    public void createDownloadDirectory() {
        File directoryFile = new File(DOWNLOAD_DIRECTORY);
        if (!directoryFile.exists()) {
            directoryFile.mkdirs();
        }
    }

    public static DownloadManager getInstance() {
        if (sDownloadManager == null) {
            synchronized (DownloadManager.class) {
                if (sDownloadManager == null) {
                    sDownloadManager = new DownloadManager();
                }
            }
        }
        return sDownloadManager;
    }


    /**
     * 执行下载
     */
    public void download(DownloadInfo downloadInfo) {
        DownloadTask downloadTask = new DownloadTask(downloadInfo);
        downloadInfo.setDownloadStatus(STATE_WAITING);
        downloadInfo.setDownloadTask(downloadTask);
        notifyObservers(downloadInfo);
        ThreadPoolProxy.getInstance().execute(downloadTask);
    }

    /**
     * 初始化下载信息
     */
    public DownloadInfo initDownloadInfo(Context context, String packageName, int size, String downloadUrl) {
        //如果已经初始化过对应包名的下载信息，则直接返回
        if (mDownloadInfoMap.get(packageName) != null) {
            return mDownloadInfoMap.get(packageName);
        }
        DownloadInfo downloadInfo = new DownloadInfo();
        downloadInfo.setPackageName(packageName);
        downloadInfo.setSize(size);
        downloadInfo.setDownloadUrl(downloadUrl);

        if (isInstalled(context, packageName)) {
            downloadInfo.setDownloadStatus(STATE_INSTALLED);
        } else if (isDownloaded(downloadInfo)) {
            downloadInfo.setDownloadStatus(STATE_DOWNLOADED);
        } else {
            downloadInfo.setDownloadStatus(STATE_UN_DOWNLOAD);
        }
        //保存下载信息
        mDownloadInfoMap.put(downloadInfo.getPackageName(), downloadInfo);
        return downloadInfo;
    }

    /**
     * 是否已经下载
     */
    private boolean isDownloaded(DownloadInfo downloadInfo) {
        String filePath = DOWNLOAD_DIRECTORY + downloadInfo.getPackageName() + ".apk";
        downloadInfo.setFilePath(filePath);
        File file = new File(filePath);
        if (file.exists()) {
            if (file.length() == downloadInfo.getSize()) {
                return true;
            } else {
                //记录已经下载了多少
                downloadInfo.setProgress(file.length());
                return false;
            }
        }
        return false;
    }

    /**
     * 判断应用是否已安装
     */
    private boolean isInstalled(Context context, String packageName) {
        try {
            context.getPackageManager().getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    private DownloadInfo getDownloadInfo(String packageName) {
        return mDownloadInfoMap.get(packageName);
    }

    /**
     * 暂停下载
     */
    private void pauseDownload(DownloadInfo downloadInfo) {
        downloadInfo.setDownloadStatus(STATE_PAUSE);
        notifyObservers(downloadInfo);
    }

    /**
     * 取消下载
     */
    private void cancelDownload(DownloadInfo downloadInfo) {
        ThreadPoolProxy.getInstance().remove(downloadInfo.getDownloadTask());
        downloadInfo.setDownloadStatus(STATE_UN_DOWNLOAD);
        notifyObservers(downloadInfo);
    }

    /**
     * 打开app
     */
    private void openApp(Context context, DownloadInfo downloadInfo) {
        Intent intent = context.getPackageManager().getLaunchIntentForPackage(downloadInfo.getPackageName());
        context.startActivity(intent);
    }

    /**
     * 安装apk, 在模拟器上可能失败，模拟器如果是x86而应用不支持
     * D/InstallAppProgress: Installation error code: -113
     * http://grepcode.com/file/repository.grepcode.com/java/ext/com.google.android/android/5.1.1_r1/android/content/pm/PackageManager.java#PackageManager.0INSTALL_FAILED_INVALID_APK
     * public static final int INSTALL_FAILED_NO_MATCHING_ABIS = -113;
     */
    private void installApk(Context context, DownloadInfo downloadInfo) {
        File file = new File(downloadInfo.getFilePath());
        if (file.exists()) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
            context.startActivity(intent);
        }
    }

    /**
     * 下载任务
     */
    private class DownloadTask implements Runnable {

        private DownloadInfo mDownloadInfo;

        private DownloadTask(DownloadInfo downloadInfo) {
            mDownloadInfo = downloadInfo;
        }

        @Override
        public void run() {
            InputStream inputStream = null;
            FileOutputStream fileOutputStream = null;
            try {
                File file = new File(mDownloadInfo.getFilePath());
                if (!file.exists()) {
                    file.createNewFile();
                }
                //获取下载apk的url,传入当前下载进度，用作断点续传
                String url = URLUtils.getDownloadURL(mDownloadInfo.getDownloadUrl(), mDownloadInfo.getProgress());
                Request request = new Request.Builder().url(url).get().build();
                //同步请求
                Response response = mOkHttpClient.newCall(request).execute();
                if (response.isSuccessful()) {
                    inputStream = response.body().byteStream();
                    fileOutputStream = new FileOutputStream(file, true);//往文件后面写数据
                    byte[] buffer = new byte[1024];
                    int len = -1;
                    while ((len = inputStream.read(buffer)) != -1) {
                        //如果下载的状态变为暂停，跳出循环
                        if (mDownloadInfo.getDownloadStatus() == STATE_PAUSE) {
                            return;
                        }
                        fileOutputStream.write(buffer, 0, len);
                        //更新下载进度
                        long progress = mDownloadInfo.getProgress() + len;
                        mDownloadInfo.setProgress(progress);
                        updateStatus(STATE_DOWNLOADING);
                        //下载完成跳出循环
                        if (progress == mDownloadInfo.getSize()) {
                            break;
                        }
                    }
                    //更新状态已下载
                    updateStatus(STATE_DOWNLOADED);

                } else {
                    //更新状态下载失败
                    updateStatus(STATE_FAILED);
                }
            } catch (IOException e) {
                e.printStackTrace();
                //更新状态下载失败
                updateStatus(STATE_FAILED);
                if (inputStream != null) {
                    closeStream(inputStream);
                }
                if (fileOutputStream != null) {
                    closeStream(fileOutputStream);
                }
            }
        }

        private void updateStatus(int status) {
            mDownloadInfo.setDownloadStatus(status);
            notifyObservers(mDownloadInfo);
        }
    }


    private void closeStream(Closeable closeable) {
        try {
            closeable.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 添加观察者
     */
    public void addObserver(String packageName, Observer observer) {
        mDownloadObservers.put(packageName, observer);
    }

    /**
     * 移除观察者
     */
    public void removeObserver(String packageName) {
        mDownloadObservers.remove(packageName);
    }

    /**
     * 通知观察者
     */
    private void notifyObservers(DownloadInfo downloadInfo) {
        Observer observer = mDownloadObservers.get(downloadInfo.getPackageName());
        if (observer != null) {
            observer.update(null, downloadInfo);
        }
    }

    /**
     * 处理下载的动作
     */
    public void handleDownloadAction(Context context, String packageName) {
        processByStatus(context, getDownloadInfo(packageName));
    }

    private void processByStatus(Context context, DownloadInfo downloadInfo) {
        switch (downloadInfo.getDownloadStatus()) {
            case STATE_UN_DOWNLOAD:
                download(downloadInfo);
                break;
            case STATE_DOWNLOADING:
                pauseDownload(downloadInfo);
                break;
            case STATE_DOWNLOADED:
                installApk(context, downloadInfo);
                break;
            case DownloadManager.STATE_PAUSE:
                download(downloadInfo);
                break;
            case DownloadManager.STATE_WAITING:
                cancelDownload(downloadInfo);
                break;
            case DownloadManager.STATE_INSTALLED:
                openApp(context, downloadInfo);
                break;
            case DownloadManager.STATE_FAILED:
                download(downloadInfo);
                break;
        }
    }
}
