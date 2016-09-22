package com.itheima.googleplaydemo.loader;

import com.itheima.googleplaydemo.bean.AppListItem;
import com.itheima.googleplaydemo.bean.HomeBean;
import com.itheima.googleplaydemo.network.HomeRequest;
import com.itheima.googleplaydemo.network.NetworkListener;

import java.util.List;

/**
 * 创建者: Leon
 * 创建时间: 2016/9/22 15:59
 * 描述： TODO
 */
public class HomeDataLoader {
    private static final String TAG = "HomeDataLoader";

    private static HomeDataLoader sDataLoader;

    private HomeBean mHomeBean;

    private ListDataLoaderListener mDataLoaderListener;

    public static HomeDataLoader getInstance() {
        if (sDataLoader == null) {
            synchronized (HomeDataLoader.class) {
                if (sDataLoader == null) {
                    sDataLoader = new HomeDataLoader();
                }
            }
        }
        return sDataLoader;
    }

    public void loadHomeData(ListDataLoaderListener listDataLoaderListener) {
        if (listDataLoaderListener == null) {
            return;
        }
        mDataLoaderListener = listDataLoaderListener;
        if (mHomeBean != null) {
            listDataLoaderListener.onLoadSuccess();
        } else {
            new HomeRequest(0, mHomeBeanNetworkListener).execute();
        }
    }

    private NetworkListener<HomeBean> mHomeBeanNetworkListener = new NetworkListener<HomeBean>() {
        @Override
        public void onResponse(HomeBean result) {
            mHomeBean = result;
            if (mHomeBean.getList().isEmpty()) {
                mDataLoaderListener.onLoadedEmpty();
            } else {
                mDataLoaderListener.onLoadSuccess();
            }
        }

        @Override
        public void onFailure(String error) {
            mDataLoaderListener.onLoadFailed();
        }
    };

    public void loadMoreData() {
        new HomeRequest(mHomeBean.getList().size(), mLoadMoreListener).execute();
    }

    private NetworkListener<HomeBean> mLoadMoreListener = new NetworkListener<HomeBean>() {
        @Override
        public void onResponse(HomeBean result) {
            mHomeBean.getList().addAll(result.getList());
            mDataLoaderListener.onMoreDataLoadSuccess();
        }

        @Override
        public void onFailure(String error) {
            mDataLoaderListener.onMoreDataLoadFailed();
        }
    };

    public List<String> getLooperData() {
        if (mHomeBean != null) {
            return mHomeBean.getPicture();
        }
        return null;
    }

    public List<AppListItem> getListData() {
        if (mHomeBean != null) {
            return mHomeBean.getList();
        }
        return null;
    }
}
