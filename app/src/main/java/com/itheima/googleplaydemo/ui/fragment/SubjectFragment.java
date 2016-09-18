package com.itheima.googleplaydemo.ui.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.itheima.googleplaydemo.R;

/**
 * 创建者: Leon
 * 创建时间: 2016/9/15 13:13
 * 描述： TODO
 */
public class SubjectFragment extends BaseListFragment {
    private static final String TAG = "SubjectFragment";

    @Override
    protected void startLoadData() {
        onDataLoadedSuccess();
    }

    @Override
    protected BaseAdapter getAdapter() {
        return mBaseAdapter;
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
                view = LayoutInflater.from(getContext()).inflate(R.layout.list_subject_item, null);
            }
            return view;
        }
    };
}
