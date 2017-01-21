package com.itheima.googleplaydemo.ui.fragment;

import android.content.Intent;
import android.view.View;

import com.itheima.googleplaydemo.bean.HomeBean;
import com.itheima.googleplaydemo.network.HeiMaRetrofit;
import com.itheima.googleplaydemo.ui.activity.AppDetailActivity;
import com.itheima.googleplaydemo.widget.LoopView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * 创建者: Leon
 * 创建时间: 2016/9/15 13:13
 * 描述： TODO
 */
public class HomeFragment extends BaseAppListFragment {

    private static final String TAG = "HomeFragment";

    private List<String> mLooperDataList = new ArrayList<String>();

    @Override
    protected void startLoadData() {
        mLooperDataList.clear();
        getAppList().clear();
        Call<HomeBean> listCall = HeiMaRetrofit.getInstance().getApi().listHome(0);
        listCall.enqueue(new Callback<HomeBean>() {
            @Override
            public void onResponse(Call<HomeBean> call, Response<HomeBean> response) {
                getAppList().addAll(response.body().getList());
                mLooperDataList.addAll(response.body().getPicture());
                onDataLoadedSuccess();
            }

            @Override
            public void onFailure(Call<HomeBean> call, Throwable t) {
                onDataLoadedError();
            }
        });
    }


    @Override
    protected View onCreateHeaderView() {
        LoopView loopView = new LoopView(getContext());
        loopView.setData(mLooperDataList);
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
        Call<HomeBean> listCall = HeiMaRetrofit.getInstance().getApi().listHome(getAppList().size());
        listCall.enqueue(new Callback<HomeBean>() {
            @Override
            public void onResponse(Call<HomeBean> call, Response<HomeBean> response) {
                getAppList().addAll(response.body().getList());
                getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<HomeBean> call, Throwable t) {
            }
        });
    }

    @Override
    protected int getLoadMorePosition() {
        return getAdapter().getCount();
    }
}
