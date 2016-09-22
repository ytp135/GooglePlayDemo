package com.itheima.googleplaydemo.loader;

/**
 * 创建者: Leon
 * 创建时间: 2016/9/22 16:45
 * 描述： TODO
 */
public interface ListDataLoaderListener extends DataLoaderListener{

    void onMoreDataLoadSuccess();

    void onMoreDataLoadFailed();
}
