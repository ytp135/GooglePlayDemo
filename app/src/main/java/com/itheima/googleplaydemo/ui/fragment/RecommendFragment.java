package com.itheima.googleplaydemo.ui.fragment;

import android.view.View;

import com.itheima.googleplaydemo.R;
import com.itheima.googleplaydemo.adapter.RecommendAdapter;
import com.itheima.googleplaydemo.network.HeiMaRetrofit;
import com.itheima.googleplaydemo.widget.StellarMap;

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
public class RecommendFragment extends BaseFragment {

    private List<String> mData = new ArrayList<String>();


    @Override
    protected void startLoadData(){
        Call<List<String>> listCall = HeiMaRetrofit.getInstance().getApi().listRecommend();
        listCall.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                mData = response.body();
                onDataLoadedSuccess();
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                onDataLoadedError();
            }
        });
    }

    @Override
    protected View onCreateContentView() {
        //创建星状图
        StellarMap stellarMap = new StellarMap(getContext());
        //设置adapter
        stellarMap.setAdapter(new RecommendAdapter(getContext(), mData));
        int padding = getResources().getDimensionPixelSize(R.dimen.padding);
        //设置星状图内部padding
        stellarMap.setInnerPadding(padding, padding, padding, padding);
        //设置布局网格15*20，越大分布越平均
        stellarMap.setRegularity(15, 20);
        //设置初始化组
        stellarMap.setGroup(0);
        return stellarMap;
    }
}
