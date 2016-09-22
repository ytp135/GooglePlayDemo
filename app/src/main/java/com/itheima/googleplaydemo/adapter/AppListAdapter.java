package com.itheima.googleplaydemo.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.itheima.googleplaydemo.bean.AppListItem;
import com.itheima.googleplaydemo.widget.AppListItemView;
import com.itheima.googleplaydemo.widget.LoadingListItemView;

import java.util.List;

/**
 * 创建者: Leon
 * 创建时间: 2016/9/20 18:42
 * 描述： TODO
 */
public class AppListAdapter extends BaseAdapter {

    private static final String TAG = "AppListAdapter";
    private List<AppListItem> mDataList;
    private Context mContext;

    private static final int ITEM_TYPE_NORMAL = 0;
    private static final int ITEM_TYPE_LOADING = 1;


    public AppListAdapter(List<AppListItem> list, Context context) {
        mDataList = list;
        mContext = context;
    }

    @Override
    public int getCount() {
        if (mDataList == null) {
            return 0;
        }
        return mDataList.size() + 1;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (getItemViewType(position) == ITEM_TYPE_NORMAL) {
            ListItemViewHolder vh = null;
            if (convertView == null) {
                convertView = new AppListItemView(mContext);
                vh = new ListItemViewHolder((AppListItemView) convertView);
                convertView.setTag(vh);
            } else {
                vh = (ListItemViewHolder) convertView.getTag();
            }
           vh.mAppListItemView.bindView(mDataList.get(position));
        } else {
            ListLoadingItemViewHolder viewHolder = null;
            if (convertView == null) {
                convertView = new LoadingListItemView(mContext);
                viewHolder = new ListLoadingItemViewHolder((LoadingListItemView) convertView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ListLoadingItemViewHolder) convertView.getTag();
            }
        }

        return convertView;
    }

    public class ListItemViewHolder {

        private AppListItemView mAppListItemView;

        public ListItemViewHolder(AppListItemView root) {
            mAppListItemView = root;
        }
    }

    public class ListLoadingItemViewHolder {

        private LoadingListItemView mListLoadingItemView;

        public ListLoadingItemViewHolder(LoadingListItemView root) {
            mListLoadingItemView = root;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == getCount() - 1) {
            return ITEM_TYPE_LOADING;
        } else {
            return ITEM_TYPE_NORMAL;
        }
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }
}
