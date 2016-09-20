package com.itheima.googleplaydemo.ui.fragment;

import android.content.Intent;
import android.view.View;

import com.itheima.googleplaydemo.bean.AppListItem;
import com.itheima.googleplaydemo.network.NetworkDataLoader;
import com.itheima.googleplaydemo.network.NetworkListener;
import com.itheima.googleplaydemo.bean.HomeBean;
import com.itheima.googleplaydemo.ui.activity.AppDetailActivity;
import com.itheima.googleplaydemo.ui.widget.LoopView;

import java.util.List;

/**
 * 创建者: Leon
 * 创建时间: 2016/9/15 13:13
 * 描述： TODO
 */
public class HomeFragment extends BaseAppListFragment {
    private static final String TAG = "HomeFragment";

    private HomeBean mHomeBean;

    @Override
    protected void startLoadData() {
//        onDataLoadedSuccess();
        NetworkDataLoader.getInstance().loadHomeData(mHomeBeanNetworkListener);
    }

    private NetworkListener<HomeBean> mHomeBeanNetworkListener = new NetworkListener<HomeBean>() {
        @Override
        public void onResponse(HomeBean result) {
            mHomeBean = result;
            onDataLoadedSuccess();
        }

        @Override
        public void onFailure(String error) {

        }

        @Override
        public void onEmpty() {

        }
    };

    @Override
    protected View getHeaderView() {
        return new LoopView(getContext());
    }

    @Override
    protected void onListItemClick(int i) {
        Intent intent = new Intent(getContext(), AppDetailActivity.class);
        startActivity(intent);
    }

    @Override
    protected List<AppListItem> getListData() {
        return mHomeBean.getList();
    }
}
