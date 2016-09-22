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
public abstract class BaseAppListFragment extends BaseListFragment implements AppListAdapter.OnLoadMoreListener{
    private static final String TAG = "BaseAppListFragment";
    private AppListAdapter mAppListAdapter;


    @Override
    protected BaseAdapter onCreateAdapter() {
        mAppListAdapter = new AppListAdapter(getListData(), getContext());
        mAppListAdapter.setOnLoadMoreListener(this);
        return mAppListAdapter;
    }

    protected abstract List<AppListItem> getListData();

    @Override
    protected void onCustomListView() {
        setListDivider(getResources().getDimensionPixelSize(R.dimen.app_list_divider_height));
    }

    @Override
    public void onLoadMore() {
    }
}
