package com.itheima.googleplaydemo.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;
import com.itheima.googleplaydemo.R;
import com.itheima.googleplaydemo.app.Constant;
import com.itheima.googleplaydemo.bean.AppDetailBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.itheima.googleplaydemo.utils.UIUtils.animateViewHeight;
import static com.itheima.googleplaydemo.utils.UIUtils.rotateView;

/**
 * Created by Leon on 2017/2/2.
 */

public class AppDetailSecurityView extends RelativeLayout {

    @BindView(R.id.app_detail_security_tags)
    LinearLayout mAppDetailSecurityTags;
    @BindView(R.id.app_detail_security_arrow)
    ImageView mAppDetailSecurityArrow;
    @BindView(R.id.app_detail_security_des)
    LinearLayout mAppDetailSecurityDes;

    private boolean securityInfoOpen = false;


    public AppDetailSecurityView(Context context) {
        this(context, null);
    }

    public AppDetailSecurityView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_app_detail_security, this);
        ButterKnife.bind(this, this);
    }

    public void bindView(AppDetailBean appDetailBean) {
        for (int i = 0; i < appDetailBean.getSafe().size(); i++) {
            AppDetailBean.SafeBean safeBean = appDetailBean.getSafe().get(i);
            //Add tag
            ImageView tag = new ImageView(getContext());
            mAppDetailSecurityTags.addView(tag);
            Glide.with(getContext())
                    .load(Constant.URL_IMAGE + safeBean.getSafeUrl())
                    .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                    .into(tag);

            //Add one line description
            LinearLayout line = new LinearLayout(getContext());
            ImageView ivDes = new ImageView(getContext());
            TextView tvDes = new TextView(getContext());
            tvDes.setText(safeBean.getSafeDes());
            if (safeBean.getSafeDesColor() == 0) {
                tvDes.setTextColor(getResources().getColor(R.color.app_detail_safe_normal));
            } else {
                tvDes.setTextColor(getResources().getColor(R.color.app_detail_safe_warning));
            }

            line.addView(ivDes);
            Glide.with(getContext())
                    .load(Constant.URL_IMAGE + safeBean.getSafeDesUrl())
                    .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                    .into(ivDes);
            line.addView(tvDes);

            mAppDetailSecurityDes.addView(line);
            collapseAppDetailSecurity();
        }
    }

    private void collapseAppDetailSecurity() {
        ViewGroup.LayoutParams layoutParams = mAppDetailSecurityDes.getLayoutParams();
        layoutParams.height = 0;
        mAppDetailSecurityDes.setLayoutParams(layoutParams);
    }

    @OnClick(R.id.app_detail_security_arrow)
    public void onClick() {
        toggleSecurityInfo();
    }

    /**
     * 打开或者关闭安全信息
     */
    private void toggleSecurityInfo() {
        if (securityInfoOpen) {
            //关闭
            int measuredHeight = mAppDetailSecurityDes.getMeasuredHeight();
            animateViewHeight(mAppDetailSecurityDes, measuredHeight, 0);
            rotateView(mAppDetailSecurityArrow, -180, 0);

        } else {
            //打开
            //测量模式为UNSPECIFIED
            mAppDetailSecurityDes.measure(0, 0);
            //获取mAppDetailSecurityDes完全展开应该有的高度
            int measuredHeight = mAppDetailSecurityDes.getMeasuredHeight();
            animateViewHeight(mAppDetailSecurityDes, 0, measuredHeight);
            rotateView(mAppDetailSecurityArrow, 0, -180);
        }
        securityInfoOpen = !securityInfoOpen;
    }
}
