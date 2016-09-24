package com.itheima.googleplaydemo.ui.fragment;

import android.widget.BaseAdapter;

import com.itheima.googleplaydemo.adapter.SubjectListAdapter;
import com.itheima.googleplaydemo.loader.SubjectDataLoader;

/**
 * 创建者: Leon
 * 创建时间: 2016/9/15 13:13
 * 描述： TODO
 */
public class SubjectFragment extends BaseLoadMoreListFragment {

    @Override
    protected void startLoadData() {
        SubjectDataLoader.getInstance().loadData(this);
    }

    @Override
    protected BaseAdapter onCreateAdapter() {
        return new SubjectListAdapter(getContext(), SubjectDataLoader.getInstance().getData());
    }

    @Override
    public void onLoadMore() {
        SubjectDataLoader.getInstance().loadMoreData();
    }
}
