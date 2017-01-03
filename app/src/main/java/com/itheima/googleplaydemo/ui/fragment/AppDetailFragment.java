package com.itheima.googleplaydemo.ui.fragment;

import android.text.format.Formatter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.itheima.googleplaydemo.R;
import com.itheima.googleplaydemo.app.Constant;
import com.itheima.googleplaydemo.bean.AppDetailBean;
import com.itheima.googleplaydemo.loader.AppDetailDataLoader;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 创建者: Leon
 * 创建时间: 2016/9/18 12:04
 * 描述： TODO
 */
public class AppDetailFragment extends BaseFragment {

    @BindView(R.id.favorite)
    Button mFavorite;
    @BindView(R.id.download)
    Button mDownload;
    @BindView(R.id.share)
    Button mShare;
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
    @BindView(R.id.app_detail_security_tags)
    LinearLayout mAppDetailSecurityTags;
    @BindView(R.id.app_detail_security_arrow)
    ImageView mAppDetailSecurityArrow;
    @BindView(R.id.app_detail_security_des)
    LinearLayout mAppDetailSecurityDes;

    @Override
    protected void startLoadData() {
        String packageName = getActivity().getIntent().getStringExtra("package_name");
        AppDetailDataLoader.getInstance().loadData(packageName, this);
    }

    @Override
    protected View onCreateContentView() {
        View content = LayoutInflater.from(getContext()).inflate(R.layout.fragment_app_detail, null);
        ButterKnife.bind(this, content);
        initView();
        return content;
    }

    private void initView() {
        updateAppInfo();
    }

    private void updateAppInfo() {
        AppDetailBean data = AppDetailDataLoader.getInstance().getData();
        String iconUrl = Constant.URL_IMAGE + data.getIconUrl();
        Glide.with(getContext()).load(iconUrl).into(mAppIcon);
        mAppName.setText(data.getName());
        mAppRating.setRating(data.getStars());

        String downloadCount = String.format(getString(R.string.download_count), data.getDownloadNum());
        mDownloadCount.setText(downloadCount);

        String versionCode = String.format(getString(R.string.version_code), data.getVersion());
        mVersionCode.setText(versionCode);

        String timestamp = String.format(getString(R.string.time), data.getDate());
        mTime.setText(timestamp);

        String size = String.format(getString(R.string.app_size), Formatter.formatFileSize(getContext(), data.getSize()));
        mAppSize.setText(size);

    }

    @OnClick({R.id.favorite, R.id.download, R.id.share})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.favorite:
                break;
            case R.id.download:
                break;
            case R.id.share:
                break;
        }
    }
}
