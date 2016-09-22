package com.itheima.googleplaydemo.loader;

import com.itheima.googleplaydemo.bean.AppListItem;
import com.itheima.googleplaydemo.network.AppRequest;
import com.itheima.googleplaydemo.network.NetworkListener;

import java.util.List;

/**
 * 创建者: Leon
 * 创建时间: 2016/9/22 20:28
 * 描述： TODO
 */
public class AppDataLoader {

    private static AppDataLoader sAppDataLoader;

    private ListDataLoaderListener mListDataLoaderListener;

    private List<AppListItem> mAppListItems;

    public static AppDataLoader getInstance() {
        if (sAppDataLoader == null) {
            synchronized (AppDataLoader.class) {
                if (sAppDataLoader == null) {
                    sAppDataLoader = new AppDataLoader();
                }
            }
        }
        return sAppDataLoader;
    }
    public void loadAppData(ListDataLoaderListener listDataLoaderListener) {
        if (listDataLoaderListener == null) {
            return;
        }
        mListDataLoaderListener = listDataLoaderListener;

        if (mAppListItems != null) {
            mListDataLoaderListener.onLoadSuccess();
        } else {
            new AppRequest(0, mListNetworkListener).execute();
        }
    }


    private NetworkListener<List<AppListItem>> mListNetworkListener = new NetworkListener<List<AppListItem>>() {

        @Override
        public void onResponse(List<AppListItem> result) {
            mAppListItems = result;
            mListDataLoaderListener.onLoadSuccess();
        }

        @Override
        public void onFailure(String error) {
            mListDataLoaderListener.onLoadFailed();
        }
    };

    public void loadMoreData() {
        new AppRequest(mAppListItems.size(), mLoadMoreListener).execute();
    }

    private NetworkListener<List<AppListItem>> mLoadMoreListener = new NetworkListener<List<AppListItem>>() {
        @Override
        public void onResponse(List<AppListItem> result) {
            mAppListItems.addAll(result);
            mListDataLoaderListener.onMoreDataLoadSuccess();
        }

        @Override
        public void onFailure(String error) {
            mListDataLoaderListener.onMoreDataLoadFailed();
        }
    };

    public List<AppListItem> getListData() {
        return mAppListItems;
    }
}
