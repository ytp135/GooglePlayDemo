package com.itheima.googleplaydemo.loader;

/**
 * 创建者: Leon
 * 创建时间: 2016/9/22 16:32
 * 描述： TODO
 */
public interface DataLoaderListener {

    void onLoadSuccess();

    void onLoadFailed();

    void onLoadedEmpty();
}
