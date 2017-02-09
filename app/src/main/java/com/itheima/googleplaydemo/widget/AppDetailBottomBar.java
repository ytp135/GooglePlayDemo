package com.itheima.googleplaydemo.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.itheima.googleplaydemo.R;
import com.itheima.googleplaydemo.bean.AppDetailBean;
import com.itheima.googleplaydemo.network.DownloadManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Leon on 2017/2/2.
 */

public class AppDetailBottomBar extends RelativeLayout {

    @BindView(R.id.favorite)
    Button mFavorite;
    @BindView(R.id.download)
    DownloadButton mDownload;
    @BindView(R.id.share)
    Button mShare;

    private AppDetailBean mAppDetailBean;

    public AppDetailBottomBar(Context context) {
        this(context, null);
    }

    public AppDetailBottomBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_app_detail_bottom_bar, this);
        ButterKnife.bind(this, this);
    }

    public void bindView(AppDetailBean appDetailBean) {
        mAppDetailBean = appDetailBean;
        mDownload.syncState(appDetailBean);
    }

    @OnClick(R.id.download)
    public void onClick() {
        DownloadManager.getInstance().handleDownloadAction(getContext(), mAppDetailBean);
    }
}
