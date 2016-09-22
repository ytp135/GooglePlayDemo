package com.itheima.googleplaydemo.ui.fragment;

import android.content.Intent;
import android.view.View;

import com.itheima.googleplaydemo.bean.AppListItem;
import com.itheima.googleplaydemo.bean.HomeBean;
import com.itheima.googleplaydemo.network.HomeRequest;
import com.itheima.googleplaydemo.network.NetworkListener;
import com.itheima.googleplaydemo.ui.activity.AppDetailActivity;
import com.itheima.googleplaydemo.utils.LogUtils;
import com.itheima.googleplaydemo.widget.LoopView;

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
        new HomeRequest(0, mHomeBeanNetworkListener).execute();
    }

    @Override
    public void onLoadMore() {
        new HomeRequest(mHomeBean.getList().size(), mLoadMoreNetworkListener).execute();
    }

    private NetworkListener<HomeBean> mLoadMoreNetworkListener = new NetworkListener<HomeBean>() {
        @Override
        public void onResponse(HomeBean result) {
            LogUtils.d(TAG, "onResponse: ");
            mHomeBean.getList().addAll(result.getList());
            getAdapter().notifyDataSetChanged();
        }

        @Override
        public void onFailure(String error) {

        }

        @Override
        public void onEmpty() {

        }
    };

    private NetworkListener<HomeBean> mHomeBeanNetworkListener = new NetworkListener<HomeBean>() {
        @Override
        public void onResponse(HomeBean result) {
            mHomeBean = result;
            onDataLoadedSuccess();
        }

        @Override
        public void onFailure(String error) {
            onDataLoadedError();
        }

        @Override
        public void onEmpty() {
            onDataLoadedEmpty();
        }
    };

    @Override
    protected View onCreateHeaderView() {
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
