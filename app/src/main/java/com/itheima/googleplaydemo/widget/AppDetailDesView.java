package com.itheima.googleplaydemo.widget;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.itheima.googleplaydemo.R;
import com.itheima.googleplaydemo.bean.AppDetailBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.itheima.googleplaydemo.utils.UIUtils.animateViewHeight;

/**
 * Created by Leon on 2017/2/2.
 */

public class AppDetailDesView extends RelativeLayout {

    @BindView(R.id.app_detail_des)
    TextView mAppDetailDes;
    @BindView(R.id.app_detail_author)
    TextView mAppDetailAuthor;
    @BindView(R.id.app_detail_des_arrow)
    ImageView mAppDetailDesArrow;


    private boolean descriptionOpen = false;
    private int mAppDetailDesOriginHeight;

    public AppDetailDesView(Context context) {
        this(context, null);
    }

    public AppDetailDesView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_app_detail_des, this);
        ButterKnife.bind(this, this);
    }

    public void bindView(AppDetailBean appDetailBean) {
        mAppDetailAuthor.setText(appDetailBean.getAuthor());
        mAppDetailDes.setText(appDetailBean.getDes());
        mAppDetailDes.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mAppDetailDes.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                mAppDetailDesOriginHeight = mAppDetailDes.getHeight();
                mAppDetailDes.setLines(7);
            }
        });
    }

    @OnClick(R.id.app_detail_des_arrow)
    public void onClick() {
        toggleDescription();
    }


    private void toggleDescription() {
        if (descriptionOpen) {
            mAppDetailDes.setLines(7);
            mAppDetailDes.measure(0, 0);
            int measuredHeight = mAppDetailDes.getMeasuredHeight();
            animateViewHeight(mAppDetailDes, mAppDetailDesOriginHeight, measuredHeight);
            ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(mAppDetailDesArrow, "rotation", -180, 0);
            objectAnimator.start();

        } else {
            int measuredHeight = mAppDetailDes.getMeasuredHeight();
            animateViewHeight(mAppDetailDes, measuredHeight, mAppDetailDesOriginHeight);
            ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(mAppDetailDesArrow, "rotation", 0, -180);
            objectAnimator.start();
        }
        descriptionOpen = !descriptionOpen;
    }
}
