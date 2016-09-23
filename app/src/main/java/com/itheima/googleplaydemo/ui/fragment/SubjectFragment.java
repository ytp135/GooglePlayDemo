package com.itheima.googleplaydemo.ui.fragment;

import android.widget.BaseAdapter;

import com.itheima.googleplaydemo.adapter.SubjectListAdapter;

/**
 * 创建者: Leon
 * 创建时间: 2016/9/15 13:13
 * 描述： TODO
 */
public class SubjectFragment extends BaseListFragment {
    private static final String TAG = "SubjectFragment";

    @Override
    protected void startLoadData() {
        onDataLoadedSuccess();
    }

    @Override
    protected BaseAdapter onCreateAdapter() {
        return new SubjectListAdapter(getContext());
    }

}
