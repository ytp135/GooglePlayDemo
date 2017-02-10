package com.itheima.googleplaydemo.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.Button;

import com.itheima.googleplaydemo.R;
import com.itheima.googleplaydemo.bean.AppDetailBean;
import com.itheima.googleplaydemo.network.DownloadInfo;
import com.itheima.googleplaydemo.network.DownloadManager;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Leon on 2017/1/4.
 */

public class DownloadButton extends Button implements Observer{

    private Drawable mDrawable;

    private float mMax = 100;

    public float getProgress() {
        return mProgress;
    }

    public void setProgress(float progress) {
        mProgress = progress;
        postInvalidate();
    }

    public float getMax() {
        return mMax;
    }

    public void setMax(float max) {
        mMax = max;
    }

    private float mProgress;

    private boolean enableProgress = true;

    public DownloadButton(Context context) {
        this(context, null);
    }

    public DownloadButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        mDrawable = new ColorDrawable(Color.BLUE);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        //是否绘制进度
        if (enableProgress) {
            //根据进度计算出drawable的右边位置
            int right = (int) ((mProgress / mMax) * getWidth());
            mDrawable.setBounds(0, 0, right, getHeight());
            //将drawable绘制到画布上
            mDrawable.draw(canvas);
        }
        super.onDraw(canvas);
    }

    /**
     * 清空进度
     */
    public void clearProgress() {
        enableProgress = false;
        invalidate();
    }

    public void syncState(AppDetailBean appDetailBean) {
        DownloadManager.getInstance().addObserver(appDetailBean.getPackageName(), this);
        DownloadInfo downloadInfo = DownloadManager.getInstance().initDownloadInfo(getContext(), appDetailBean.getPackageName(), appDetailBean.getSize(), appDetailBean.getDownloadUrl());
        updateStatus(downloadInfo);
    }

    @Override
    public void update(Observable o, Object arg) {
        final DownloadInfo downloadInfo = (DownloadInfo) arg;
        post(new Runnable() {
            @Override
            public void run() {
                updateStatus(downloadInfo);
            }
        });
    }


    private void updateStatus(DownloadInfo downloadInfo) {
        setBackgroundResource(R.drawable.selector_app_detail_bottom_normal);
        switch (downloadInfo.getDownloadStatus()) {
            case DownloadManager.STATE_UN_DOWNLOAD:
                setText(R.string.download);
                break;
            case DownloadManager.STATE_DOWNLOADING:
                setBackgroundResource(R.drawable.selector_app_detail_bottom_downloading);
                setMax(downloadInfo.getSize());
                setProgress(downloadInfo.getProgress());
                int ration = (int) (downloadInfo.getProgress() * 1.0f / downloadInfo.getSize() * 100);
                String progress = String.format(getContext().getString(R.string.download_progress), ration);
                setText(progress);
                break;
            case DownloadManager.STATE_DOWNLOADED:
                setText(R.string.install);
                clearProgress();
                break;
            case DownloadManager.STATE_PAUSE:
                setText(R.string.continue_download);
                break;
            case DownloadManager.STATE_WAITING:
                setText(R.string.waiting);
                break;
            case DownloadManager.STATE_INSTALLED:
                setText(R.string.open);
                break;
            case DownloadManager.STATE_FAILED:
                setText(R.string.retry);
                break;
        }
    }
}
