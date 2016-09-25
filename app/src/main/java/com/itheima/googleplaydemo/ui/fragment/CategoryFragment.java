package com.itheima.googleplaydemo.ui.fragment;

import android.widget.BaseAdapter;

import com.itheima.googleplaydemo.adapter.CategoryListAdapter;
import com.itheima.googleplaydemo.loader.CategoryDataLoader;

/**
 * 创建者: Leon
 * 创建时间: 2016/9/15 13:13
 * 描述： TODO
 */
public class CategoryFragment extends BaseListFragment {
    private static final String TAG = "CategoryFragment";

    @Override
    protected void startLoadData() {
        CategoryDataLoader.getInstance().loadData(this);
    }

    @Override
    protected BaseAdapter onCreateAdapter() {
        return new CategoryListAdapter(getContext(), CategoryDataLoader.getInstance().getData());
    }

}
