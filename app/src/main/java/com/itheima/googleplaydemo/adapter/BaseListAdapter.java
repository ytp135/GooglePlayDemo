package com.itheima.googleplaydemo.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * 创建者: Leon
 * 创建时间: 2016/9/24 21:42
 * 描述： TODO
 */
public abstract class BaseListAdapter<T> extends BaseAdapter {

    protected List<T> mDataList;

    protected Context mContext;

    public BaseListAdapter(Context context, List<T> dataList) {
        mDataList = dataList;
        mContext = context;
    }

    @Override
    public int getCount() {
        if (mDataList == null) {
            return 0;
        }
        return mDataList.size();
    }

    @Override
    public Object getItem(int position) {
        return mDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = onCreateViewHolder(position);
            convertView = viewHolder.holdView;
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        onBindViewHolder(viewHolder, position);
        return convertView;
    }

    protected abstract void onBindViewHolder(ViewHolder viewHolder, int position);

    protected abstract ViewHolder onCreateViewHolder(int position);

    public class ViewHolder {
        View holdView;
        public ViewHolder(View v) {
            holdView = v;
        }
    }

    public List<T> getDataList() {
        return mDataList;
    }
}
