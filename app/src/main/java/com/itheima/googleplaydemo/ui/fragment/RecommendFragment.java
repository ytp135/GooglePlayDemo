package com.itheima.googleplaydemo.ui.fragment;

import android.view.View;

import com.itheima.googleplaydemo.adapter.RecommendAdapter;
import com.itheima.googleplaydemo.network.HeiMaRetrofit;
import com.itheima.googleplaydemo.widget.stellarmap.StellarMap;

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
        StellarMap stellarMap = new StellarMap(getContext());
        stellarMap.setAdapter(new RecommendAdapter(getContext(), mData));
        stellarMap.setRegularity(15, 20);
        stellarMap.setGroup(0, false);
        return stellarMap;
    }
}
