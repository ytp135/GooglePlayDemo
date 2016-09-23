package com.itheima.googleplaydemo.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.itheima.googleplaydemo.bean.SubjectBean;
import com.itheima.googleplaydemo.widget.SubjectListItemView;

import java.util.List;

/**
 * 创建者: Leon
 * 创建时间: 2016/9/23 9:45
 * 描述： TODO
 */
public class SubjectListAdapter extends BaseAdapter{

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
        return mDataList.size();
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
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = new SubjectListItemView(mContext);
            viewHolder = new ViewHolder((SubjectListItemView) convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.mSubjectListItemView.bindView(mDataList.get(position));
        return convertView;
    }

    private class ViewHolder {
        SubjectListItemView mSubjectListItemView;

        public ViewHolder(SubjectListItemView subjectListItemView) {
            mSubjectListItemView = subjectListItemView;
        }
    }
}
