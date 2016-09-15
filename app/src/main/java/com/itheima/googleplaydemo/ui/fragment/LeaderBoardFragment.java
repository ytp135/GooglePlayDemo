package com.itheima.googleplaydemo.ui.fragment;

import android.view.View;
import android.widget.TextView;

/**
 * 创建者: Leon
 * 创建时间: 2016/9/15 13:13
 * 描述： TODO
 */
public class LeaderBoardFragment extends BaseFragment {
    private static final String TAG = "LeaderBoardFragment";

    @Override
    protected void startLoadData() {
        onDataLoadedSuccess();
    }

    @Override
    protected View onCreateContentView() {
        TextView textView = new TextView(getContext());
        textView.setText(TAG);
        return textView;
    }
}
