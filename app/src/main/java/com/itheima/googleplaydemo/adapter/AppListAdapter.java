package com.itheima.googleplaydemo.adapter;

import android.content.Context;

import com.itheima.googleplaydemo.bean.AppListItem;
import com.itheima.googleplaydemo.widget.AppListItemView;

import java.util.List;

/**
 * 创建者: Leon
 * 创建时间: 2016/9/20 18:42
 * 描述： TODO
 */
public class AppListAdapter extends BaseLoadMoreListAdapter<AppListItem> {


    public AppListAdapter(Context context, List<AppListItem> dataList) {
        super(context, dataList);
    }

    @Override
    protected void onBindNormalViewHolder(ViewHolder viewHolder, int position) {
        ((AppListItemView)(viewHolder.holdView)).bindView(getDataList().get(position));
    }

    @Override
    protected ViewHolder onCreateNormalViewHolder() {
        return new ViewHolder(new AppListItemView(getContext()));
    }

}
