package com.itheima.googleplaydemo.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.itheima.googleplaydemo.R;
import com.itheima.googleplaydemo.bean.AppDetailBean;
import com.itheima.googleplaydemo.network.DownloadInfo;
import com.itheima.googleplaydemo.network.DownloadManager;

import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Leon on 2017/2/2.
 */

public class AppDetailBottomBar extends RelativeLayout implements Observer {

    @BindView(R.id.favorite)
    Button mFavorite;
    @BindView(R.id.download)
    ProgressButton mDownload;
    @BindView(R.id.share)
    Button mShare;

    private AppDetailBean mAppDetailBean;

    public AppDetailBottomBar(Context context) {
        this(context, null);
    }

    public AppDetailBottomBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_app_detail_bottom_bar, this);
        ButterKnife.bind(this, this);
    }

    private void updateDownloadButton(DownloadInfo downloadInfo) {
        mDownload.setBackgroundResource(R.drawable.selector_app_detail_bottom_normal);
        switch (downloadInfo.getDownloadStatus()) {
            case DownloadManager.STATE_UN_DOWNLOAD:
                mDownload.setText(R.string.download);
                break;
            case DownloadManager.STATE_DOWNLOADING:
                mDownload.setBackgroundResource(R.drawable.selector_app_detail_bottom_downloading);
                mDownload.setMax(downloadInfo.getMax());
                mDownload.setProgress(downloadInfo.getProgress());
                int ration = (int) (downloadInfo.getProgress() * 1.0f / downloadInfo.getMax() * 100);
                String progress = String.format(getContext().getString(R.string.download_progress), ration);
                mDownload.setText(progress);
                break;
            case DownloadManager.STATE_DOWNLOADED:
                mDownload.setText(R.string.install);
                mDownload.clearProgress();
                break;
            case DownloadManager.STATE_PAUSE:
                mDownload.setText(R.string.continue_download);
                break;
            case DownloadManager.STATE_WAITING:
                mDownload.setText(R.string.waiting);
                break;
            case DownloadManager.STATE_INSTALLED:
                mDownload.setText(R.string.open);
                break;
            case DownloadManager.STATE_FAILED:
                mDownload.setText(R.string.retry);
                break;
        }
    }

    public void bindView(AppDetailBean appDetailBean) {
        mAppDetailBean = appDetailBean;
        DownloadManager.getInstance().addObserver(appDetailBean.getPackageName(), this);
        DownloadInfo downloadInfo = DownloadManager.getInstance().getDownloadInfo(getContext(), appDetailBean);
        updateDownloadButton(downloadInfo);
    }

    @Override
    public void update(Observable o, Object arg) {
        final DownloadInfo downloadInfo = (DownloadInfo) arg;
        post(new Runnable() {
            @Override
            public void run() {
                updateDownloadButton(downloadInfo);
            }
        });
    }

    @OnClick(R.id.download)
    public void onClick() {
        handleDownloadClick();
    }


    private void handleDownloadClick() {
        DownloadInfo downloadInfo = DownloadManager.getInstance().getDownloadInfo(getContext(), mAppDetailBean);
        switch (downloadInfo.getDownloadStatus()) {
            case DownloadManager.STATE_UN_DOWNLOAD:
                DownloadManager.getInstance().download(downloadInfo);
                break;
            case DownloadManager.STATE_DOWNLOADING:
                DownloadManager.getInstance().pauseDownload(downloadInfo);
                break;
            case DownloadManager.STATE_DOWNLOADED:
                DownloadManager.getInstance().installApk(getContext(), downloadInfo);
                break;
            case DownloadManager.STATE_PAUSE:
                DownloadManager.getInstance().download(downloadInfo);
                break;
            case DownloadManager.STATE_WAITING:
                DownloadManager.getInstance().cancelDownload(downloadInfo);
                break;
            case DownloadManager.STATE_INSTALLED:
                DownloadManager.getInstance().openApp(getContext(), downloadInfo);
                break;
            case DownloadManager.STATE_FAILED:
                DownloadManager.getInstance().download(downloadInfo);
                break;
        }
    }
}
