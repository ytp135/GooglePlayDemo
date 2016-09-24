package com.itheima.googleplaydemo.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.itheima.googleplaydemo.bean.SubjectBean;
import com.itheima.googleplaydemo.widget.LoadingListItemView;
import com.itheima.googleplaydemo.widget.SubjectListItemView;

import java.util.List;

/**
 * 创建者: Leon
 * 创建时间: 2016/9/23 9:45
 * 描述： TODO
 */
public class SubjectListAdapter extends BaseAdapter{

    private static final int ITEM_TYPE_NORMAL = 0;
    private static final int ITEM_TYPE_LOADING = 1;

    private Context mContext;
    private List<SubjectBean> mDataList;

    public SubjectListAdapter(List<SubjectBean> dataList, Context context) {
        mContext = context;
        mDataList = dataList;
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
            SubjectListItemViewHolder viewHolder = null;
            if (convertView == null) {
                convertView = new SubjectListItemView(mContext);
                viewHolder = new SubjectListItemViewHolder((SubjectListItemView) convertView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (SubjectListItemViewHolder) convertView.getTag();
            }
            viewHolder.mSubjectListItemView.bindView(mDataList.get(position));
        } else {
            ListLoadingItemViewHolder vh = null;
            if (convertView == null) {
                convertView = new LoadingListItemView(mContext);
                vh = new ListLoadingItemViewHolder((LoadingListItemView) convertView);
                convertView.setTag(vh);
            } else {
                vh = (ListLoadingItemViewHolder) convertView.getTag();
            }
        }
        return convertView;
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

    private class SubjectListItemViewHolder {
        SubjectListItemView mSubjectListItemView;

        public SubjectListItemViewHolder(SubjectListItemView subjectListItemView) {
            mSubjectListItemView = subjectListItemView;
        }
    }

    public class ListLoadingItemViewHolder {

        private LoadingListItemView mListLoadingItemView;

        public ListLoadingItemViewHolder(LoadingListItemView root) {
            mListLoadingItemView = root;
        }
    }
}
