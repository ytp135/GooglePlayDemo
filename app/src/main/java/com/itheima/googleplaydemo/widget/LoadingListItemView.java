package com.itheima.googleplaydemo.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import com.itheima.googleplaydemo.R;

/**
 * 创建者: Leon
 * 创建时间: 2016/9/22 10:44
 * 描述： TODO
 */
public class LoadingListItemView extends RelativeLayout {

    public LoadingListItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LoadingListItemView(Context context) {
        this(context, null);
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.list_loading_item, this);
    }
}
