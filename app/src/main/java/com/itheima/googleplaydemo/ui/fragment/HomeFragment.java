package com.itheima.googleplaydemo.ui.fragment;

import android.view.View;

import com.itheima.googleplaydemo.ui.widget.LoopView;

/**
 * 创建者: Leon
 * 创建时间: 2016/9/15 13:13
 * 描述： TODO
 */
public class HomeFragment extends BaseAppListFragment {
    private static final String TAG = "HomeFragment";

    @Override
    protected void startLoadData() {
        onDataLoadedSuccess();
    }

    @Override
    protected View getHeaderView() {
        return new LoopView(getContext());
    }
}
