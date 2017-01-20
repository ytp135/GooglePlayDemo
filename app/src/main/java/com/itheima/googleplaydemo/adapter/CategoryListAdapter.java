package com.itheima.googleplaydemo.adapter;

import android.content.Context;

import com.itheima.googleplaydemo.bean.CategoryBean;
import com.itheima.googleplaydemo.widget.CategoryItemView;

import java.util.List;

/**
 * 创建者: Leon
 * 创建时间: 2016/9/25 22:21
 * 描述： TODO
 */
public class CategoryListAdapter extends BaseListAdapter<CategoryBean> {


    public CategoryListAdapter(Context context, List<CategoryBean> dataList) {
        super(context, dataList);
    }

    @Override
    protected void onBindViewHolder(ViewHolder viewHolder, CategoryBean item) {
        ((CategoryItemView)viewHolder.holdView).bindView(item);
    }

    @Override
    protected ViewHolder onCreateViewHolder(int position) {
        return new ViewHolder(new CategoryItemView(getContext()));
    }

}
