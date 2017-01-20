package com.itheima.googleplaydemo.adapter;

import android.content.Context;
import android.view.View;

import com.itheima.googleplaydemo.widget.LoadingListItemView;

import java.util.List;

/**
 * 创建者: Leon
 * 创建时间: 2016/9/24 21:56
 * 描述： TODO
 */
public abstract class BaseLoadMoreListAdapter<T> extends BaseListAdapter<T> {

    private static final int ITEM_TYPE_NORMAL = 0;
    private static final int ITEM_TYPE_LOAD_MORE = 1;
    private OnLoadMoreListener mOnLoadMoreListener;

    public BaseLoadMoreListAdapter(Context context, List<T> dataList) {
        super(context, dataList);
    }

    @Override
    public int getCount() {
        if (getDataList() == null) {
            return 0;
        } else {
            return getDataList().size() + 1;
        }
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == getCount() - 1) {
            return ITEM_TYPE_LOAD_MORE;
        } else {
            return ITEM_TYPE_NORMAL;
        }
    }

    @Override
    protected ViewHolder onCreateViewHolder(int position) {
        if (getItemViewType(position) == ITEM_TYPE_LOAD_MORE) {
            return new LoadMoreItemViewHolder(new LoadingListItemView(mContext));
        } else {
            return onCreateNormalItemViewHolder();
        }
    }

    @Override
    protected void onBindViewHolder(ViewHolder viewHolder, T item) {
        if (viewHolder.holdView instanceof LoadingListItemView) {
            if (mOnLoadMoreListener != null) {
                mOnLoadMoreListener.onLoadMore();
            }
        } else {
            onBindNormalViewHolder(viewHolder, item);
        }
    }

    protected abstract void onBindNormalViewHolder(ViewHolder viewHolder, T item);

    protected abstract ViewHolder onCreateNormalItemViewHolder();


    private class LoadMoreItemViewHolder extends ViewHolder {

        public LoadMoreItemViewHolder(View v) {
            super(v);
        }
    }

    public interface OnLoadMoreListener {
        void onLoadMore();
    }

    public void setOnLoadMoreListener(OnLoadMoreListener listener) {
        mOnLoadMoreListener = listener;
    }



}
