package com.itheima.googleplaydemo.network;

/**
 * 创建者: Leon
 * 创建时间: 2016/9/21 17:04
 * 描述： TODO
 */
public class GooglePlayResponse<T> {

    private T mResponse;

    public GooglePlayResponse(T response) {
        mResponse = response;
    }

    public T getResponse() {
        return mResponse;
    }

    public void setResponse(T response) {
        mResponse = response;
    }
}
