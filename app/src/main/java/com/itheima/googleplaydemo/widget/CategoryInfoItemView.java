package com.itheima.googleplaydemo.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.itheima.googleplaydemo.R;
import com.itheima.googleplaydemo.app.Constant;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Leon on 2017/1/20.
 */

public class CategoryInfoItemView extends LinearLayout {

    @BindView(R.id.icon)
    ImageView mIcon;
    @BindView(R.id.title)
    TextView mTitle;

    public CategoryInfoItemView(Context context) {
        this(context, null);
    }

    public CategoryInfoItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_category_info, this);
        ButterKnife.bind(this, this);
    }

    public void setTitle(String title) {
        mTitle.setText(title);
    }

    public void setIconUrl(String iconUrl) {
        String url = Constant.URL_IMAGE + iconUrl;
        Glide.with(getContext()).load(url).placeholder(R.drawable.ic_default).into(mIcon);
    }
}
