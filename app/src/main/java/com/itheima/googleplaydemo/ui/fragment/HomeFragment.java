package com.itheima.googleplaydemo.ui.fragment;

import android.content.Intent;
import android.view.View;

import com.itheima.googleplaydemo.ui.activity.AppDetailActivity;
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

    @Override
    protected void onListItemClick(int i) {
        Intent intent = new Intent(getContext(), AppDetailActivity.class);
        startActivity(intent);
    }
}
