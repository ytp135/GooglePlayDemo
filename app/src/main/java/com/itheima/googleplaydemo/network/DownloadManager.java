package com.itheima.googleplaydemo.network;

import android.os.Environment;

import com.itheima.googleplaydemo.app.Constant;
import com.itheima.googleplaydemo.utils.concurrent.ThreadPoolProxyFactory;

import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Leon on 2017/1/5.
 */

public class DownloadManager {

    private static final String TAG = "DownloadManager";

    private static final String DOWNLOAD_DIR = "/Android/data/";

    private static DownloadManager sDownloadManager;

    private OkHttpClient mOkHttpClient;

    public static final int STATE_UN_DOWNLOAD = 0;//未下载
    public static final int STATE_DOWNLOADING = 1;//下载中
    public static final int STATE_PAUSE = 2;//暂停下载
    public static final int STATE_WAITING = 3;//等待下载
    public static final int STATE_FAILED = 4;//下载失败
    public static final int STATE_DOWNLOADED = 5;//下载完成
    public static final int STATE_INSTALLED = 6;//已安装

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
        DownloadTask downloadTask = new DownloadTask(downloadInfo);
        downloadInfo.setDownloadStatus(STATE_WAITING);
        ThreadPoolProxyFactory.getDownloadThreadPoolProxy().execute(downloadTask);
    }

    public class DownloadTask implements Runnable {

        private DownloadInfo mDownloadInfo;

        public DownloadTask(DownloadInfo downloadInfo) {
            mDownloadInfo = downloadInfo;
        }

        @Override
        public void run() {
            mDownloadInfo.setDownloadStatus(STATE_DOWNLOADING);
            String url = Constant.URL_DOWNLOAD + mDownloadInfo.getDownloadUrl();
            Request request = new Request.Builder().url(url).get().build();
            String directory = Environment.getExternalStorageDirectory() + DOWNLOAD_DIR + mDownloadInfo.getPackageName() + "/apk/";
            File directoryFile = new File(directory);
            if (!directoryFile.exists()) {
                directoryFile.mkdirs();
            }
            String fileName = mDownloadInfo.getApkName() + ".apk";
            InputStream inputStream = null;
            FileOutputStream fileOutputStream = null;
            try {
                Response response = mOkHttpClient.newCall(request).execute();
                if (response.isSuccessful()) {
                    inputStream = response.body().byteStream();
                    File file = new File(directory, fileName);
                    boolean success = file.createNewFile();
                    if (success) {
                        fileOutputStream = new FileOutputStream(file, true);
                        byte[] buffer = new byte[1024];
                        int len = -1;
                        while ((len = inputStream.read(buffer)) != -1) {
                            fileOutputStream.write(buffer, 0, len);
                        }
                        mDownloadInfo.setDownloadStatus(STATE_DOWNLOADED);
                    }
                } else {
                    mDownloadInfo.setDownloadStatus(STATE_FAILED);
                }
            } catch (IOException e) {
                e.printStackTrace();
                mDownloadInfo.setDownloadStatus(STATE_FAILED);
                if (inputStream != null) {
                    closeStream(inputStream);
                }
                if (fileOutputStream != null) {
                    closeStream(fileOutputStream);
                }
            }
        }
    }

    private void closeStream(Closeable closeable) {
        try {
            closeable.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
