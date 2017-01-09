package com.itheima.googleplaydemo.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.itheima.googleplaydemo.R;
import com.itheima.googleplaydemo.network.DownloadInfo;
import com.itheima.googleplaydemo.network.DownloadManager;

import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Leon on 2017/1/4.
 */

public class DownloadProgressView extends FrameLayout implements Observer{

    private static final String TAG = "DownloadProgressView";

    @BindView(R.id.download)
    ImageView mDownload;
    @BindView(R.id.download_info)
    TextView mDownloadText;


    private Paint mPaint;
    private RectF mRectF;

    private DownloadInfo mDownloadInfo;

    private boolean enableProgress;

    public DownloadProgressView(Context context) {
        this(context, null);
    }

    public DownloadProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_download_progress, this);
        ButterKnife.bind(this, this);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(5);
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.STROKE);
        mRectF = new RectF();
        setWillNotDraw(false);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        if (enableProgress) {
            int left = mDownload.getLeft() -3;
            int top = mDownload.getTop() - 3;
            int right = mDownload.getRight() + 3;
            int bottom = mDownload.getBottom() + 3;
            mRectF.set(left, top, right, bottom);
            float sweepAngle = (mDownloadInfo.getProgress() * 1.0f / mDownloadInfo.getMax()) * 360;
            canvas.drawArc(mRectF, -90, sweepAngle, false, mPaint);
        }
        super.onDraw(canvas);
    }

    @OnClick(R.id.download)
    public void onClick() {
        handleDownloadClick();
    }

    public void bindView(DownloadInfo downloadInfo) {
        if (mDownloadInfo != null) {
            DownloadManager.getInstance().removeObserver(mDownloadInfo.getPackageName());
            Log.d(TAG, "bindView: remove previous observer");
        }
        mDownloadInfo = downloadInfo;
        DownloadManager.getInstance().addObserver(mDownloadInfo.getPackageName(), this);
        updateStatus(downloadInfo);
    }

    private void updateStatus(DownloadInfo downloadInfo) {
        Log.d(TAG, "updateStatus: " + downloadInfo.getDownloadStatus());
        //当移除之前的观察者后，还有残留的runnable没有执行，这里过滤掉之前的runnable
        if (!downloadInfo.getPackageName().equals(mDownloadInfo.getPackageName())) {
            return;
        }
        switch (downloadInfo.getDownloadStatus()) {
            case DownloadManager.STATE_UN_DOWNLOAD:
                mDownloadText.setText(R.string.download);
                mDownload.setImageResource(R.drawable.ic_download);
                break;
            case DownloadManager.STATE_DOWNLOADED:
                mDownloadText.setText(R.string.install);
                mDownload.setImageResource(R.drawable.ic_install);
                enableProgress = false;
                break;
            case DownloadManager.STATE_DOWNLOADING:
                int progress = (int) (mDownloadInfo.getProgress() * 1.0f / mDownloadInfo.getMax() * 100);
                mDownloadText.setText(String.format(getResources().getString(R.string.download_progress), progress));
                mDownload.setImageResource(R.drawable.ic_pause);
                enableProgress = true;
                break;
            case DownloadManager.STATE_FAILED:
                mDownloadText.setText(R.string.retry);
                mDownload.setImageResource(R.drawable.ic_redownload);
                break;
            case DownloadManager.STATE_INSTALLED:
                mDownloadText.setText(R.string.open);
                mDownload.setImageResource(R.drawable.ic_install);
                break;
            case DownloadManager.STATE_PAUSE:
                mDownloadText.setText(R.string.continue_download);
                mDownload.setImageResource(R.drawable.ic_download);
                break;
            case DownloadManager.STATE_WAITING:
                mDownloadText.setText(R.string.waiting);
                mDownload.setImageResource(R.drawable.ic_cancel);
                break;
        }
    }

    private void handleDownloadClick() {
        switch (mDownloadInfo.getDownloadStatus()) {
            case DownloadManager.STATE_UN_DOWNLOAD:
                DownloadManager.getInstance().download(mDownloadInfo);
                break;
            case DownloadManager.STATE_DOWNLOADING:
                DownloadManager.getInstance().pauseDownload(mDownloadInfo);
                break;
            case DownloadManager.STATE_DOWNLOADED:
                DownloadManager.getInstance().installApk(getContext(), mDownloadInfo);
                break;
            case DownloadManager.STATE_PAUSE:
                DownloadManager.getInstance().download(mDownloadInfo);
                break;
            case DownloadManager.STATE_WAITING:
                DownloadManager.getInstance().cancelDownload(mDownloadInfo);
                break;
            case DownloadManager.STATE_INSTALLED:
                DownloadManager.getInstance().openApp(getContext(), mDownloadInfo);
                break;
            case DownloadManager.STATE_FAILED:
                DownloadManager.getInstance().download(mDownloadInfo);
                break;
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        DownloadManager.getInstance().removeObserver(mDownloadInfo.getPackageName());
    }

    @Override
    public void update(Observable o, final Object arg) {
        post(new Runnable() {
            @Override
            public void run() {
                updateStatus((DownloadInfo) arg);
            }
        });
    }
}
