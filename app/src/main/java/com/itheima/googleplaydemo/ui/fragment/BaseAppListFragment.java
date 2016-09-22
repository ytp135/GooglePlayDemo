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
public abstract class BaseAppListFragment extends BaseListFragment {
    private static final String TAG = "BaseAppListFragment";

    @Override
    protected BaseAdapter getAdapter() {
        return new AppListAdapter(getListData(), getContext());
    }

    protected abstract List<AppListItem> getListData();

    @Override
    protected void onCustomListView() {
        setListDivider(getResources().getDimensionPixelSize(R.dimen.app_list_divider_height));
    }
}
