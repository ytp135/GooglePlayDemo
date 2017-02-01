package com.itheima.googleplaydemo.widget;

import android.content.Context;
import android.text.format.Formatter;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.itheima.googleplaydemo.R;
import com.itheima.googleplaydemo.app.Constant;
import com.itheima.googleplaydemo.bean.AppListItem;
import com.itheima.googleplaydemo.network.DownloadInfo;
import com.itheima.googleplaydemo.network.DownloadManager;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 创建者: Leon
 * 创建时间: 2016/9/22 11:42
 * 描述： TODO
 */
public class AppListItemView extends RelativeLayout {
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
    @BindView(R.id.download_progress)
    DownloadProgressView mDownloadProgressView;

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
        mAppSize.setText(Formatter.formatFileSize(getContext(), item.getSize()));
        mAppRating.setRating(item.getStars());
        Glide.with(getContext()).load(Constant.URL_IMAGE + item.getIconUrl()).into(mAppIcon);

        //获取下载情况
        DownloadInfo downloadInfo = DownloadManager.getInstance().getDownloadInfo(getContext(), item);
        mDownloadProgressView.bindView(downloadInfo);
    }
}
