package com.itheima.googleplaydemo.loader;

import com.itheima.googleplaydemo.bean.AppListItem;
import com.itheima.googleplaydemo.bean.HomeBean;
import com.itheima.googleplaydemo.network.GooglePlayRequest;
import com.itheima.googleplaydemo.network.HomeRequest;
import com.itheima.googleplaydemo.network.NetworkListener;

import java.util.List;

/**
 * 创建者: Leon
 * 创建时间: 2016/9/22 15:59
 * 描述： TODO
 */
public class HomeDataLoader extends ListDataLoader<HomeBean>{
    private static final String TAG = "HomeDataLoader";

    private static HomeDataLoader sDataLoader;

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

    @Override
    protected GooglePlayRequest<HomeBean> onCreateLoadMoreRequest(NetworkListener<HomeBean> listener) {
        return new HomeRequest(getListData().size(), listener);
    }

    @Override
    protected void onGetMoreData(HomeBean result) {
        getData().getList().addAll(result.getList());
    }


    public List<String> getLooperData() {
        if (getData() != null) {
            return getData().getPicture();
        }
        return null;
    }

    public List<AppListItem> getListData() {
        if (getData() != null) {
            return getData().getList();
        }
        return null;
    }

    @Override
    protected GooglePlayRequest<HomeBean> onCreateRequest(NetworkListener<HomeBean> listener) {
        return new HomeRequest(0, listener);
    }

    @Override
    protected boolean checkIfEmpty() {
        return getData().getList().size() == 0;
    }
}
