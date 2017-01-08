package com.itheima.googleplaydemo.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.itheima.googleplaydemo.R;
import com.itheima.googleplaydemo.network.DownloadInfo;
import com.itheima.googleplaydemo.network.DownloadManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Leon on 2017/1/4.
 */

public class DownloadProgressView extends FrameLayout {

    private static final String TAG = "DownloadProgressView";

    @BindView(R.id.download)
    ImageView mDownload;
    @BindView(R.id.download_info)
    TextView mDownloadInfo;


    private Paint mPaint;
    private RectF mRectF;

    private float mMax;

    public float getProgress() {
        return mProgress;
    }

    public void setProgress(float progress) {
        mProgress = progress;
    }

    public float getMax() {
        return mMax;
    }

    public void setMax(float max) {
        mMax = max;
    }

    private float mProgress;

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
        mPaint.setStrokeWidth(3);
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.STROKE);

        mRectF = new RectF();
        setWillNotDraw(false);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        int left = mDownload.getLeft();
        int top = mDownload.getTop();
        int right = mDownload.getRight();
        int bottom = mDownload.getBottom();
        mRectF.set(left, top, right, bottom);
        float sweepAngle = (mProgress / mMax) * 360;
        canvas.drawArc(mRectF, -90, sweepAngle, false, mPaint);
        super.onDraw(canvas);
    }

    @OnClick(R.id.download)
    public void onClick() {
    }

    public void bindView(DownloadInfo downloadInfo) {
        switch (downloadInfo.getDownloadStatus()) {
            case DownloadManager.STATE_UN_DOWNLOAD:
                mDownloadInfo.setText(R.string.download);
                mDownload.setImageResource(R.drawable.ic_download);
                break;
            case DownloadManager.STATE_DOWNLOADED:
                mDownloadInfo.setText(R.string.install);
                mDownload.setImageResource(R.drawable.ic_install);
                break;
            case DownloadManager.STATE_DOWNLOADING:
//                mDownloadInfo.setText(R.string.down);
                mDownload.setImageResource(R.drawable.ic_pause);
                break;
            case DownloadManager.STATE_FAILED:
                mDownloadInfo.setText(R.string.retry);
                mDownload.setImageResource(R.drawable.ic_redownload);
                break;
            case DownloadManager.STATE_INSTALLED:
                mDownloadInfo.setText(R.string.open);
                mDownload.setImageResource(R.drawable.ic_install);
                break;
            case DownloadManager.STATE_PAUSE:
                mDownloadInfo.setText(R.string.continue_download);
                mDownload.setImageResource(R.drawable.ic_download);
                break;
            case DownloadManager.STATE_WAITING:
                mDownloadInfo.setText(R.string.waiting);
                mDownload.setImageResource(R.drawable.ic_cancel);
                break;
        }
    }
}
