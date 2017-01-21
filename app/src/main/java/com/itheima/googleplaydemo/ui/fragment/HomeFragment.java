package com.itheima.googleplaydemo.ui.fragment;

import android.content.Intent;
import android.view.View;

import com.itheima.googleplaydemo.bean.AppListItem;
import com.itheima.googleplaydemo.loader.HomeDataLoader;
import com.itheima.googleplaydemo.ui.activity.AppDetailActivity;
import com.itheima.googleplaydemo.widget.LoopView;

import java.util.List;


/**
 * 创建者: Leon
 * 创建时间: 2016/9/15 13:13
 * 描述： TODO
 */
public class HomeFragment extends BaseAppListFragment {

    @Override
    protected void startLoadData() {
        HomeDataLoader.getInstance().loadData(this);
    }


    @Override
    protected List<AppListItem> getAppList() {
        return HomeDataLoader.getInstance().getListData();
    }


    @Override
    protected View onCreateHeaderView() {
        LoopView loopView = new LoopView(getContext());
        loopView.setData(HomeDataLoader.getInstance().getLooperData());
        return loopView;
    }

    @Override
    protected void onListItemClick(int i) {
        Intent intent = new Intent(getContext(), AppDetailActivity.class);
        intent.putExtra("package_name", getAppList().get(i).getPackageName());
        startActivity(intent);
    }

    @Override
    protected void onStartLoadMore() {

    }
}
