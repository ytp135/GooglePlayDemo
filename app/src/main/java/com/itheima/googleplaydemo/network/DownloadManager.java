package com.itheima.googleplaydemo.network;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.util.Log;

import com.itheima.googleplaydemo.app.Constant;
import com.itheima.googleplaydemo.bean.AppDetailBean;
import com.itheima.googleplaydemo.bean.AppListItem;
import com.itheima.googleplaydemo.utils.URLUtils;
import com.itheima.googleplaydemo.utils.concurrent.ThreadPoolProxyFactory;

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

public class DownloadManager{

    private static final String TAG = "DownloadManager";
    private static DownloadManager sDownloadManager;

    private OkHttpClient mOkHttpClient;

    public static final int STATE_UN_DOWNLOAD = 0;//未下载
    public static final int STATE_DOWNLOADING = 1;//下载中
    public static final int STATE_PAUSE = 2;//暂停下载
    public static final int STATE_WAITING = 3;//等待下载
    public static final int STATE_FAILED = 4;//下载失败
    public static final int STATE_DOWNLOADED = 5;//下载完成
    public static final int STATE_INSTALLED = 6;//已安装

    private Map<String, DownloadInfo> mStringDownloadInfoMap = new HashMap<String, DownloadInfo>();

    private Map<String, Observer> mDownloadObservers = new HashMap<String, Observer>();

    private DownloadManager() {
        mOkHttpClient = new OkHttpClient();
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


    public void download(DownloadInfo downloadInfo) {
        Log.d(TAG, "download: start download");
        DownloadTask downloadTask = new DownloadTask(downloadInfo);
        downloadInfo.setDownloadStatus(STATE_WAITING);
        downloadInfo.setDownloadTask(downloadTask);
        mStringDownloadInfoMap.put(downloadInfo.getPackageName(), downloadInfo);

        notifyObservers(downloadInfo);
        ThreadPoolProxyFactory.getDownloadThreadPoolProxy().execute(downloadTask);
    }

    public DownloadInfo getDownloadInfo(Context context, AppListItem item) {
        DownloadInfo downloadInfo = new DownloadInfo();
        String downloadUrl = Constant.URL_DOWNLOAD + item.getDownloadUrl();
        downloadInfo.setDownloadUrl(downloadUrl);
        String appFileName = item.getPackageName() + ".apk";
        File file = new File(DownloadInfo.DOWNLOAD_DIRECTORY, appFileName);
        downloadInfo.setAppName(appFileName);

        if (isInstalled(context, item.getPackageName())) {
            downloadInfo.setDownloadStatus(STATE_INSTALLED);
            return downloadInfo;
        }

        if (file.exists() && file.length() == item.getSize()) {
            downloadInfo.setDownloadStatus(STATE_DOWNLOADED);
            return downloadInfo;
        }

        if (mStringDownloadInfoMap.containsKey(item.getPackageName())) {
            return mStringDownloadInfoMap.get(item.getPackageName());
        }

        downloadInfo.setDownloadStatus(STATE_UN_DOWNLOAD);
        return downloadInfo;
    }

    private boolean isInstalled(Context context, String packageName) {
        try {
            context.getPackageManager().getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public DownloadInfo getDownloadInfo(Context context, AppDetailBean item) {
        DownloadInfo downloadInfo = new DownloadInfo();
        String appFileName = item.getPackageName() + ".apk";
        File file = new File(DownloadInfo.DOWNLOAD_DIRECTORY, appFileName);
        downloadInfo.setAppName(appFileName);
        downloadInfo.setPackageName(item.getPackageName());
        downloadInfo.setMax(item.getSize());
        long initRange = 0;
        if (file.exists()) {
            initRange = file.length();
        }
        downloadInfo.setProgress((int) initRange);
        downloadInfo.setDownloadUrl(item.getDownloadUrl());

        if (isInstalled(context, item.getPackageName())) {
            downloadInfo.setDownloadStatus(STATE_INSTALLED);
            return downloadInfo;
        }

        if (file.exists() && file.length() == item.getSize()) {
            downloadInfo.setDownloadStatus(STATE_DOWNLOADED);
            return downloadInfo;
        }

        if (mStringDownloadInfoMap.containsKey(item.getPackageName())) {
            return mStringDownloadInfoMap.get(item.getPackageName());
        }

        downloadInfo.setDownloadStatus(STATE_UN_DOWNLOAD);
        return downloadInfo;
    }

    public void pauseDownload(DownloadInfo downloadInfo) {
        downloadInfo.setDownloadStatus(STATE_PAUSE);
        notifyObservers(downloadInfo);
    }

    public void cancelDownload(DownloadInfo downloadInfo) {
        ThreadPoolProxyFactory.getDownloadThreadPoolProxy().remove(downloadInfo.getDownloadTask());
        downloadInfo.setDownloadStatus(STATE_UN_DOWNLOAD);
        notifyObservers(downloadInfo);
    }

    public void openApp(Context context, DownloadInfo downloadInfo) {
        Intent intent = context.getPackageManager().getLaunchIntentForPackage(downloadInfo.getPackageName());
        context.startActivity(intent);
    }

    /**
     * D/InstallAppProgress: Installation error code: -113
     *
     * http://grepcode.com/file/repository.grepcode.com/java/ext/com.google.android/android/5.1.1_r1/android/content/pm/PackageManager.java#PackageManager.0INSTALL_FAILED_INVALID_APK
     *
     *
     * @param context
     * @param downloadInfo
     */
    public void installApk(Context context, DownloadInfo downloadInfo) {
        File file = new File(DownloadInfo.DOWNLOAD_DIRECTORY, downloadInfo.getApkName());
        if (file.exists()) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
            context.startActivity(intent);
        }
    }

    public class DownloadTask implements Runnable {

        private DownloadInfo mDownloadInfo;

        public DownloadTask(DownloadInfo downloadInfo) {
            mDownloadInfo = downloadInfo;
        }

        @Override
        public void run() {
            InputStream inputStream = null;
            FileOutputStream fileOutputStream = null;
            try {
                File directoryFile = new File(DownloadInfo.DOWNLOAD_DIRECTORY);
                if (!directoryFile.exists()) {
                    directoryFile.mkdirs();
                }
                File file = new File(DownloadInfo.DOWNLOAD_DIRECTORY, mDownloadInfo.getApkName());
                long initRange = 0;
                boolean success = true;
                if (file.exists()) {
                    Log.d(TAG, "run: file exist");
                    initRange = file.length();
                    mDownloadInfo.setProgress((int) initRange);
                } else {
                    success = file.createNewFile();
                }
                String url = URLUtils.getDownloadURL(mDownloadInfo.getDownloadUrl(), initRange);
                Log.d(TAG, "run: " + url);
                Request request = new Request.Builder().url(url).get().build();
                Response response = mOkHttpClient.newCall(request).execute();
                if (response.isSuccessful()) {
                    Log.d(TAG, "run: get success response");
                    inputStream = response.body().byteStream();
                    if (success) {
                        fileOutputStream = new FileOutputStream(file, true);
                        byte[] buffer = new byte[1024];
                        int len = -1;
                        while ((len = inputStream.read(buffer)) != -1) {
                            if (mDownloadInfo.getDownloadStatus() == STATE_PAUSE) {
                                Log.d(TAG, "run: pause, quite run" );
                                return;
                            }
                            fileOutputStream.write(buffer, 0, len);
                            int progress = mDownloadInfo.getProgress() + len;
                            mDownloadInfo.setProgress(progress);
                            updateStatus(STATE_DOWNLOADING);

                            if (progress == mDownloadInfo.getMax()) {
                                break;
                            }
                        }
                        updateStatus(STATE_DOWNLOADED);
                    }
                } else {
                    updateStatus(STATE_FAILED);
                }
            } catch (IOException e) {
                e.printStackTrace();
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

    public void addObserver(String packageName, Observer observer) {
        mDownloadObservers.put(packageName, observer);
    }

    public void removeObserver(String packageName) {
        mDownloadObservers.remove(packageName);
    }

    private void notifyObservers(DownloadInfo downloadInfo) {
        Observer observer = mDownloadObservers.get(downloadInfo.getPackageName());
        if (observer != null) {
            observer.update(null, downloadInfo);
        }
    }

}
