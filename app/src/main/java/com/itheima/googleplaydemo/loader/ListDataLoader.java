package com.itheima.googleplaydemo.loader;

import com.itheima.googleplaydemo.network.GooglePlayRequest;
import com.itheima.googleplaydemo.network.NetworkListener;

/**
 * 创建者: Leon
 * 创建时间: 2016/9/23 11:21
 * 描述： TODO
 */
public abstract class ListDataLoader<T> extends BaseDataLoader<T> {

    private LoadMoreListener mListDataLoaderListener;

    public void loadData(LoadMoreListener listDataLoaderListener) {
        mListDataLoaderListener = listDataLoaderListener;
        super.loadData(listDataLoaderListener);
    }

    public void loadMoreData() {
        onCreateLoadMoreRequest(mLoadMoreListener).execute();
    }

    protected abstract GooglePlayRequest<T> onCreateLoadMoreRequest(NetworkListener<T> listener);

    private NetworkListener<T> mLoadMoreListener = new NetworkListener<T>() {
        @Override
        public void onResponse(T result) {
            onGetMoreData(result);
            mListDataLoaderListener.onMoreDataLoadSuccess();

        }

        @Override
        public void onFailure(String error) {
            mListDataLoaderListener.onMoreDataLoadFailed();
        }
    };

    protected abstract void onGetMoreData(T result);
}
