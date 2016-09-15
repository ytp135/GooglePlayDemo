package com.itheima.googleplaydemo.ui.fragment;

import android.view.View;
import android.widget.TextView;

/**
 * 创建者: Leon
 * 创建时间: 2016/9/15 13:13
 * 描述： TODO
 */
public class GameFragment extends BaseFragment {
    private static final String TAG = "GameFragment";

    @Override
    protected void startLoadData() {
        onDataLoadedSuccess();
    }

    @Override
    protected View getContentView() {
        TextView textView = new TextView(getContext());
        textView.setText(TAG);
        return textView;
    }
}
