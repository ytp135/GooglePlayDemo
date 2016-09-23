package com.itheima.googleplaydemo.loader;

import com.itheima.googleplaydemo.bean.AppListItem;
import com.itheima.googleplaydemo.network.AppRequest;
import com.itheima.googleplaydemo.network.GooglePlayRequest;
import com.itheima.googleplaydemo.network.NetworkListener;

import java.util.List;

/**
 * 创建者: Leon
 * 创建时间: 2016/9/22 20:28
 * 描述： TODO
 */
public class AppDataLoader extends ListDataLoader<List<AppListItem>>{

    private static AppDataLoader sAppDataLoader;

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


    @Override
    protected GooglePlayRequest<List<AppListItem>> onCreateLoadMoreRequest(NetworkListener<List<AppListItem>> listener) {
        return new AppRequest(getData().size(), listener);
    }

    @Override
    protected void onGetMoreData(List<AppListItem> result) {
        getData().addAll(result);
    }


    @Override
    protected GooglePlayRequest<List<AppListItem>> onCreateRequest(NetworkListener<List<AppListItem>> listener) {
        return new AppRequest(0, listener);
    }

    @Override
    protected boolean checkIfEmpty() {
        return getData().size() == 0;
    }
}
