package com.itheima.googleplaydemo.network;

/**
 * 创建者: Leon
 * 创建时间: 2016/9/20 18:11
 * 描述： TODO
 */
public interface NetworkListener<T> {

    void onResponse(T result);

    void onFailure(String error);
}
