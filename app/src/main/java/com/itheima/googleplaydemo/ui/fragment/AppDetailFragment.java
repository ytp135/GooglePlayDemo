package com.itheima.googleplaydemo.ui.fragment;

import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.itheima.googleplaydemo.R;
import com.itheima.googleplaydemo.bean.AppDetailBean;
import com.itheima.googleplaydemo.network.DownloadManager;
import com.itheima.googleplaydemo.network.HeiMaRetrofit;
import com.itheima.googleplaydemo.widget.AppDetailBottomBar;
import com.itheima.googleplaydemo.widget.AppDetailDesView;
import com.itheima.googleplaydemo.widget.AppDetailGalleryView;
import com.itheima.googleplaydemo.widget.AppDetailInfoView;
import com.itheima.googleplaydemo.widget.AppDetailSecurityView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 创建者: Leon
 * 创建时间: 2016/9/18 12:04
 * 描述： TODO
 */
public class AppDetailFragment extends BaseFragment{

    private String mPackageName;

    private AppDetailBean mAppDetailBean;

    @Override
    protected void startLoadData() {
        mPackageName = getActivity().getIntent().getStringExtra("package_name");
        Call<AppDetailBean> appDetailBeanCall = HeiMaRetrofit.getInstance().getApi().appDetail(mPackageName);
        appDetailBeanCall.enqueue(new Callback<AppDetailBean>() {
            @Override
            public void onResponse(Call<AppDetailBean> call, Response<AppDetailBean> response) {
                mAppDetailBean = response.body();
                onDataLoadedSuccess();
            }

            @Override
            public void onFailure(Call<AppDetailBean> call, Throwable t) {
                onDataLoadedError();
            }
        });
    }

    @Override
    protected View onCreateContentView() {
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        ScrollView scrollView = new ScrollView(getContext());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1);
        scrollView.setLayoutParams(layoutParams);


        LinearLayout linearLayoutInScrollView = new LinearLayout(getContext());
        linearLayoutInScrollView.setOrientation(LinearLayout.VERTICAL);

        LinearLayout.LayoutParams contentParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        contentParams.topMargin = getResources().getDimensionPixelSize(R.dimen.padding);

        //应用信息
        AppDetailInfoView appDetailInfoView = new AppDetailInfoView(getContext());
        appDetailInfoView.bindView(mAppDetailBean);

        //应用安全
        AppDetailSecurityView appDetailSecurityView = new AppDetailSecurityView(getContext());
        appDetailSecurityView.setLayoutParams(contentParams);
        appDetailSecurityView.bindView(mAppDetailBean);

        //应用截图
        AppDetailGalleryView appDetailGalleryView = new AppDetailGalleryView(getContext());
        appDetailGalleryView.setLayoutParams(contentParams);
        appDetailGalleryView.bindView(mAppDetailBean);

        //应用描述
        AppDetailDesView appDetailDesView = new AppDetailDesView(getContext());
        appDetailDesView.setLayoutParams(contentParams);
        appDetailDesView.bindView(mAppDetailBean);

        linearLayoutInScrollView.addView(appDetailInfoView);
        linearLayoutInScrollView.addView(appDetailSecurityView);
        linearLayoutInScrollView.addView(appDetailGalleryView);
        linearLayoutInScrollView.addView(appDetailDesView);

        scrollView.addView(linearLayoutInScrollView);
        linearLayout.addView(scrollView);

        //底部bar
        AppDetailBottomBar appDetailBottomBar = new AppDetailBottomBar(getContext());
        appDetailBottomBar.bindView(mAppDetailBean);
        linearLayout.addView(appDetailBottomBar);
        return linearLayout;
    }


    @Override
    public void onPause() {
        super.onPause();
        DownloadManager.getInstance().removeObserver(mPackageName);
    }
}