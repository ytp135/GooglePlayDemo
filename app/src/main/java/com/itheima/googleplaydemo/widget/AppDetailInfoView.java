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
import com.itheima.googleplaydemo.bean.AppDetailBean;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Leon on 2017/2/2.
 */

public class AppDetailInfoView extends RelativeLayout {

    @BindView(R.id.app_icon)
    ImageView mAppIcon;
    @BindView(R.id.app_name)
    TextView mAppName;
    @BindView(R.id.app_rating)
    RatingBar mAppRating;
    @BindView(R.id.download_count)
    TextView mDownloadCount;
    @BindView(R.id.version_code)
    TextView mVersionCode;
    @BindView(R.id.time)
    TextView mTime;
    @BindView(R.id.app_size)
    TextView mAppSize;

    public AppDetailInfoView(Context context) {
        this(context, null);
    }

    public AppDetailInfoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_app_detail_info, this);
        ButterKnife.bind(this, this);
    }

    public void bindView(AppDetailBean appDetailBean) {
        String iconUrl = Constant.URL_IMAGE + appDetailBean.getIconUrl();
        Glide.with(getContext()).load(iconUrl).placeholder(R.drawable.ic_default).into(mAppIcon);
        mAppName.setText(appDetailBean.getName());
        mAppRating.setRating(appDetailBean.getStars());

        String downloadCount = String.format(getResources().getString(R.string.download_count), appDetailBean.getDownloadNum());
        mDownloadCount.setText(downloadCount);

        String versionCode = String.format(getResources().getString(R.string.version_code), appDetailBean.getVersion());
        mVersionCode.setText(versionCode);

        String timestamp = String.format(getResources().getString(R.string.time), appDetailBean.getDate());
        mTime.setText(timestamp);

        String size = String.format(getResources().getString(R.string.app_size), Formatter.formatFileSize(getContext(), appDetailBean.getSize()));
        mAppSize.setText(size);

    }
}
