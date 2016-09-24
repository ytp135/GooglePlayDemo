package com.itheima.googleplaydemo.ui.fragment;

import com.itheima.googleplaydemo.bean.AppListItem;
import com.itheima.googleplaydemo.loader.AppDataLoader;

import java.util.List;

/**
 * 创建者: Leon
 * 创建时间: 2016/9/15 13:13
 * 描述： TODO
 */
public class AppFragment extends BaseAppListFragment {
    private static final String TAG = "AppFragment";

    @Override
    protected void startLoadData() {
        AppDataLoader.getInstance().loadData(this);
    }

    @Override
    public void onLoadMore() {
        AppDataLoader.getInstance().loadMoreData();
    }

    @Override
    protected List<AppListItem> getAppList() {
        return AppDataLoader.getInstance().getData();
    }

}
