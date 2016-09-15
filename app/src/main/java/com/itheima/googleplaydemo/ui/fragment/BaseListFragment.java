package com.itheima.googleplaydemo.ui.fragment;

import android.view.View;
import android.widget.ListView;

/**
 * 创建者: Leon
 * 创建时间: 2016/9/15 13:54
 * 描述： TODO
 */
public class BaseListFragment extends BaseFragment {
    private static final String TAG = "BaseListFragment";

    private ListView mListView;

    @Override
    protected void startLoadData() {
        onDataLoadedSuccess();
    }

    @Override
    protected View onCreateContentView() {
        mListView = new ListView(getContext());
        return mListView;
    }
}
