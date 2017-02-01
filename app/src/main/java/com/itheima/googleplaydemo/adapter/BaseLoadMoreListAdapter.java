package com.itheima.googleplaydemo.adapter;

import android.content.Context;

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

    public BaseLoadMoreListAdapter(Context context, List<T> dataList) {
        super(context, dataList);
    }

    /**
     *  返回条目个数，由于多了一个进度条的条目，所以多加一个1。
     */
    @Override
    public int getCount() {
        if (getDataList() == null) {
            return 0;
        } else {
            return getDataList().size() + 1;
        }
    }

    /**
     *  返回条目的类型个数，这里有两种类型的条目，一种是正常的item, 一种是进度条条目
     */
    @Override
    public int getViewTypeCount() {
        return 2;
    }

    /**
     * 返回对应position位置的item的类型，最后一个位置为进度条类型，其他为正常item类型
     */
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
            return new ViewHolder(new LoadingListItemView(getContext()));
        } else {
            return onCreateNormalItemViewHolder();
        }
    }

    @Override
    protected void onBindViewHolder(ViewHolder viewHolder, int position) {
        if (ITEM_TYPE_NORMAL == getItemViewType(position)) {
            onBindNormalViewHolder(viewHolder, position);
        }
    }

    /**
     *  创建普通的item的ViewHolder
     */
    protected abstract ViewHolder onCreateNormalItemViewHolder();

    /**
     * 绑定普通的ViewHolder
     */
    protected abstract void onBindNormalViewHolder(ViewHolder viewHolder, int position);

}
