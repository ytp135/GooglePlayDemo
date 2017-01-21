package com.itheima.googleplaydemo.ui.fragment;

import com.itheima.googleplaydemo.bean.AppListItem;
import com.itheima.googleplaydemo.network.HeiMaRetrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 创建者: Leon
 * 创建时间: 2016/9/15 13:13
 * 描述： TODO
 */
public class AppFragment extends BaseAppListFragment {

    @Override
    protected void startLoadData() {
        Call<List<AppListItem>> listCall = HeiMaRetrofit.getInstance().getApi().listApps(0);
        listCall.enqueue(new Callback<List<AppListItem>>() {
            @Override
            public void onResponse(Call<List<AppListItem>> call, Response<List<AppListItem>> response) {
                getAppList().addAll(response.body());
                onDataLoadedSuccess();
            }

            @Override
            public void onFailure(Call<List<AppListItem>> call, Throwable t) {
                onDataLoadedError();
            }
        });

    }


    @Override
    protected void onStartLoadMore() {
        Call<List<AppListItem>> listCall = HeiMaRetrofit.getInstance().getApi().listApps(getAppList().size());
        listCall.enqueue(new Callback<List<AppListItem>>() {
            @Override
            public void onResponse(Call<List<AppListItem>> call, Response<List<AppListItem>> response) {
                getAppList().addAll(response.body());
                getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<AppListItem>> call, Throwable t) {

            }
        });
    }
}
