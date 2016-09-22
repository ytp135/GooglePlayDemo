package com.itheima.googleplaydemo.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.itheima.googleplaydemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 创建者: Leon
 * 创建时间: 2016/9/22 10:44
 * 描述： TODO
 */
public class LoadingListItemView extends RelativeLayout {
    private static final String TAG = "ListLoadingItemView";

    @BindView(R.id.list_loading_item_tv)
    TextView mListLoadingItemTv;

    public LoadingListItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LoadingListItemView(Context context) {
        this(context, null);
    }

    private void init() {
        View root = LayoutInflater.from(getContext()).inflate(R.layout.list_loading_item, this);
        ButterKnife.bind(this, root);
    }
}
