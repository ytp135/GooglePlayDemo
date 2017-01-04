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
        invalidate();
    }

    public float getMax() {
        return mMax;
    }

    public void setMax(float max) {
        mMax = max;
    }

    private float mProgress;

    public ProgressButton(Context context) {
        this(context, null);
    }

    public ProgressButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        mDrawable = new ColorDrawable(Color.BLUE);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        int right = (int) ((mProgress / mMax) * getWidth());
        mDrawable.setBounds(0, 0, right, getHeight());
        mDrawable.draw(canvas);
        super.onDraw(canvas);
    }
}
