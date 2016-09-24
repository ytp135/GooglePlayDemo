package com.itheima.googleplaydemo.ui.fragment;

import android.widget.BaseAdapter;

import com.itheima.googleplaydemo.R;
import com.itheima.googleplaydemo.adapter.AppListAdapter;
import com.itheima.googleplaydemo.bean.AppListItem;

import java.util.List;

/**
 * 创建者: Leon
 * 创建时间: 2016/9/17 9:59
 * 描述： TODO
 */
public abstract class BaseAppListFragment extends BaseLoadMoreFragment{
    private AppListAdapter mAppListAdapter;


    @Override
    protected BaseAdapter onCreateAdapter() {
        mAppListAdapter = new AppListAdapter(getContext(), getAppList());
        return mAppListAdapter;
    }

    @Override
    protected void initListView() {
        super.initListView();
        setListDivider(getResources().getDimensionPixelSize(R.dimen.app_list_divider_height));
    }

    protected abstract List<AppListItem> getAppList();
}
