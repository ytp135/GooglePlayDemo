package com.itheima.googleplaydemo.ui.fragment;

import android.view.LayoutInflater;
import android.view.View;

import com.itheima.googleplaydemo.R;
import com.itheima.googleplaydemo.loader.AppDetailDataLoader;

/**
 * 创建者: Leon
 * 创建时间: 2016/9/18 12:04
 * 描述： TODO
 */
public class AppDetailFragment extends BaseFragment {
    private static final String TAG = "AppDetailFragment";

    @Override
    protected void startLoadData() {
        String packageName = getActivity().getIntent().getStringExtra("package_name");
        AppDetailDataLoader.getInstance().loadData(packageName, this);
    }

    @Override
    protected View onCreateContentView() {
        View content = LayoutInflater.from(getContext()).inflate(R.layout.fragment_app_detail, null);
        initView();
        return content;
    }

    private void initView() {
    }

}
