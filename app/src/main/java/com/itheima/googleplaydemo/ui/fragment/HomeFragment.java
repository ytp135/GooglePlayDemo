package com.itheima.googleplaydemo.ui.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.itheima.googleplaydemo.R;
import com.itheima.googleplaydemo.ui.widget.LoopView;

/**
 * 创建者: Leon
 * 创建时间: 2016/9/15 13:13
 * 描述： TODO
 */
public class HomeFragment extends BaseListFragment {
    private static final String TAG = "HomeFragment";


    @Override
    protected BaseAdapter getAdapter() {
        return mBaseAdapter;
    }

    @Override
    protected void startLoadData() {
        onDataLoadedSuccess();
    }

    private BaseAdapter mBaseAdapter = new BaseAdapter() {
        @Override
        public int getCount() {
            return 30;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = LayoutInflater.from(getContext()).inflate(R.layout.list_app_item, null);
            }
            return view;
        }
    };

    @Override
    protected View getHeaderView() {
        return new LoopView(getContext());
    }
}
