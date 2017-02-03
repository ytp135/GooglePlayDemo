package com.itheima.googleplaydemo.ui.fragment;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;

import com.itheima.googleplaydemo.R;
import com.itheima.googleplaydemo.app.Constant;
import com.itheima.googleplaydemo.bean.HomeBean;
import com.itheima.googleplaydemo.network.HeiMaRetrofit;
import com.itheima.googleplaydemo.ui.activity.AppDetailActivity;
import com.leon.loopviewpagerlib.Banner;

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
        Banner banner = (Banner) LayoutInflater.from(getContext()).inflate(R.layout.home_header, getListView(), false);
        banner.setImageUrlHost(Constant.URL_IMAGE);
        banner.setImageUrls(mLooperDataList);
        return banner;
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

    /**
     * 处理item的点击事件，由于多加了一个头，获取点击位置的数据时下标减1     */
    @Override
    protected void onListItemClick(int i) {
        Intent intent = new Intent(getContext(), AppDetailActivity.class);
        intent.putExtra("package_name", getAppList().get(i-1).getPackageName());
        startActivity(intent);
    }

    /**
     * 返回加载更多时的位置
     */
    @Override
    protected int getLoadMorePosition() {
        return getAdapter().getCount();
    }
}
