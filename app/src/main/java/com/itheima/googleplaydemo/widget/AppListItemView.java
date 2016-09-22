package com.itheima.googleplaydemo.widget;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.itheima.googleplaydemo.R;
import com.itheima.googleplaydemo.bean.AppListItem;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 创建者: Leon
 * 创建时间: 2016/9/22 11:42
 * 描述： TODO
 */
public class AppListItemView extends CardView {
    private static final String TAG = "AppListItemView";
    @BindView(R.id.app_icon)
    ImageView mAppIcon;
    @BindView(R.id.app_name)
    TextView mAppName;
    @BindView(R.id.app_rating)
    RatingBar mAppRating;
    @BindView(R.id.app_size)
    TextView mAppSize;
    @BindView(R.id.app_des)
    TextView mAppDes;

    public AppListItemView(Context context) {
        this(context, null);
    }

    public AppListItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.list_app_item, this);
        ButterKnife.bind(this, this);
    }

    public void bindView(AppListItem item) {
        mAppName.setText(item.getName());
        mAppDes.setText(item.getDes());
        mAppSize.setText(String.valueOf(item.getSize()));
    }
}
