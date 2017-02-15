package com.itheima.googleplaydemo.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.itheima.googleplaydemo.R;
import com.itheima.googleplaydemo.bean.CategoryBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 创建者: Leon
 * 创建时间: 2016/9/25 22:28
 * 描述： TODO
 */
public class CategoryItemView extends RelativeLayout {

    private static final String TAG = "CategoryItemView";

    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.table_layout)
    TableLayout mTableLayout;

    public CategoryItemView(Context context) {
        this(context, null);
    }

    public CategoryItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.list_category_item, this);
        ButterKnife.bind(this, this);
    }


    public void bindView(CategoryBean item) {
        mTitle.setText(item.getTitle());
        mTableLayout.removeAllViews();
        int widthPixels = getResources().getDisplayMetrics().widthPixels - mTableLayout.getPaddingLeft() - mTableLayout.getPaddingRight() ;
        int itemWidth = widthPixels / 3;
        TableRow.LayoutParams layoutParams = new TableRow.LayoutParams();
        layoutParams.width = itemWidth;//每个子条目的宽度
        List<CategoryBean.InfosBean> infos = item.getInfos();
        for (int i = 0; i < infos.size(); i++) {
            CategoryBean.InfosBean infosBean = infos.get(i);
            TableRow tableRow = new TableRow(getContext());
            //添加一行中的第一个item
            addFirstChildItem(tableRow, layoutParams, infosBean);
            //添加一行中的第二个item
            addSecondChildItem(tableRow, layoutParams, infosBean);
            //添加一行中的第三个item
           addThirdChildItem(tableRow, layoutParams, infosBean);
            //添加一行
            mTableLayout.addView(tableRow);
        }
    }

    private void addFirstChildItem(TableRow tableRow, TableRow.LayoutParams layoutParams, CategoryBean.InfosBean infosBean) {
        CategoryInfoItemView infoItemView1 = new CategoryInfoItemView(getContext());
        infoItemView1.setLayoutParams(layoutParams);
        infoItemView1.bindView(infosBean.getName1(), infosBean.getUrl1());
        tableRow.addView(infoItemView1);
    }

    private void addSecondChildItem(TableRow tableRow, TableRow.LayoutParams layoutParams, CategoryBean.InfosBean infosBean) {
        CategoryInfoItemView infoItemView2 = new CategoryInfoItemView(getContext());
        infoItemView2.setLayoutParams(layoutParams);
        infoItemView2.bindView(infosBean.getName2(), infosBean.getUrl2());
        tableRow.addView(infoItemView2);
    }

    private void addThirdChildItem(TableRow tableRow, TableRow.LayoutParams layoutParams, CategoryBean.InfosBean infosBean) {
        String name3 = infosBean.getName3();
        if ( name3 != null && name3.length() > 0) {
            CategoryInfoItemView infoItemView3 = new CategoryInfoItemView(getContext());
            infoItemView3.setLayoutParams(layoutParams);
            infoItemView3.bindView(infosBean.getName3(), infosBean.getUrl3());
            tableRow.addView(infoItemView3);
        }
    }
}
