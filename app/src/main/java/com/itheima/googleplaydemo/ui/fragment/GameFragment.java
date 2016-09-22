package com.itheima.googleplaydemo.ui.fragment;

import com.itheima.googleplaydemo.bean.AppListItem;
import com.itheima.googleplaydemo.loader.GameDataLoader;

import java.util.List;

/**
 * 创建者: Leon
 * 创建时间: 2016/9/15 13:13
 * 描述： TODO
 */
public class GameFragment extends BaseAppListFragment {

    @Override
    protected void startLoadData() {
        GameDataLoader.getInstance().loadGameData(this);
    }

    @Override
    public void onMoreDataLoadSuccess() {
        notifyDataSetChange();
    }

    @Override
    public void onLoadMore() {
        GameDataLoader.getInstance().loadMoreData();
    }

    @Override
    public void onMoreDataLoadFailed() {

    }

    @Override
    public void onLoadSuccess() {
        onDataLoadedSuccess();
    }

    @Override
    public void onLoadFailed() {
        onDataLoadedError();
    }

    @Override
    public void onLoadedEmpty() {
        onDataLoadedEmpty();
    }

    @Override
    protected List<AppListItem> getAppList() {
        return GameDataLoader.getInstance().getListData();
    }
}
