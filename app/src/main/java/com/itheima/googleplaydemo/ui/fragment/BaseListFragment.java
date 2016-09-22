package com.itheima.googleplaydemo.ui.fragment;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

/**
 * 创建者: Leon
 * 创建时间: 2016/9/15 13:54
 * 描述： TODO
 */
public abstract class BaseListFragment extends BaseFragment {
    private static final String TAG = "BaseListFragment";

    private ListView mListView;

    @Override
    protected View onCreateContentView() {
        mListView = new ListView(getContext());
        if (getHeaderView() != null) {
            mListView.addHeaderView(getHeaderView());
        }
        mListView.setAdapter(getAdapter());
        mListView.setOnItemClickListener(mOnItemClickListener);
        onCustomListView();
        return mListView;
    }

    private AdapterView.OnItemClickListener mOnItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            onListItemClick(i);
        }
    };

    protected abstract BaseAdapter getAdapter();

    protected View getHeaderView() {
        return null;
    }

    protected void onListItemClick(int i) {};

    protected void onCustomListView(){};

    protected void setListDivider(int height) {
        mListView.setDivider(new ColorDrawable(Color.TRANSPARENT));
        mListView.setDividerHeight(height);
    }
}
