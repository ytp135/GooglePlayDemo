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
                //保存全部展开后的大小
                mAppDetailDesOriginHeight = mAppDetailDes.getHeight();
                //设置初始显示7行
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
            //关闭
            mAppDetailDes.setLines(7);
            //获取7行时的高度
            mAppDetailDes.measure(0, 0);
            int measuredHeight = mAppDetailDes.getMeasuredHeight();
            //动画从原始高度到7行高度
            animateViewHeight(mAppDetailDes, mAppDetailDesOriginHeight, measuredHeight);
            //箭头逆时针旋转180度
            ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(mAppDetailDesArrow, "rotation", -180, 0);
            objectAnimator.start();

        } else {
            //打开
            //从7行高度到原始高度
            int measuredHeight = mAppDetailDes.getMeasuredHeight();
            animateViewHeight(mAppDetailDes, measuredHeight, mAppDetailDesOriginHeight);
            //箭头顺时针旋转180度
            ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(mAppDetailDesArrow, "rotation", 0, -180);
            objectAnimator.start();
        }
        descriptionOpen = !descriptionOpen;
    }
}
