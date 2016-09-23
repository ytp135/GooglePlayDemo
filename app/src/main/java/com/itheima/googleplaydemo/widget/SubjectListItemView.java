package com.itheima.googleplaydemo.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.itheima.googleplaydemo.R;

import butterknife.BindView;

/**
 * 创建者: Leon
 * 创建时间: 2016/9/23 9:31
 * 描述： TODO
 */
public class SubjectListItemView extends RelativeLayout {

    @BindView(R.id.subject_list_item_image)
    ImageView mSubjectListItemImage;
    @BindView(R.id.subject_list_item_title)
    TextView mSubjectListItemTitle;

    public SubjectListItemView(Context context) {
        this(context, null);
    }

    public SubjectListItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.list_subject_item, this);
    }
}
