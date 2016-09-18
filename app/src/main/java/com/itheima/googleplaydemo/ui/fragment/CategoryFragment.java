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
public class CategoryFragment extends BaseListFragment {
    private static final String TAG = "CategoryFragment";

    @Override
    protected void startLoadData() {
        onDataLoadedSuccess();
    }

    @Override
    protected BaseAdapter getAdapter() {
        return mBaseAdapter;
    }

    private BaseAdapter mBaseAdapter = new BaseAdapter() {

        private static final int ITEM_TYPE_TITLE = 0;
        private static final int ITEM_TYPE_NORMAL = 1;

        @Override
        public int getCount() {
            return 12;
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
                if (getItemViewType(i) == ITEM_TYPE_TITLE) {
                    view = LayoutInflater.from(getContext()).inflate(R.layout.list_item_title, null);
                } else {
                    view = LayoutInflater.from(getContext()).inflate(R.layout.list_category_item, null);
                }
            }
            return view;
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
        public int getViewTypeCount() {
            return 2;
        }
    };
}
