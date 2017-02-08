package com.itheima.googleplaydemo.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * Created by Leon on 2017/1/4.
 */

public class ProgressButton extends Button {

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

    public ProgressButton(Context context) {
        this(context, null);
    }

    public ProgressButton(Context context, AttributeSet attrs) {
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
}
