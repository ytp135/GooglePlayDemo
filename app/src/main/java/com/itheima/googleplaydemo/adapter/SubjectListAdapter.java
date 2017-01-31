package com.itheima.googleplaydemo.adapter;

import android.content.Context;

import com.itheima.googleplaydemo.bean.SubjectBean;
import com.itheima.googleplaydemo.widget.SubjectListItemView;

import java.util.List;

/**
 * 创建者: Leon
 * 创建时间: 2016/9/23 9:45
 * 描述： TODO
 */
public class SubjectListAdapter extends BaseLoadMoreListAdapter<SubjectBean> {


    public SubjectListAdapter(Context context, List<SubjectBean> dataList) {
        super(context, dataList);
    }

    @Override
    protected void onBindNormalViewHolder(ViewHolder viewHolder, int position) {
        ((SubjectListItemView)viewHolder.holdView).bindView(getDataList().get(position));
    }

    @Override
    protected ViewHolder onCreateNormalItemViewHolder() {
        return new ViewHolder(new SubjectListItemView(getContext()));
    }

}
