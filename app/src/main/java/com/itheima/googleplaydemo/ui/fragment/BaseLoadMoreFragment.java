package com.itheima.googleplaydemo.ui.fragment;

import com.itheima.googleplaydemo.adapter.BaseLoadMoreListAdapter;
import com.itheima.googleplaydemo.loader.LoadMoreListener;

/**
 * 创建者: Leon
 * 创建时间: 2016/9/24 22:46
 * 描述： TODO
 */
public abstract class BaseLoadMoreFragment extends BaseListFragment
        implements BaseLoadMoreListAdapter.OnLoadMoreListener, LoadMoreListener{


    @Override
    protected void initListView() {
        super.initListView();
        ((BaseLoadMoreListAdapter)getAdapter()).setOnLoadMoreListener(this);
    }

    @Override
    public void onMoreDataLoadFailed() {

    }

    @Override
    public void onMoreDataLoadSuccess() {
        notifyDataSetChange();
    }
}
