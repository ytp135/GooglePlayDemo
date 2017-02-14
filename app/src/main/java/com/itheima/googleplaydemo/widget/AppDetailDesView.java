package com.itheima.googleplaydemo.widget;

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
import static com.itheima.googleplaydemo.utils.UIUtils.rotateView;

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

    private static final int MAX_LINES = 7;

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
                //若初始化时行数大于7行则设置高度为7
                if (mAppDetailDes.getLineCount() > MAX_LINES) {
                    //设置初始显示7行
                    mAppDetailDes.setLines(7);
                }
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
            if (mAppDetailDes.getLineCount() > MAX_LINES) {
                mAppDetailDes.setLines(7);
                //测量 获取7行时的高度
                mAppDetailDes.measure(0, 0);
            }
            int measuredHeight = mAppDetailDes.getMeasuredHeight();
            animateViewHeight(mAppDetailDes, mAppDetailDesOriginHeight, measuredHeight);
            rotateView(mAppDetailDesArrow, -180, 0);

        } else {
            //打开
            int measuredHeight = mAppDetailDes.getMeasuredHeight();
            animateViewHeight(mAppDetailDes, measuredHeight, mAppDetailDesOriginHeight);
            rotateView(mAppDetailDesArrow, 0, -180);
        }
        descriptionOpen = !descriptionOpen;
    }
}
