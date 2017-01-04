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
        Log.d(TAG, "onDraw: " + left + "," + top + "," + right + "," + bottom);
        canvas.drawArc(mRectF, -90, 180, false, mPaint);
        super.onDraw(canvas);
    }

    @OnClick(R.id.download)
    public void onClick() {
    }
}
