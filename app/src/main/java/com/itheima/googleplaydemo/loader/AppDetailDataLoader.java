package com.itheima.googleplaydemo.loader;

import com.itheima.googleplaydemo.bean.AppDetailBean;
import com.itheima.googleplaydemo.network.AppDetailRequest;
import com.itheima.googleplaydemo.network.GooglePlayRequest;
import com.itheima.googleplaydemo.network.NetworkListener;

/**
 * Created by Leon on 2017/1/3.
 */

public class AppDetailDataLoader extends BaseDataLoader<AppDetailBean> {


    private String mPackageName;

    private static AppDetailDataLoader sAppDetailDataLoader;

    public static AppDetailDataLoader getInstance() {
        if (sAppDetailDataLoader == null) {
            synchronized (AppDetailDataLoader.class) {
                if (sAppDetailDataLoader == null) {
                    sAppDetailDataLoader = new AppDetailDataLoader();
                }
            }
        }
        return sAppDetailDataLoader;
    }

    public void loadData(String packageName, DataLoaderListener loaderListener) {
        mPackageName = packageName;
        super.loadData(loaderListener);
    }

    @Override
    protected GooglePlayRequest<AppDetailBean> onCreateRequest(NetworkListener<AppDetailBean> listener) {
        return new AppDetailRequest(mPackageName, listener);
    }

    @Override
    protected boolean checkIfEmpty() {
        return false;
    }

    @Override
    protected boolean useCache() {
        return false;
    }
}