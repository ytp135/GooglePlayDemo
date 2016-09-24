package com.itheima.googleplaydemo.loader;

/**
 * 创建者: Leon
 * 创建时间: 2016/9/22 20:27
 * 描述： TODO
 */
public interface LoadMoreListener extends DataLoaderListener{

    void onMoreDataLoadSuccess();

    void onMoreDataLoadFailed();
}
