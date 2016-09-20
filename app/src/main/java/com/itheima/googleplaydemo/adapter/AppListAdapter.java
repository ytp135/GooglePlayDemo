package com.itheima.googleplaydemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.itheima.googleplaydemo.R;
import com.itheima.googleplaydemo.bean.AppListItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 创建者: Leon
 * 创建时间: 2016/9/20 18:42
 * 描述： TODO
 */
public class AppListAdapter extends BaseAdapter {
    private static final String TAG = "AppListAdapter";

    private List<AppListItem> mDataList;
    private Context mContext;


    public AppListAdapter(List<AppListItem> list, Context context) {
        mDataList = list;
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
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_app_item, null);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        AppListItem item = mDataList.get(position);
        vh.mAppName.setText(item.getName());
        vh.mAppSize.setText(String.valueOf(item.getSize()));
        vh.mAppDes.setText(item.getDes());
        return convertView;
    }

    public class ViewHolder {

        @BindView(R.id.app_name)
        TextView mAppName;
        @BindView(R.id.app_size)
        TextView mAppSize;
        @BindView(R.id.app_des)
        TextView mAppDes;

        public ViewHolder(View root) {
            ButterKnife.bind(this, root);
        }

    }
}
