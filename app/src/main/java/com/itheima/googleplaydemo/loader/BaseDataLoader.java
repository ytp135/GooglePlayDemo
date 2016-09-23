package com.itheima.googleplaydemo.loader;

import com.itheima.googleplaydemo.network.GooglePlayRequest;
import com.itheima.googleplaydemo.network.NetworkListener;

/**
 * 创建者: Leon
 * 创建时间: 2016/9/23 11:07
 * 描述： TODO
 */
public abstract class BaseDataLoader<T> {

    private DataLoaderListener mDataLoaderListener;

    private T mData;

    public void loadData(DataLoaderListener listener) {
        if (mData != null) {
            listener.onLoadSuccess();
            return;
        }
        if (listener == null) {
            return;
        }
        mDataLoaderListener = listener;
        onCreateRequest(mNetworkListener).execute();
    }

    protected abstract GooglePlayRequest<T> onCreateRequest(NetworkListener<T> listener);

    protected NetworkListener<T> mNetworkListener = new NetworkListener<T>() {
        @Override
        public void onResponse(T result) {
            mData = result;
            if (checkIfEmpty()) {
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

    protected abstract boolean checkIfEmpty();

    public T getData() {
        return mData;
    }

}
