package com.itheima.googleplaydemo.loader;

import com.itheima.googleplaydemo.network.GooglePlayRequest;
import com.itheima.googleplaydemo.network.NetworkListener;
import com.itheima.googleplaydemo.network.RecommendRequest;

import java.util.List;

/**
 * 创建者: Leon
 * 创建时间: 2016/9/25 21:52
 * 描述： TODO
 */
public class RecommendDataLoader extends BaseDataLoader<List<String>>{

    private static RecommendDataLoader sRecommendDataLoader;

    public static RecommendDataLoader getInstance() {
        if (sRecommendDataLoader == null) {
            synchronized (RecommendDataLoader.class) {
                if (sRecommendDataLoader == null) {
                    sRecommendDataLoader = new RecommendDataLoader();
                }
            }
        }
        return sRecommendDataLoader;
    }


    @Override
    protected GooglePlayRequest onCreateRequest(NetworkListener listener) {
        return new RecommendRequest(listener);
    }

    @Override
    protected boolean checkIfEmpty() {
        return getData().size() == 0;
    }
}
