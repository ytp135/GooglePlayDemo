package com.itheima.googleplaydemo.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.itheima.googleplaydemo.bean.CategoryBean;
import com.itheima.googleplaydemo.loader.CategoryDataLoader;
import com.itheima.googleplaydemo.widget.CategoryItemView;

import java.util.List;

/**
 * 创建者: Leon
 * 创建时间: 2016/9/25 22:21
 * 描述： TODO
 */
public class CategoryListAdapter extends BaseListAdapter<CategoryBean> {

    private static final int ITEM_TYPE_TITLE = 0;
    private static final int ITEM_TYPE_NORMAL = 1;

    public CategoryListAdapter(Context context, List<CategoryBean> dataList) {
        super(context, dataList);
    }

    @Override
    protected void onBindViewHolder(ViewHolder viewHolder, int position) {
        if (getItemViewType(position) == ITEM_TYPE_TITLE) {
            ((TextView)viewHolder.holdView).setText(CategoryDataLoader.getInstance().getTitle(position));
        }
    }

    @Override
    protected ViewHolder onCreateViewHolder(int position) {
        if (getItemViewType(position) == ITEM_TYPE_TITLE) {
            return new TitleViewHolder(new TextView(mContext));
        } else {
            return new CategoryItemViewHolder(new CategoryItemView(mContext));
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0 || position == 5) {
            return ITEM_TYPE_TITLE;
        } else {
            return ITEM_TYPE_NORMAL;
        }
    }

    @Override
    public int getCount() {
        return CategoryDataLoader.getInstance().getListCount();
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    private class TitleViewHolder extends ViewHolder {

        public TitleViewHolder(View v) {
            super(v);
        }
    }

    private class CategoryItemViewHolder extends ViewHolder {

        public CategoryItemViewHolder(View v) {
            super(v);
        }
    }
}
